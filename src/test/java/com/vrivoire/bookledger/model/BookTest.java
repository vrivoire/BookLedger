package com.vrivoire.bookledger.model;

import com.vrivoire.bookledger.model.Book;
import com.vrivoire.bookledger.model.BookHelper;
import com.vrivoire.bookledger.model.Genre;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    private Book book;

    public BookTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        book = new Book("Isaac Asimov", "Foundation", Genre.SCIFI, 255, 1955, 5, true);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testOK() {
        System.out.println("testOK");
        Set<ConstraintViolation<Book>> violations = BookHelper.validate(book);
        assertEquals(0, violations.size());
    }

    @Test
    public void testAuthor1() {
        System.out.println("testAuthor1");
        book.setAuthor("");
        Set<ConstraintViolation<Book>> violations = BookHelper.validate(book);
        assertEquals(2, violations.size());
    }

    @Test
    public void testAuthor2() {
        System.out.println("testAuthor2");
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < 201; i++) {
            string.append("*");

        }
        book.setAuthor(string.toString());
        Set<ConstraintViolation<Book>> violations = BookHelper.validate(book);
        assertEquals(1, violations.size());
    }

    @Test
    public void testTitle1() {
        System.out.println("testTitle1");
        book.setTitle("");
        Set<ConstraintViolation<Book>> violations = BookHelper.validate(book);
        assertEquals(2, violations.size());
    }

    @Test
    public void testTitle2() {
        System.out.println("testTitle2");
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < 201; i++) {
            string.append("*");

        }
        book.setTitle(string.toString());
        Set<ConstraintViolation<Book>> violations = BookHelper.validate(book);
        assertEquals(1, violations.size());
    }

    @Test
    public void testGenre() {
        System.out.println("testGenre");
        book.setGenre(null);
        Set<ConstraintViolation<Book>> violations = BookHelper.validate(book);
        assertEquals(1, violations.size());
    }

    @Test
    public void testPages() {
        System.out.println("testPages");
        book.setPages(0);
        Set<ConstraintViolation<Book>> violations = BookHelper.validate(book);
        assertEquals(2, violations.size());
    }

    @Test
    public void testRating1() {
        System.out.println("testRating1");
        book.setRating(0);
        Set<ConstraintViolation<Book>> violations = BookHelper.validate(book);
        assertEquals(1, violations.size());
    }

    @Test
    public void testRating2() {
        System.out.println("testRating2");
        book.setRating(6);
        Set<ConstraintViolation<Book>> violations = BookHelper.validate(book);
        assertEquals(1, violations.size());
    }
}
