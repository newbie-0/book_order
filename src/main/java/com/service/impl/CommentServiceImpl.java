package com.service.impl;

import com.bean.Comment;
import com.bean.CommentExample;
import com.bean.extend.CommentExtend;
import com.dao.CommentMapper;
import com.dao.extend.CommentExtendMapper;
import com.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private CommentExtendMapper commentExtendMapper;

    @Override
    public void save(Comment comment) {
        comment.setTime(new Date());
        comment.setStatus("待审核");
        commentMapper.insert(comment);
    }

    @Override
    public List<CommentExtend> findByBookId(Integer id) {
        List<CommentExtend> list = commentExtendMapper.findByBookId(id);
        return list;
    }

    @Override
    public List<CommentExtend> findUnCheckedComment() {
        List<CommentExtend> list = commentExtendMapper.findUnCheckedComment();
        return list;
    }

    @Override
    public void check(Integer id, String status) {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setStatus(status);
        commentMapper.updateByPrimaryKeySelective(comment);
    }
}
