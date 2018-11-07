package ru.innopolis.stc12.booksharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import ru.innopolis.stc12.booksharing.exceptions.ControllerException;

@Controller
public class AboutController {
    @GetMapping(value = "/about")
    @ExceptionHandler(ControllerException.class)
    public String getAboutPage(Model model) {
        model.addAttribute("aboutText", "Бумажные книги - это круто. Это возможность погрузиться на время в другую реальность с художественной книгой или научиться чему-то новому. Электронные книги не дают такого качества восприятия. Или может просто мы консерваторы :) Получил удовольствие/полезность от книги - поделись с другими. Одной книжкой могут воспользоваться многие.");
        return "about";
    }
}
