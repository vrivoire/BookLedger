package com.vrivoire.bookledger;

import com.vrivoire.bookledger.model.Book;
import com.vrivoire.bookledger.model.Genre;
import com.vrivoire.bookledger.repositories.BookRepository;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        BookRepository bookRepository = context.getBean(BookRepository.class);
        Book book = new Book("Isaac Asimov", "Foundation", Genre.SCIFI, 255, 1955, 5, true);
        bookRepository.save(book);

        showData(book);
        LOG.info("Server started.");
        LOG.info("You may go to the following URL: http://127.0.0.1:8080");
        LOG.info("H2 console URL: http://127.0.0.1:8080/h2-console  JDBC URL: jdbc:h2:mem:prvml or jdbc:h2:./database/prvml.db ((user: sa)");
        LOG.info("---------------------------------------------");
    }

    private static void showData(Book book) {
        LOG.info("---------------------------------------------");
        LOG.info(book);
        LOG.info("---------------------------------------------");
    }
}
