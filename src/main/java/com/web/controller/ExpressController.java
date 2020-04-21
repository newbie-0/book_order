package com.web.controller;

import com.bean.Express;
import com.bean.extend.ExpressExtend;
import com.service.ExpressService;
import com.util.Message;
import com.util.MessageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/express")
public class ExpressController {
    @Autowired
    private ExpressService expressService;

    @ApiOperation("查询商家所有快递单信息")
    @GetMapping("/findAllByMerchant")
    public Message findAllByMerchant(Integer merchant) {
        List<ExpressExtend> list = expressService.findAllByMerchant(merchant);
        return MessageUtil.success(list);
    }

    @ApiOperation("添加快递单")
    @PostMapping("/save")
    public Message save(Express express) {
        expressService.save(express);
        return MessageUtil.success("添加成功");
    }
}

