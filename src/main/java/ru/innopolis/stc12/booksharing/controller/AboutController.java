package ru.innopolis.stc12.booksharing.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import ru.innopolis.stc12.booksharing.exceptions.TestException;
import ru.innopolis.stc12.booksharing.service.UserService;

@Controller
public class AboutController {
    @GetMapping(value = "/about")
    @ExceptionHandler(TestException.class)
    public String getAboutPage(Model model) {
        model.addAttribute("aboutText", "Бумажные книги - это круто. Это возможность погрузиться на время в другую реальность с художественной книгой или научиться чему-то новому. Электронные книги не дают такого качества восприятия. Или может просто мы консерваторы :) Получил удовольствие/полезность от книги - поделись с другими. Одной книжкой могут воспользоваться многие.");

        UserDetails userDetails = UserService.currentUserDetails();
        String username = userDetails != null ? userDetails.getUsername() : null;
        model.addAttribute("username", username);

        return "about";
    }
}
