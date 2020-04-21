package com.service;

import com.bean.Category;
import com.bean.extend.CategoryBookExtend;
import com.bean.extend.CategoryExtend;

import java.util.List;

public interface CategoryService {
    void saveOrUpdate(Category category) throws Exception;

    List<Category> findParentCategory();

    List<Category> findAll();

    List<CategoryExtend> cascadeFindAll();

    List<CategoryBookExtend> cascadeBookFindAll();

    List<CategoryBookExtend> findAllCategoryAndBook(String isSort);

    void deleteById(Integer id) throws Exception;

    void batchDelete(Integer[] ids) throws Exception;
}
