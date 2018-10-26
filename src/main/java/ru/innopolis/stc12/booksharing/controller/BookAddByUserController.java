package ru.innopolis.stc12.booksharing.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc12.booksharing.entitys.Book;
import ru.innopolis.stc12.booksharing.entitys.BookWithOwner;
import ru.innopolis.stc12.booksharing.entitys.User;
import ru.innopolis.stc12.booksharing.services.BooksService;
import ru.innopolis.stc12.booksharing.services.BooksWithOwnerService;
import ru.innopolis.stc12.booksharing.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookAddByUserController {
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String PAGE_NAME = "addBookByUser";
    private BooksService booksService;
    private UserService userService;
    private BooksWithOwnerService booksWithOwnerService;

    @Autowired
    public void setBooksService(BooksService booksService) {
        this.booksService = booksService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBooksWithOwnerService(BooksWithOwnerService booksWithOwnerService) {
        this.booksWithOwnerService = booksWithOwnerService;
    }

    @RequestMapping(value = "/addBookByUser", method = RequestMethod.GET)
    public String showPage(Model model) {
        model.addAttribute("showSendRequestForm", "false");
        return PAGE_NAME;
    }

    @RequestMapping(value = "/addBookByUser/searchBook", method = RequestMethod.GET)
    public String searchBook(
            @RequestParam(value = "searchValue") String searchValue,
            Model model) {
        List<Book> bookList = searchBookByTypeValue(searchValue);
        if (!bookList.isEmpty()) {
            model.addAttribute("bookList", bookList);
        } else {
            model.addAttribute(MESSAGE_ATTRIBUTE, "Книга не найдена");
            model.addAttribute("showSendRequestForm", "true");
        }
        return PAGE_NAME;
    }

    @RequestMapping(value = "/addBookByUser/chooseBook", method = RequestMethod.GET)
    @ExceptionHandler(NumberFormatException.class)
    public String chooseBook(
            @RequestParam(value = "chooseBook") String isbn,
            Model model) {
        Book book = booksService.getByIsbn(Integer.valueOf(isbn));
        if (book != null) {
            model.addAttribute("book", book);
        } else {
            model.addAttribute(MESSAGE_ATTRIBUTE, "Что то пошло не так :(");
        }
        return PAGE_NAME;
    }

    @RequestMapping(value = "/addBookByUser/addBook", method = RequestMethod.GET)
    @ExceptionHandler({NumberFormatException.class, NullPointerException.class})
    public String addBook(
            @RequestParam(value = "addBook") String isbn,
            Model model, Principal principal) {
        Book book = booksService.getByIsbn(Integer.valueOf(isbn));
        User user = userService.getUserByLogin(principal.getName());
        if (booksWithOwnerService.addBook(new BookWithOwner(book, user))) {
            model.addAttribute(MESSAGE_ATTRIBUTE, "Книга успешно добавлена");
        } else {
            model.addAttribute(MESSAGE_ATTRIBUTE, "Не удалось добавить книгу, попробуйте позже");
        }
        return PAGE_NAME;
    }

    @RequestMapping(value = "/addBookByUser/sendRequest", method = RequestMethod.POST)
    public String sendRequest(
            @RequestParam(value = "bookName") String bookName,
            @RequestParam(value = "bookAuthor") String bookAuthor,
            @RequestParam(value = "bookIsbn") String bookIsbn,
            Model model) {
        model.addAttribute(MESSAGE_ATTRIBUTE, "Запрос отправлен, Вам придет уведомление на Ваш email");
        //TODO: add implement for email send
        return PAGE_NAME;
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
