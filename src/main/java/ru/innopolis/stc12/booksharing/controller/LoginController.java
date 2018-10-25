package ru.innopolis.stc12.booksharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc12.booksharing.exceptions.TestException;

@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ExceptionHandler(TestException.class)
    public String getLoginPage(Model model) {
        model.addAttribute("loginMessage", "Welcome!");
        return "login";
    }
}
