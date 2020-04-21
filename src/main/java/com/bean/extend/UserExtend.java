package com.bean.extend;

import com.bean.Privilege;
import com.bean.Role;
import com.bean.User;

import java.util.List;

public class UserExtend extends User {
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
