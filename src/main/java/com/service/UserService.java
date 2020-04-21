package com.service;

import com.bean.User;
import com.bean.extend.UserExtend;

import java.util.List;

public interface UserService {
    UserExtend findById(Integer id);

    UserExtend login(String username, String password) throws Exception;

    List<UserExtend> findAll();

    void save(User user) throws Exception;

    User update(User user);

    void deleteById(Integer id) throws Exception;

    void updateStatus(Integer id, boolean status);
}
