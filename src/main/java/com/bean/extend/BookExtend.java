package com.bean.extend;

import com.bean.Book;

import java.util.List;

public class BookExtend extends Book {
    public final static String UNCHECKED = "待审核";
    public final static String UNPASSED = "未通过";
    public final static String UNPUTAWAY = "未上架";
    public final static String PUTAWAY = "已上架";

    private List<Book> relations;

    public List<Book> getRelations() {
        return relations;
    }

    public void setRelations(List<Book> relations) {
        this.relations = relations;
    }
}
