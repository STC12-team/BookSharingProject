package ru.innopolis.stc12.booksharing.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.stc12.booksharing.exceptions.ControllerException;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.service.UserService;

@Controller
public class RegisterController {
    private Logger logger = Logger.getLogger(RegisterController.class);
    private UserService userService;
    private MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    @ExceptionHandler(ControllerException.class)
    public String getRegisterPage(Model model) {
        return "register";
    }

    @PostMapping(value = "/register")
    @ExceptionHandler(ControllerException.class)
    public ModelAndView postRegisterPage(@RequestParam(value = "newLogin") String login,
                                         @RequestParam(value = "newPassword") String password,
                                         @RequestParam(value = "newEmail") String email,
                                         Model model) {

        if (userService.getUserByLogin(login) != null) {
            logger.info("Пользователь с таким именем уже есть");
            model.addAttribute("loginErrorMessage", messageSource.getMessage("model.loginErrorMessage", null, "", LocaleContextHolder.getLocale()));
            return new ModelAndView("register");
        }

        User user = userService.addNewUser(login, password, email);
        model.addAttribute("user", user);

        return new ModelAndView("library");
    }
}
