package com.web.controller;

import com.bean.Role;
import com.service.RoleService;
import com.util.Message;
import com.util.MessageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation("查询所有的角色")
    @GetMapping("/findAll")
    public Message findAll() {
        List<Role> list = roleService.findAll();
        return MessageUtil.success(list);
    }
}
