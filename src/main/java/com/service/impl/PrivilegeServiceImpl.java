package com.service.impl;

import com.bean.Privilege;
import com.dao.extend.PrivilegeExtendMapper;
import com.service.PrivilegeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
    @Resource
    private PrivilegeExtendMapper privilegeExtendMapper;

    @Override
    public List<Privilege> selectByRoleId(Integer id) {
        return privilegeExtendMapper.selectByRoleId(id);
    }
}
