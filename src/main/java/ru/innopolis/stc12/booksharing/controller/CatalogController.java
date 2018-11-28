package ru.innopolis.stc12.booksharing.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.service.BookCopiesService;

import java.util.List;

@Controller
public class CatalogController {
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private BookCopiesService bookCopiesService;
    private Logger logger = Logger.getLogger(CatalogController.class);

    @Autowired
    public void setBookCopiesService(BookCopiesService bookCopiesService) {
        this.bookCopiesService = bookCopiesService;
    }

    @GetMapping(value = "/catalog")
    public String showCatalogPage(Model model) {
        List<BookCopy> bookCopies = bookCopiesService.getBookCopyByUser(0);
        if (!bookCopies.isEmpty()) {
            model.addAttribute("bookCopies", bookCopies);
        } else {
            model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Что то пошло не так :(");
            logger.error("BookCopies is Empty");
        }
        return "catalog";
    }
}
