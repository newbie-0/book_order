package com.dao.extend;

import com.bean.extend.UserExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserExtendMapper {
    UserExtend selectById(Integer id);

    List<UserExtend> findAll();

    void updateStatus(@Param("id") Integer id, @Param("status") boolean status);
}
