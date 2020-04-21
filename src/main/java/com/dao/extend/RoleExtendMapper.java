package com.dao.extend;

import com.bean.Role;

import java.util.List;

public interface RoleExtendMapper {
    Role selectByUserId(Integer id);
}
