package com.example.lab4;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BooksPageController {
    @GetMapping(value="/books")
    public String books() {
        return "books";
    }
}