package com.web.controller;

import com.bean.ShoppingCart;
import com.bean.extend.ShoppingCartExtend;
import com.service.ShoppingCartService;
import com.util.Message;
import com.util.MessageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @ApiOperation("保存或更新")
    @PostMapping("/saveOrUpdate")
    public Message addBook(ShoppingCart shoppingCart) {
        try {
            shoppingCartService.saveOrUpdate(shoppingCart);
            return MessageUtil.success("添加成功");
        } catch (Exception e) {
            return MessageUtil.error(e.getMessage());
        }
    }

    @ApiOperation("通过userId查询所有的购物车信息")
    @GetMapping("/findByUserId")
    public Message findByUserId(Integer userId) {
        List<ShoppingCartExtend> list = shoppingCartService.findByUserId(userId);
        return MessageUtil.success(list);
    }

    @ApiOperation("通过id删除")
    @DeleteMapping("/deleteById")
    public Message deleteById(Integer id) {
        shoppingCartService.deleteById(id);
        return MessageUtil.success("删除成功");
    }

    @ApiOperation("计算用户购物车的总价")
    @GetMapping("/getTotal")
    public Message getTotal(Integer userId) {
        BigDecimal total = shoppingCartService.getTotal(userId);
        return MessageUtil.success(total);
    }

    @ApiOperation("清空购物车")
    @DeleteMapping("/clear")
    public Message clear(Integer userId) {
        shoppingCartService.clear(userId);
        return MessageUtil.success("购物车已清空");
    }
}


