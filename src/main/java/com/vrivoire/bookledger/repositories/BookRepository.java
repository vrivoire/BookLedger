package com.vrivoire.bookledger.repositories;

import com.vrivoire.bookledger.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
