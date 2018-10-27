package ru.innopolis.stc12.booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.stc12.booksharing.exceptions.TestException;
import ru.innopolis.stc12.booksharing.model.pojo.User;
import ru.innopolis.stc12.booksharing.service.UserService;

@Controller
public class LoginController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    @ExceptionHandler(TestException.class)
    public String getLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", error);
        }

        return "login";
    }

    @PostMapping(value = "/login")
    @ExceptionHandler(TestException.class)
    public ModelAndView postLoginPage(@RequestParam(value = "login") String login,
                                     @RequestParam(value = "password") String password,
                                     Model model) {
        // clean up params
        password = password.replaceAll("[^a-zA-Z0-9]", "");
        login = login.replaceAll("[^a-zA-Z0-9]", "");

        if (userService.validateUser(login, password)) {
            User user = userService.getUserByLogin(login);
            model.addAttribute("user", user);

            return new ModelAndView("catalog");
        }
        model.addAttribute("loginError", "Check your credentials");
        return new ModelAndView("login");
    }
}
