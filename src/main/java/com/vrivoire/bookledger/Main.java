package com.vrivoire.bookledger;

import com.vrivoire.bookledger.model.Book;
import com.vrivoire.bookledger.model.Genre;
import com.vrivoire.bookledger.repositories.BookRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Main {

    private static BookRepository bookRepository = null;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        bookRepository = context.getBean(BookRepository.class);
        Book book = new Book("Isaac Asimov", "Foundation", Genre.SCIFI, 255, 1955, 5, true);
        bookRepository.save(book);
    }

    public static BookRepository getBookRepository() {
        return bookRepository;
    }

}
