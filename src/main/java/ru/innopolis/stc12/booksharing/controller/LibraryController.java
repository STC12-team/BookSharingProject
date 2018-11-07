package ru.innopolis.stc12.booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;
import ru.innopolis.stc12.booksharing.service.BookEditionsService;

import java.util.List;

@Controller
public class LibraryController {
    private BookEditionsService bookEditionsService;

    @Autowired
    public void setBookEditionsService(BookEditionsService bookEditionsService) {
        this.bookEditionsService = bookEditionsService;
    }

    @GetMapping("/library")
    public String getLibraryPage(Model model) {
        //TODO предусмотреть вывод только части книг, разделить на страницы
        List<BookEdition> bookEditionList = bookEditionsService.getAllBookEditions();
        model.addAttribute("bookEditionList", bookEditionList);
        return "/library";
    }
}
