package com.web.controller;

import com.bean.Refund;
import com.bean.extend.RefundExtend;
import com.bean.extend.UserExtend;
import com.service.RefundService;
import com.util.Message;
import com.util.MessageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/refund")
public class RefundController {
    @Autowired
    private RefundService refundService;

    @ApiOperation("保存")
    @PostMapping("/saveOrUpdate")
    public Message saveOrUpdate(Refund refund) {
        refundService.saveOrUpdate(refund);
        return MessageUtil.success("退货退款申请提交成功");
    }

    @ApiOperation("条件查询商家退款退货单")
    @GetMapping("/findRefundLike")
    public Message findRefundLike(Integer merchant, Integer id, String status, String consignee, String telephone, Date date) {
        List<RefundExtend> list = refundService.findRefundLike(merchant, status, id, consignee, telephone, date);
        return MessageUtil.success(list);
    }

    @ApiOperation(" 查询用户的退库退货单")
    @GetMapping("/findByUserId")
    public Message findByUserId(Integer userId) {
        List<RefundExtend> list = refundService.findByUserId(userId);
        return MessageUtil.success(list);
    }

    @ApiOperation("通过orderId退款")
    @PutMapping("/refund")
    public Message refund(Integer orderId) {
        UserExtend userExtend = refundService.refund(orderId);
        return MessageUtil.success("退款成功", userExtend);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
