package com.service.impl;

import com.bean.User;
import com.bean.UserExample;
import com.bean.extend.UserExtend;
import com.dao.UserMapper;
import com.dao.extend.UserExtendMapper;
import com.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserExtendMapper userExtendMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public UserExtend findById(Integer id) {
        return userExtendMapper.selectById(id);
    }

    @Override
    public UserExtend login(String username, String password) throws Exception {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> list = userMapper.selectByExample(userExample);
        if (list.size() > 0) {
            User u = list.get(0);
            if (!u.getStatus())
                throw new Exception("用户已失效");
            if (u.getPassword().equals(password)) {
                UserExtend userExtend = userExtendMapper.selectById(u.getId());
                return userExtend;
            } else {
                throw new Exception("密码错误");
            }
        } else {
            throw new Exception("用户名不存在");
        }
    }

    @Override
    public List<UserExtend> findAll() {
        List<UserExtend> list = userExtendMapper.findAll();
        return list;
    }

    @Override
    public void save(User user) throws Exception {
        user.setBalance(new BigDecimal(0));
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> list = userMapper.selectByExample(userExample);
        if (list.size() > 0)
            throw new Exception("用户名已存在!");
        userMapper.insert(user);
    }

    @Override
    public User update(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(user.getId());
        userMapper.updateByExampleSelective(user, userExample);
        userExample.createCriteria().andIdEqualTo(user.getId());
        User u = userMapper.selectByExample(userExample).get(0);
        return u;

    }

    @Override
    public void deleteById(Integer id) throws Exception {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(id);
        userMapper.deleteByExample(userExample);
    }

    @Override
    public void updateStatus(Integer id, boolean status) {
        userExtendMapper.updateStatus(id, status);
    }
}
