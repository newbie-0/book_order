package com.bean.extend;

import com.bean.Book;
import com.bean.ShoppingCart;

public class ShoppingCartExtend extends ShoppingCart {
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}