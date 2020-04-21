package com.service;

import com.bean.Book;
import com.bean.extend.BookExtend;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BookService {
    void saveOrUpdate(Book book);

    List<BookExtend> findByUserIdAndStatus(Integer userId, String status);

//    PageInfo<Book> findAll(int curPage, int size);

    void deleteById(Integer id) throws Exception;

    void batchDelete(Integer[] ids) throws Exception;

    PageInfo<Book> findBookLike(String status, String name, Integer categoryId, String orderField);

    List<Book> findRelationBook(Integer id);
}
