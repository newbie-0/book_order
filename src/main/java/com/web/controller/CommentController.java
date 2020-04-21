package com.web.controller;

import com.bean.Comment;
import com.bean.extend.CommentExtend;
import com.service.CommentService;
import com.util.Message;
import com.util.MessageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation("发布评论")
    @PostMapping("/save")
    public Message save(Comment comment) {
        commentService.save(comment);
        return MessageUtil.success("评论发布成功");
    }

    @ApiOperation("通过书籍id查询评论")
    @GetMapping("/findByBookId")
    public Message findByBookId(Integer id) {
        List<CommentExtend> list = commentService.findByBookId(id);
        return MessageUtil.success(list);
    }

    @ApiOperation("查询所有待审核的评论")
    @GetMapping("/findUnCheckedComment")
    public Message findUnCheckedComment() {
        List<CommentExtend> list = commentService.findUnCheckedComment();
        return MessageUtil.success(list);
    }

    @ApiOperation("评论的审核")
    @PutMapping("/check")
    public Message check(Integer id, String status) {
        commentService.check(id, status);
        return MessageUtil.success("评论审核完毕");
    }
}
