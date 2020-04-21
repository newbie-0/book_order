package com.service.impl;

import com.bean.*;
import com.bean.extend.OrderExtend;
import com.dao.BookMapper;
import com.dao.OrderMapper;
import com.dao.UserMapper;
import com.dao.extend.OrderExtendMapper;
import com.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private OrderExtendMapper orderExtendMapper;
    @Resource
    private UserMapper userMapper;

    private Timer timer = new Timer();

    @Override
    public void saveOrUpdate(Order order) {
        if (order.getId() == null) {
            Book book = bookMapper.selectByPrimaryKey(order.getBookId());
            order.setMerchant(book.getUserId());
            order.setStatus("待付款");
            order.setSubmitTime(new Date());
            orderMapper.insert(order);
            book.setInventory(book.getInventory() - order.getNumber());
            bookMapper.updateByPrimaryKey(book);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                Order realOrder = orderMapper.selectByPrimaryKey(order.getId());
                if (realOrder.getStatus().equals("待付款")) {
                    order.setStatus("未付款");
                    orderMapper.updateByPrimaryKey(order);
                    book.setInventory(book.getInventory() + order.getNumber());
                    bookMapper.updateByPrimaryKey(book);
                }
                }
            }, 1000 * 60);
        } else {
            if (order.getStatus().equals("未付款")) {
                Book book = bookMapper.selectByPrimaryKey(order.getBookId());
                book.setInventory(book.getInventory() + order.getNumber());
                bookMapper.updateByPrimaryKey(book);
            }
            orderMapper.updateByPrimaryKeySelective(order);
        }
    }

    @Override
    public List<OrderExtend> findByMerchant(Integer merchant) {
        List<OrderExtend> list = orderExtendMapper.findByMerchant(merchant);
        return list;
    }

    @Override
    public List<OrderExtend> findByUserIdAndStatus(Integer userId, String status) {
        List<OrderExtend> list = orderExtendMapper.findByUserIdAndStatus(userId, status);
        return list;
    }

    @Override
    public void deleteById(Integer id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<OrderExtend> findOrderLike(Integer userId, Integer id, String consignee, Date date) {
        List<OrderExtend> list = orderExtendMapper.findOrderLike(userId, id, consignee, date);
        return list;
    }

    @Override
    public void pay(Order order) {
        Order realOrder = orderMapper.selectByPrimaryKey(order.getId());
        Book book = bookMapper.selectByPrimaryKey(realOrder.getBookId());
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(book.getUserId());
        User user = userMapper.selectByExample(userExample).get(0);
        BigDecimal balance = user.getBalance().add(book.getPrice().multiply(new BigDecimal(realOrder.getNumber())));
        user.setBalance(balance);
        userMapper.updateByExample(user, userExample);
    }
}
