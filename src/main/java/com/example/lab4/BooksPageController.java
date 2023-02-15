package com.example.lab4;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class BooksPageController {
    @GetMapping(value="/books")
    public String books(Model model) {
        model.addAttribute("buddyInfoTemp", new BuddyInfoTempObj());
        return "books";
    }
}