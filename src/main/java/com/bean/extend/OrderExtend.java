package com.bean.extend;

import com.bean.*;

public class OrderExtend extends Order {
    private User user;
    private Book book;
    private Site site;
    private User merch;
    private Express express;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public User getMerch() {
        return merch;
    }

    public void setMerch(User merch) {
        this.merch = merch;
    }

    public Express getExpress() {
        return express;
    }

    public void setExpress(Express express) {
        this.express = express;
    }
}
