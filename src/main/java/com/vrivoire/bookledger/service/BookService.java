package com.vrivoire.bookledger.service;

import com.vrivoire.bookledger.model.Book;
import com.vrivoire.bookledger.model.BookJson;

import java.util.List;

public interface BookService {

    List<Book> loadAll();

    Book getBook(Long id);

    BookJson addBook(BookJson bookJson);
}
