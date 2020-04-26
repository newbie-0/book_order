package com.web.controller;

import com.bean.Privilege;
import com.bean.User;
import com.bean.extend.UserExtend;
import com.service.PrivilegeService;
import com.service.UserService;
import com.util.JwtTokenUtil;
import com.util.Message;
import com.util.MessageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PrivilegeService privilegeService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public Message login(@RequestBody User user) {
        try {
            // 1. 认证用户的用户名和密码
            UserExtend u = userService.login(user.getUsername(), user.getPassword());
            // 2. 如果登录成功产生token,将token缓存起来，返回
            String token = JwtTokenUtil.createJWT(u.getId(), u.getUsername());
            // 3. 如果登录失败
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("user", u);
            return MessageUtil.success(map);
        } catch (Exception e) {
            return MessageUtil.error(e.getMessage());
        }
    }

    @ApiOperation("退出")
    @PostMapping("/logout")
    public Message logout() {
        // 1. 将缓存中的token移除
        return MessageUtil.success("success");
    }

    @ApiOperation("获取所有的菜单")
    @GetMapping("/getMenuList")
    public Message getMenuList(HttpServletRequest request) {
        // 获取请求头中的token
        String token = request.getHeader("token");
        Integer id = Integer.parseInt(JwtTokenUtil.getUserId(token, JwtTokenUtil.base64Secret));
        UserExtend user = userService.findById(id);
        List<Privilege> list = privilegeService.selectByRoleId(user.getRoleId());
        return MessageUtil.success(list);
    }

    @ApiOperation("查询所有的用户")
    @GetMapping("/findAll")
    public Message findAll() {
        List<UserExtend> list = userService.findAll();
        return MessageUtil.success(list);
    }

    @ApiOperation("添加用户")
    @PostMapping("/save")
    public Message save(User user) {
        try {
            userService.save(user);
            return MessageUtil.success("添加成功");
        } catch (Exception e) {
            return MessageUtil.error(e.getMessage());
        }
    }

    @ApiOperation("更新用户信息")
    @PutMapping("/update")
    public Message update(User user) {
        User u = userService.update(user);
        return MessageUtil.success("更新成功", u);
    }

    @ApiOperation("/通过id删除用户")
    @DeleteMapping("/deleteById")
    public Message deleteById(Integer id) {
        try {
            userService.deleteById(id);
            return MessageUtil.success("删除成功");
        } catch (Exception e) {
            return MessageUtil.error("不能删除一个父级列");
        }
    }

    @ApiOperation(" 更新用户状态")
    @PutMapping("/updateStatus")
    public Message updateStatus(Integer id, boolean status) {
        userService.updateStatus(id, status);
        return MessageUtil.success("更新用户状态成功");
    }

    @ApiOperation("通过id查询用户信息")
    @GetMapping("/findById")
    public Message findById(Integer id) {
        UserExtend userExtend = userService.findById(id);
        return MessageUtil.success(userExtend);
    }
}



