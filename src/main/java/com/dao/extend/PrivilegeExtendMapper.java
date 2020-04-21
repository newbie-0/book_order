package com.dao.extend;

import com.bean.Privilege;

import java.util.List;

public interface PrivilegeExtendMapper {
    List<Privilege> selectByRoleId(Integer id);
}
