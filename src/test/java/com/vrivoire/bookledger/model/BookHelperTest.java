package com.vrivoire.bookledger.model;

import com.vrivoire.bookledger.model.Book;
import com.vrivoire.bookledger.model.BookHelper;
import com.vrivoire.bookledger.model.BookJson;
import com.vrivoire.bookledger.model.Genre;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookHelperTest {

    private Validator validator;
    private Book book;
    private BookJson bookJson;

    public BookHelperTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        book = new Book("Isaac Asimov", "Foundation", Genre.SCIFI, 255, 1955, 5, true);
        bookJson = new BookJson("Isaac Asimov", "Foundation", "SCIFI", "255", "1955", "5", "on", false, null);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testBuildFromJsonOK() {
        System.out.println("buildFromJsonOK");
        Book result = BookHelper.buildFromJson(bookJson);
        assertEquals(book, result);
    }

    @Test
    public void testBuildFromJson1() {
        System.out.println("buildFromJson1");
        bookJson.setAuthor("");
        Book result = BookHelper.buildFromJson(bookJson);
        assertNotEquals(book, result);
    }

    @Test
    public void testBuildFromJson2() {
        System.out.println("buildFromJson2");
        bookJson.setPages("0");
        Book result = BookHelper.buildFromJson(bookJson);
        Set<ConstraintViolation<Book>> validate = BookHelper.validate(result);
        assertEquals(2, validate.size());
    }

    @Test
    public void testBuildFromJson3() {
        System.out.println("buildFromJson3");
        bookJson.setPublication("abc");
        Book result = BookHelper.buildFromJson(bookJson);
        Set<ConstraintViolation<Book>> validate = BookHelper.validate(result);
        assertEquals(1, validate.size());
    }
}
