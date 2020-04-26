package com.web.controller;

import com.bean.Category;
import com.bean.extend.CategoryBookExtend;
import com.bean.extend.CategoryExtend;
import com.service.CategoryService;
import com.util.Message;
import com.util.MessageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation("分类的保存或更新")
    @PostMapping("/saveOrUpdate")
    public Message saveOrUpdate(Category category) {
        try {
            categoryService.saveOrUpdate(category);
            return MessageUtil.success("更新成功！");
        } catch (Exception e) {
            return MessageUtil.error(e.getMessage());
        }
    }

    @ApiOperation("查询所有父级分类")
    @GetMapping("/findParentCategory")
    public Message findParentCategory() {
        List<Category> list = categoryService.findParentCategory();
        return MessageUtil.success(list);
    }

    @ApiOperation("查询所有分类")
    @GetMapping("/findAll")
    public Message findAll() {
        List<Category> list = categoryService.findAll();
        return MessageUtil.success(list);
    }

    @ApiOperation(" 级联查询所分类")
    @GetMapping("/cascadeFindAll")
    public Message cascadeFindAll() {
        List<CategoryExtend> list = categoryService.cascadeFindAll();
        return MessageUtil.success(list);
    }

    @ApiOperation("查询所有分类及其下属分类的书籍")
    @GetMapping("/findAllCategoryAndBook")
    public Message findAllCategoryAndBook(String isSort) {
        List<CategoryBookExtend> list = categoryService.findAllCategoryAndBook(isSort);
        return MessageUtil.success(list);
    }


    @ApiOperation("删除分类")
    @DeleteMapping("/deleteById")
    public Message deleteById(Integer id) {
        try {
            categoryService.deleteById(id);
            return MessageUtil.success("删除成功");
        } catch (Exception e) {
            return MessageUtil.error("不能删除一个父级列");
        }
    }

    @ApiOperation("批量删除分类")
    @DeleteMapping("/batchDelete")
    public Message batchDelete(@RequestBody Integer[] ids) throws Exception {
        categoryService.batchDelete(ids);
        return MessageUtil.success("删除成功");
    }
}
