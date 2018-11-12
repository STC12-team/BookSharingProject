package ru.innopolis.stc12.booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import ru.innopolis.stc12.booksharing.exceptions.ControllerException;

import java.util.Locale;

@Controller
public class AboutController {
    @Autowired
    private ApplicationContext appContext;

    @GetMapping(value = "/about")
    @ExceptionHandler(ControllerException.class)
    public String getAboutPage(Locale locale, Model model) {
        model.addAttribute("aboutText", appContext.getMessage("message.about", new Object[]{}, locale));
        return "about";
    }
}
