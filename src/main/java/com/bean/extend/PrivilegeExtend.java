package com.bean.extend;

import com.bean.Privilege;

import java.util.List;

public class PrivilegeExtend extends Privilege {
    private List<Privilege> children;

    public List<Privilege> getChildren() {
        return children;
    }

    public void setChildren(List<Privilege> children) {
        this.children = children;
    }
}
