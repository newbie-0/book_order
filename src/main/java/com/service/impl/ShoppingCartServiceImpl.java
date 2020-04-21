package com.service.impl;

import com.bean.Book;
import com.bean.ShoppingCart;
import com.bean.ShoppingCartExample;
import com.bean.extend.ShoppingCartExtend;
import com.dao.BookMapper;
import com.dao.ShoppingCartMapper;
import com.dao.extend.ShoppingCartExtendMapper;
import com.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Resource
    private ShoppingCartMapper shoppingCartMapper;
    @Resource
    private ShoppingCartExtendMapper shoppingCartExtendMapper;
    @Resource
    private BookMapper bookMapper;

    @Override
    public void saveOrUpdate(ShoppingCart shoppingCart) throws Exception {
        Book book = bookMapper.selectByPrimaryKey(shoppingCart.getBookId());
        if (shoppingCart.getId() == null) {
            ShoppingCartExample shoppingCartExample = new ShoppingCartExample();
            shoppingCartExample.createCriteria().andUserIdEqualTo(shoppingCart.getUserId());
            List<ShoppingCart> list = shoppingCartMapper.selectByExample(shoppingCartExample);
            for (ShoppingCart cart : list) {
                if (cart.getBookId().equals(shoppingCart.getBookId())) {
                    Integer num = cart.getNumber();
                    num++;
                    if (book.getInventory() < num)
                        throw new Exception(book.getName() + "书籍库存不足");
                    cart.setNumber(num);
                    shoppingCartMapper.updateByPrimaryKey(cart);
                    return;
                }
            }
            if (book.getInventory() < shoppingCart.getNumber())
                throw new Exception(book.getName() + "书籍库存不足");
            shoppingCartMapper.insert(shoppingCart);
        } else {
            if (book.getInventory() < shoppingCart.getNumber())
                throw new Exception(book.getName() + "书籍库存不足");
            shoppingCartMapper.updateByPrimaryKeySelective(shoppingCart);
        }
    }

    @Override
    public List<ShoppingCartExtend> findByUserId(Integer userId) {
        List<ShoppingCartExtend> list = shoppingCartExtendMapper.findByUserId(userId);
        return list;
    }

    @Override
    public void deleteById(Integer id) {
        shoppingCartMapper.deleteByPrimaryKey(id);
    }

    @Override
    public BigDecimal getTotal(Integer userId) {
        BigDecimal total = new BigDecimal(0);
        List<ShoppingCartExtend> list = findByUserId(userId);
        for (ShoppingCartExtend shoppingCartExtend : list) {
            BigDecimal temp = shoppingCartExtend.getBook().getPrice().multiply(new BigDecimal(shoppingCartExtend.getNumber()));
            total = total.add(temp);
        }
        return total;
    }

    @Override
    public void clear(Integer userId) {
        ShoppingCartExample shoppingCartExample = new ShoppingCartExample();
        shoppingCartExample.createCriteria().andUserIdEqualTo(userId);
        shoppingCartMapper.deleteByExample(shoppingCartExample);
    }
}
