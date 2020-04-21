package com.service.impl;

import com.bean.Refund;
import com.bean.User;
import com.bean.UserExample;
import com.bean.extend.OrderExtend;
import com.bean.extend.RefundExtend;
import com.bean.extend.UserExtend;
import com.dao.RefundMapper;
import com.dao.UserMapper;
import com.dao.extend.OrderExtendMapper;
import com.dao.extend.RefundExtendMapper;
import com.dao.extend.UserExtendMapper;
import com.service.RefundService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class RefundServiceImpl implements RefundService {
    @Resource
    private RefundMapper refundMapper;
    @Resource
    private RefundExtendMapper refundExtendMapper;
    @Resource
    private OrderExtendMapper orderExtendMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserExtendMapper userExtendMapper;

    @Override
    public void saveOrUpdate(Refund refund) {
        if (refund.getId() == null) {
            refund.setTime(new Date());
            refund.setStatus("待处理");
            refund.setConsignee("");
            refund.setSite("");
            refund.setTelephone("");
            refund.setResult("");
            refundMapper.insertSelective(refund);
        } else  {
            refundMapper.updateByPrimaryKeySelective(refund);
        }
    }

    @Override
    public List<RefundExtend> findRefundLike(Integer merchant, String status, Integer id, String consignee, String telephone, Date date) {
        List<RefundExtend> list = refundExtendMapper.findRefundLike(merchant, id, status, consignee, telephone, date);
        return list;
    }

    @Override
    public List<RefundExtend> findByUserId(Integer userId) {
        List<RefundExtend> list = refundExtendMapper.findByUserId(userId);
        return list;
    }

    @Override
    public UserExtend refund(Integer orderId) {
        OrderExtend orderExtend = orderExtendMapper.selectById(orderId);
        User merchant = orderExtend.getMerch();
        User user = orderExtend.getUser();
        BigDecimal cost = orderExtend.getCost();
        merchant.setBalance(merchant.getBalance().subtract(cost));
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(merchant.getId());
        userMapper.updateByExampleSelective(merchant, userExample);
        userExample.clear();
        user.setBalance(user.getBalance().add(cost));
        userExample.createCriteria().andIdEqualTo(user.getId());
        userMapper.updateByExampleSelective(user, userExample);
        UserExtend userExtend = userExtendMapper.selectById(merchant.getId());
        return userExtend;
    }
}
