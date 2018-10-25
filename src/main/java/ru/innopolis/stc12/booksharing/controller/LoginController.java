package ru.innopolis.stc12.booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc12.booksharing.exceptions.TestException;
import ru.innopolis.stc12.booksharing.service.UserService;

@Controller
public class LoginController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ExceptionHandler(TestException.class)
    public String getLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("usersList", userService.getUsers());
        model.addAttribute("loginError", error);

        return "login";
    }
}
