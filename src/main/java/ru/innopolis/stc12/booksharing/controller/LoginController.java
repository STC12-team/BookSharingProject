package ru.innopolis.stc12.booksharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc12.booksharing.exceptions.TestException;

@Controller
public class LoginController {
    @GetMapping(value = "/login")
    @ExceptionHandler(TestException.class)
    public String getLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", error);
        }
        return "login";
    }
}
