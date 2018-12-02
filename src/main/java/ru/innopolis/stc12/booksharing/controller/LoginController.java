package ru.innopolis.stc12.booksharing.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc12.booksharing.exceptions.ControllerException;

@Controller
public class LoginController {
    private Logger logger = Logger.getLogger(LoginController.class);

    @GetMapping(value = "/login")
    @ExceptionHandler(ControllerException.class)
    public String getLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            throw new ControllerException("Введены неверный пароль или логин!");
        }
        return "login";
    }
}
