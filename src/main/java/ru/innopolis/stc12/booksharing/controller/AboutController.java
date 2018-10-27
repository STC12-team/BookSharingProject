package ru.innopolis.stc12.booksharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import ru.innopolis.stc12.booksharing.exceptions.TestException;

@Controller
public class AboutController {
    @GetMapping(value = "/about")
    @ExceptionHandler(TestException.class)
    public String getAboutPage(Model model) {
        model.addAttribute("aboutText", "Book sharing, the best project");
        return "about";
    }
}
