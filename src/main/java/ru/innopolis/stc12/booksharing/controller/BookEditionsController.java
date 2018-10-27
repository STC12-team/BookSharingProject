package ru.innopolis.stc12.booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc12.booksharing.exceptions.TestException;
import ru.innopolis.stc12.booksharing.service.BookEditionsService;

@Controller
public class BookEditionsController {
    BookEditionsService bookEditionsService;

    @Autowired
    public void setBookEditionsService(BookEditionsService bookEditionsService) {
        this.bookEditionsService = bookEditionsService;
    }

    @GetMapping(value = "/book_editions")
    @ExceptionHandler(TestException.class)
    public String getBookEditionsPage(Model model) {
        model.addAttribute("bookEditions", bookEditionsService.getAllBookEditions());
        return "book_editions";
    }

    @GetMapping(value = "/addBookEdition")
    public String showAddBookEdition(Model model) {
        return "addBookEdition";
    }

    @PostMapping(value = "/addBookEditionUrl")
    public String addBookEdition(
            @RequestParam(value = "bookEditionTitle", required = true) String title,
            @RequestParam(value = "bookEditionDescription", required = true) String description,
            @RequestParam(value = "bookEditionIsbn", required = true) String isbn,
            Model model) {
        bookEditionsService.addBookEdition(title, description, isbn);
        return "addBookEdition";
    }
}
