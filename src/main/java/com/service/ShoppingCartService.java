package com.service;

import com.bean.ShoppingCart;
import com.bean.extend.ShoppingCartExtend;

import java.math.BigDecimal;
import java.util.List;

public interface ShoppingCartService {
    void saveOrUpdate(ShoppingCart shoppingCart) throws Exception;

    List<ShoppingCartExtend> findByUserId(Integer userId);

    void deleteById(Integer id);

    BigDecimal getTotal(Integer userId);

    void clear(Integer userId);
}
