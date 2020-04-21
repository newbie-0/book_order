package com.service.impl;

import com.bean.Role;
import com.bean.RoleExample;
import com.dao.RoleMapper;
import com.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        RoleExample roleExample = new RoleExample();
        List<Role> list = roleMapper.selectByExample(roleExample);
        return list;
    }
}
