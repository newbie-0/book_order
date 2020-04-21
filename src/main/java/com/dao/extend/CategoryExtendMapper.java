package com.dao.extend;

import com.bean.Category;
import com.bean.extend.CategoryBookExtend;
import com.bean.extend.CategoryExtend;

import java.util.List;

public interface CategoryExtendMapper {
    List<CategoryExtend> cascadeFindAll();

    List<Category> selectChildrenById(Integer id);

    List<CategoryBookExtend> cascadeBookFindAll();

    List<CategoryBookExtend> findAllCategoryAndBook();
}
