package com.vrivoire.bookledger.controller;

import com.vrivoire.bookledger.model.Book;

import java.util.List;

public class AjaxResponseBody {

    String msg;
    List<Book> books;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AjaxResponseBody [");
        builder.append("books=").append(books);
        builder.append(", msg=").append(msg);
        builder.append("]");
        return builder.toString();
    }

}
