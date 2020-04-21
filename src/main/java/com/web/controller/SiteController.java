package com.web.controller;

import com.bean.Site;
import com.service.SiteService;
import com.util.Message;
import com.util.MessageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/site")
public class SiteController {
    @Autowired
    private SiteService siteService;

    @ApiOperation("查询所有发货地点")
    @GetMapping("/findAll")
    public Message findAll() {
        List<Site> list = siteService.findAll();
        return MessageUtil.success(list);
    }

    @ApiOperation("添加发货地点")
    @PostMapping("/save")
    public Message save(Site site) {
        try {
            siteService.save(site);
            return MessageUtil.success("添加成功");
        } catch (Exception e) {
            return MessageUtil.error(e.getMessage());
        }
    }

    @ApiOperation("根据id删除发货地点")
    @DeleteMapping("/deleteById")
    public Message deleteById(Integer id) {
        siteService.deleteById(id);
        return MessageUtil.success("删除成功");
    }
}

