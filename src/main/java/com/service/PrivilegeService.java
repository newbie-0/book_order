package com.service;

import com.bean.Privilege;

import java.util.List;

public interface PrivilegeService {
    List<Privilege> selectByRoleId(Integer id);
}
