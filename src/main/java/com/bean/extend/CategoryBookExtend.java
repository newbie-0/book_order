package com.bean.extend;

import com.bean.Book;
import com.bean.Category;

import java.util.List;

public class CategoryBookExtend extends Category {
    private List<Book> children;

    public List<Book> getChildren() {
        return children;
    }

    public void setChildren(List<Book> children) {
        this.children = children;
    }
}
