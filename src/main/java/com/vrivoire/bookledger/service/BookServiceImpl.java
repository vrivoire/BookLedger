package com.vrivoire.bookledger.service;

import com.vrivoire.bookledger.model.Book;
import com.vrivoire.bookledger.model.BookHelper;
import com.vrivoire.bookledger.model.BookJson;
import com.vrivoire.bookledger.repositories.BookRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> loadAll() {
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "author").ignoreCase();
        Sort.Order[] orders = new Sort.Order[1];
        orders[0] = order;
        Sort sort = Sort.by(orders);
        return bookRepository.findAll(sort);
    }

    @Override
    public Book getBook(Long id) {
        Optional<Book> optional = bookRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public BookJson addBook(BookJson bookJson) {
        BookJson response = bookJson;
        Book book = BookHelper.buildFromJson(bookJson);
        Set<ConstraintViolation<Book>> violations = validate(book);

        if (violations.isEmpty()) {
            bookJson.setValidated(true);
            Book save = bookRepository.save(book);
            response.setValidated(true);
        } else {
            Map<String, String> errors = new HashMap<>();
            violations.forEach((violation) -> {
                errors.put(violation.getInvalidValue().toString(), violation.getMessage());
            });
            response.setErrorMessages(errors);
            bookJson.setValidated(false);
        }
        return response;
    }

    private Set<ConstraintViolation<Book>> validate(Book book) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        return violations;
    }
}
