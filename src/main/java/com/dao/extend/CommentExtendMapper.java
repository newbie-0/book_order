package com.dao.extend;

import com.bean.extend.CommentExtend;

import java.util.List;

public interface CommentExtendMapper {
    List<CommentExtend> findByBookId(Integer bookId);

    List<CommentExtend> findUnCheckedComment();
}
