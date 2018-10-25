package ru.innopolis.stc12.booksharing.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc12.booksharing.entitys.Book;
import ru.innopolis.stc12.booksharing.services.BooksService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookAddByUserController {
    private BooksService booksService;

    @Autowired
    public void setBooksService(BooksService booksService) {
        this.booksService = booksService;
    }

    @RequestMapping(value = "/booksFound", method = RequestMethod.GET)
    public String openSearchBookPage(Model model) {
        return "booksFound";
    }

    @RequestMapping(value = "/booksFound/searchBook", method = RequestMethod.GET)
    public String searchBook(
            @RequestParam(value = "searchValue") String searchValue,
            Model model) {
        List<Book> bookList = searchBookByTypeValue(searchValue);
        if (!bookList.isEmpty()) {
            model.addAttribute("bookList", bookList);
        } else {
            model.addAttribute("message", "Книга не найдена");
        }
        return "booksFound";
    }

    @RequestMapping(value = "/booksFound/chooseBook", method = RequestMethod.GET)
    public String chooseBook(
            @RequestParam(value = "chooseBook") String isbn,
            Model model) {
        model.addAttribute("message", isbn);
        return "booksFound";
    }

    private List<Book> searchBookByTypeValue(String typeValue) {
        if (typeValue == null) {
            return new ArrayList<>();
        }
        List<Book> bookList = booksService.getByName(typeValue);
        if (!bookList.isEmpty()) {
            return bookList;
        }
        bookList = booksService.getByAuthor(typeValue);
        if (!bookList.isEmpty()) {
            return bookList;
        }
        try {
            Book book = booksService.getByIsbn(Integer.valueOf(typeValue));
            if (book != null) {
                bookList.add(book);
            }
        } catch (NumberFormatException e) {
            Logger.getLogger(BookAddByUserController.class).error(e);
        }
        return bookList;
    }
}
