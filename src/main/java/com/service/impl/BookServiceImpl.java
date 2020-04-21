package com.service.impl;

import com.bean.Book;
import com.bean.BookExample;
import com.bean.Category;
import com.bean.RelationExample;
import com.bean.extend.BookExtend;
import com.bean.extend.CategoryExtend;
import com.dao.BookMapper;
import com.dao.extend.BookExtendMapper;
import com.dao.extend.CategoryExtendMapper;
import com.github.pagehelper.PageInfo;
import com.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;
    @Resource
    private BookExtendMapper bookExtendMapper;
    @Resource
    private CategoryExtendMapper categoryExtendMapper;

    @Override
    public void saveOrUpdate(Book book) {
        if (book.getId() != null) {
            bookMapper.updateByPrimaryKeySelective(book);
        } else {
            book.setStatus(BookExtend.UNCHECKED);
            book.setSale(0);
            book.setTime(new Date());
            if (book.getImage() == null)
                book.setImage("");
            bookMapper.insert(book);
        }
    }

/*    @Override
    public PageInfo<Book> findAll(int curPage, int size) {
        PageHelper.startPage(curPage, size);
        List<Book> list = bookMapper.selectByExample(new BookExample());
        PageInfo<Book> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }*/

    @Override
    public List<BookExtend> findByUserIdAndStatus(Integer userId, String status) {
        List<BookExtend> list = bookExtendMapper.findByUserIdAndStatus(userId, status);
        return list;
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        Book book = bookMapper.selectByPrimaryKey(id);
        if (book == null)
            throw new Exception("该书籍不存在");
        bookMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDelete(Integer[] ids) throws Exception {
        for (Integer id : ids)
            deleteById(id);
    }

    @Override
    public PageInfo<Book> findBookLike(String status, String name, Integer categoryId, String orderField) {
        List<Category> children = categoryExtendMapper.selectChildrenById(categoryId);
        List<Book> list;
        if (children.size() == 0)
            list = bookExtendMapper.findBookLike(status, name, categoryId, orderField);
        else {
            list = bookExtendMapper.findBookLike(status, name, categoryId, orderField);
            for (int i = 0; i < children.size(); i++) {
                Category categoryExtend = children.get(i);
                List<Book> bookList = bookExtendMapper.findBookLike(status, name, categoryExtend.getId() ,orderField);
                for (int j = 0; j < bookList.size(); j++) {
                    Book book = bookList.get(j);
                    if (list.size() == 0)
                        list.add(book);
                    else if (!list.contains(book)) {
                        list.add(book);
                    }
                }
            }
        }
        return new PageInfo<>(list);

    }

    @Override
    public List<Book> findRelationBook(Integer id) {
        List<Book> list = bookExtendMapper.findRelationBook(id);
        return list;
    }
}
