package com.service;

import com.bean.Comment;
import com.bean.extend.CommentExtend;

import java.util.List;

public interface CommentService {
    void save(Comment comment);

    List<CommentExtend> findByBookId(Integer id);

    List<CommentExtend> findUnCheckedComment();

    void check(Integer id, String status);
}
