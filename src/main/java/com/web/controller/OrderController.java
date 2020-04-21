package com.web.controller;

import com.bean.Order;
import com.bean.extend.OrderExtend;
import com.service.OrderService;
import com.util.Message;
import com.util.MessageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation("保存或更新")
    @PostMapping("/saveOrUpdate")
    public Message saveOrUpdate(Order order) {
        orderService.saveOrUpdate(order);
        return MessageUtil.success("更新成功", order.getId());
    }

    @ApiOperation("查询商家的所有订单")
    @GetMapping("/findByMerchant")
    public Message findByMerchant(Integer merchant) {
        List<OrderExtend> list = orderService.findByMerchant(merchant);
        return MessageUtil.success(list);
    }

    @ApiOperation("根据状态查询用户的订单")
    @GetMapping("/findByUserIdAndStatus")
    public Message findByUserIdAndStatus(Integer userId, String status) {
        List<OrderExtend> list = orderService.findByUserIdAndStatus(userId, status);
        return MessageUtil.success(list);
    }

    @ApiOperation("条件查询用户订单")
    @GetMapping("/findOrderLike")
    public Message findOrderLike(Integer userId, Integer id, String consignee, Date date) {
        List<OrderExtend> list = orderService.findOrderLike(userId, id, consignee, date);
        return MessageUtil.success(list);
    }

    @ApiOperation("通过id删除订单")
    @DeleteMapping("/deleteById")
    public Message deleteById(Integer id) {
        orderService.deleteById(id);
        return MessageUtil.success("删除成功");
    }

    @ApiOperation("付款")
    @PutMapping("pay")
    public Message pay(Order order) {
        orderService.pay(order);
        return MessageUtil.success("付款成功");
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}

