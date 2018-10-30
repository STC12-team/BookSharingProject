package ru.innopolis.stc12.booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopy;
import ru.innopolis.stc12.booksharing.service.BookCopiesService;

import java.util.List;

@Controller
public class CatalogController {
    private static final String MESSAGE_ATTRIBUTE = "message";
    private BookCopiesService bookCopiesService;


    @Autowired
    public void setBookCopiesService(BookCopiesService bookCopiesService) {
        this.bookCopiesService = bookCopiesService;
    }

    @GetMapping(value = "/catalog")
    public String showCatalogPage(Model model) {
        List<BookCopy> bookCopies = bookCopiesService.getAllBookCopies();
        if (!bookCopies.isEmpty()) {
            model.addAttribute("bookCopies", bookCopies);
        } else {
            model.addAttribute(MESSAGE_ATTRIBUTE, "Что то пошло не так :(");
        }
        return "catalog";
    }
}
