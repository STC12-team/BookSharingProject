package ru.innopolis.stc12.booksharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc12.booksharing.exceptions.TestException;

@Controller
public class AboutController {
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    @ExceptionHandler(TestException.class)
    public String getAboutPage(Model model) {
        model.addAttribute("aboutText", "Book sharing, the best project");
        return "about";
    }
}
