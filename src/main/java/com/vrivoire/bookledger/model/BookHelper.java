package com.vrivoire.bookledger.model;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class BookHelper {

    public static Book buildFromJson(BookJson bookJson) {
        Book book = new Book();
        try {
            book.setAuthor(bookJson.getAuthor());
            book.setTitle(bookJson.getTitle());
            book.setGenre(Genre.valueOf(bookJson.getGenre()));
            book.setPages(Integer.decode(bookJson.getPages()));
            book.setPublication(Integer.decode(bookJson.getPublication()));
            book.setRating(Integer.decode(bookJson.getRating()));
            book.setIsRead((bookJson.getIsRead() != null) && bookJson.getIsRead().equalsIgnoreCase("on"));
        } catch (NumberFormatException e) {
            // continue, the book will be validated after
            e.printStackTrace(System.err);
        }
        return book;
    }

    public static Set<ConstraintViolation<Book>> validate(Book book) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        violations.forEach((violation) -> {
            System.out.println(violations.size() + " " + violation.getMessage());
        });
        return violations;
    }
}
