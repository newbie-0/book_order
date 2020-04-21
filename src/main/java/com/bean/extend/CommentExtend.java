package com.bean.extend;

import com.bean.Comment;
import com.bean.User;

public class CommentExtend extends Comment {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
