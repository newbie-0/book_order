package com.dao.extend;

import com.bean.extend.ShoppingCartExtend;

import java.util.List;

public interface ShoppingCartExtendMapper {
    List<ShoppingCartExtend> findByUserId(Integer userId);
}
