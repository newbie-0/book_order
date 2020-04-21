package com.service.impl;

import com.bean.Book;
import com.bean.Category;
import com.bean.CategoryExample;
import com.bean.extend.CategoryBookExtend;
import com.bean.extend.CategoryExtend;
import com.dao.CategoryMapper;
import com.dao.extend.BookExtendMapper;
import com.dao.extend.CategoryExtendMapper;
import com.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private CategoryExtendMapper categoryExtendMapper;
    @Resource
    private BookExtendMapper bookExtendMapper;

    @Override
    public void saveOrUpdate(Category category) throws Exception {
        if (category.getId() != null) {
            categoryMapper.updateByPrimaryKeySelective(category);
        } else {
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.createCriteria().andNameEqualTo(category.getName());
            List<Category> list = categoryMapper.selectByExample(categoryExample);
            if (list.size() > 0)
                throw new Exception("书籍分类名称不能重复！");
            categoryMapper.insert(category);
        }
    }

    @Override
    public List<Category> findParentCategory() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andParentIdIsNull();
        List<Category> list = categoryMapper.selectByExample(categoryExample);
        return list;
    }

    @Override
    public List<Category> findAll() {
        List<Category> list = categoryMapper.selectByExample(new CategoryExample());
        return list;
    }

    @Override
    public List<CategoryExtend> cascadeFindAll() {
        return categoryExtendMapper.cascadeFindAll();
    }

    @Override
    public List<CategoryBookExtend> cascadeBookFindAll() {
        List<CategoryBookExtend> list = categoryExtendMapper.cascadeBookFindAll();
        return list;
    }

    @Override
    public List<CategoryBookExtend> findAllCategoryAndBook(String isSort) {
        List<CategoryBookExtend> list = categoryExtendMapper.findAllCategoryAndBook();
        for (CategoryBookExtend categoryBookExtend : list) {
            List<Book> bookList = categoryBookExtend.getChildren();
            List<Category> categoryList = categoryExtendMapper.selectChildrenById(categoryBookExtend.getId());
            if (categoryList.size() != 0) {
                for (Category category : categoryList) {
                    List<Book> tempList = bookExtendMapper.findBookLike(null, null, category.getId(), null);
                    for (Book book : tempList)
                        bookList.add(book);
                }
                categoryBookExtend.setChildren(bookList);
            }
        }
        if (isSort != null) {
            list.sort(new Comparator<CategoryBookExtend>() {
                @Override

                public int compare(CategoryBookExtend o1, CategoryBookExtend o2) {
                    if (o1.getChildren().size() >= o2.getChildren().size())
                        return -1;
                    else
                        return 1;
                }
            });
        }
        return list;
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        Category category = categoryMapper.selectByPrimaryKey(id);
        if (category == null)
            throw new Exception("该分类不存在");
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDelete(Integer[] ids) throws Exception {
        for (Integer id : ids)
            deleteById(id);
    }
}
