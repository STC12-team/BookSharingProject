package ru.innopolis.stc12.booksharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.stc12.booksharing.exceptions.TestException;

@Controller
public class CatalogController {

    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    @ExceptionHandler(TestException.class)
    public String getLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (model.containsAttribute("user")) {
            return "catalog";
        }
        return "redirect:login";
    }
}
