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
public class RegisterController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    @ExceptionHandler(TestException.class)
    public String getRegisterPage(Model model) {
        return "register";
    }

    @PostMapping(value = "/register")
    @ExceptionHandler(TestException.class)
    public ModelAndView postRegisterPage(@RequestParam(value = "newLogin") String login,
                                         @RequestParam(value = "newPassword") String password,
                                         Model model) {

        if (userService.getUserByLogin(login) != null) {
            model.addAttribute("loginErrorMessage", "Пользователь с таким именем уже есть");
            return new ModelAndView("register");
        }

        User user = userService.addUser(login, password);
        model.addAttribute("user", user);

        return new ModelAndView("catalog");
    }
}