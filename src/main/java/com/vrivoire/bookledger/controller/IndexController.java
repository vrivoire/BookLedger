package com.vrivoire.bookledger.controller;

import com.vrivoire.bookledger.model.Book;
import com.vrivoire.bookledger.model.BookJson;
import com.vrivoire.bookledger.service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class IndexController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/")
    public String showIndex(Model model) {
        return "index";
    }

    @RequestMapping(value = "/getBooks", method = RequestMethod.GET)
    public ResponseEntity<?> getBooks() {

        AjaxResponseBody result = new AjaxResponseBody();
        try {
            List<Book> books = bookService.loadAll();

            if (books.isEmpty()) {
                result.setMsg("No books found!");
            } else {
                result.setMsg("Success");
            }
            result.setBooks(books);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RedirectView addBook(@ModelAttribute BookJson bookJson, RedirectAttributes redirectAttrs) {

        BookJson respone = bookJson;
        try {
            respone = bookService.addBook(bookJson);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(respone);
            redirectAttrs.addAttribute(json);
        } catch (JsonProcessingException e) {
            Map<String, String> map = new HashMap<>();
            map.put("Error", e.getMessage());
            respone.setErrorMessages(map);
            e.printStackTrace(System.err);
        }
        return new RedirectView("/", true);
    }
}
