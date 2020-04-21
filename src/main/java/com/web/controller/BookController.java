package com.web.controller;

import com.bean.Book;
import com.bean.Relation;
import com.bean.extend.BookExtend;
import com.github.pagehelper.PageInfo;
import com.service.BookService;
import com.service.RelationService;
import com.util.FastDFS;
import com.util.Message;
import com.util.MessageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private RelationService relationService;


    @ApiOperation("书籍的保存或更新")
    @PostMapping("/saveOrUpdate")
    public Message saveOrUpdate(Book book) {
        bookService.saveOrUpdate(book);
        return MessageUtil.success("更新成功", book.getId());
    }

    @ApiOperation("书籍的审核")
    @PutMapping("/check")
    public Message check(Integer id, String status) {
        Book book = new Book();
        book.setId(id);
        book.setStatus(status);
        bookService.saveOrUpdate(book);
        return MessageUtil.success("审核完毕");
    }

    @ApiOperation("通过userId查询所有书籍")
    @GetMapping("/findByUserIdAndStatus")
    public Message findByUserIdAndStatus(Integer userId, String status) {
        List<BookExtend> list =  bookService.findByUserIdAndStatus(userId, status);
        return MessageUtil.success(list);
    }

//    @ApiOperation("查询所有书籍信息")
//    @GetMapping("/findAll")
//    public Message findAllUser(int curPage) {
//        PageInfo<Book> pageInfo = bookService.findAll(curPage, 5);
//        return MessageUtil.success(pageInfo);
//    }

    @ApiOperation("删除书籍")
    @DeleteMapping("/deleteById")
    public Message deleteById(Integer id) throws Exception {
        bookService.deleteById(id);
        return MessageUtil.success("删除成功");
    }

    @ApiOperation("批量删除书籍")
    @PostMapping("/batchDelete")
    public Message batchDelete(@RequestBody Integer[] ids) throws Exception {
        bookService.batchDelete(ids);
        return MessageUtil.success("批量删除成功");
    }

    @ApiOperation("书籍上架")
    @PutMapping("/putaway")
    public Message putaway(Integer id) {
        Book book = new Book();
        book.setId(id);
        book.setStatus("已上架");
        bookService.saveOrUpdate(book);
        return MessageUtil.success("上架成功");
    }

    @ApiOperation("书籍下架")
    @PutMapping("/soldOut")
    public Message soldOut(Integer id) {
        Book book = new Book();
        book.setId(id);
        book.setStatus("未上架");
        bookService.saveOrUpdate(book);
        return MessageUtil.success("下架成功");
    }

    @ApiOperation("条件查询书籍")
    @GetMapping("/findBookLike")
    public Message findBookLike(String status, String name, Integer categoryId, String orderField) {
        if (orderField != null) {
            if ("1".equals(orderField))
                orderField = "price";
            else {
                orderField = "sale";
            }
        }
        PageInfo<Book> list = bookService.findBookLike(status, name, categoryId, orderField);
        return MessageUtil.success(list);
    }


    @ApiOperation("为书籍添加关联书籍")
    @PostMapping("/saveRelation")
    public Message saveRelation(Integer bookId, Integer[] relationId) {
        relationService.save(bookId, relationId);
        return MessageUtil.success("关联商品添加完毕");
    }

    @ApiOperation("查询关联书籍")
    @GetMapping("/findRelationBook")
    public Message findRelationBook(Integer id) {
        List<Book> list = bookService.findRelationBook(id);
        return MessageUtil.success(list);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
