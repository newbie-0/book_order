package com.service;

import com.bean.Order;
import com.bean.extend.OrderExtend;

import java.util.Date;
import java.util.List;

public interface OrderService {
    void saveOrUpdate(Order order);

    List<OrderExtend> findByMerchant(Integer merchant);

    List<OrderExtend> findByUserIdAndStatus(Integer userId, String status);

    void deleteById(Integer id);

    List<OrderExtend> findOrderLike(Integer userId, Integer id, String consignee, Date date);

    void pay(Order order);
}
