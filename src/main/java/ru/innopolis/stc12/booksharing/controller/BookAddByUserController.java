package ru.innopolis.stc12.booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopy;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;
import ru.innopolis.stc12.booksharing.model.pojo.User;
import ru.innopolis.stc12.booksharing.service.BookCopiesService;
import ru.innopolis.stc12.booksharing.service.BookEditionsService;
import ru.innopolis.stc12.booksharing.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookAddByUserController {
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String PAGE_NAME = "addBookByUser";
    private BookEditionsService bookEditionsService;
    private UserService userService;
    private BookCopiesService bookCopiesService;

    @Autowired
    public void setBookEditionsService(BookEditionsService bookEditionsService) {
        this.bookEditionsService = bookEditionsService;
    }

    @Autowired
    public void setUsersService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBookCopiesService(BookCopiesService bookCopiesService) {
        this.bookCopiesService = bookCopiesService;
    }

    @GetMapping(value = "/addBookByUser")
    public String showPage(Model model) {
        model.addAttribute("showSendRequestForm", "false");
        return PAGE_NAME;
    }

    @GetMapping(value = "/addBookByUser/searchBook")
    public String searchBook(
            @RequestParam(value = "searchValue") String searchValue,
            Model model) {
        List<BookEdition> bookEditionList = searchBookByTypeValue(searchValue);
        if (!bookEditionList.isEmpty()) {
            model.addAttribute("bookEditionList", bookEditionList);
        } else {
            model.addAttribute(MESSAGE_ATTRIBUTE, "Книга не найдена");
            model.addAttribute("showSendRequestForm", "true");
        }
        return PAGE_NAME;
    }

    @GetMapping(value = "/addBookByUser/chooseBook")
    @ExceptionHandler(NumberFormatException.class)
    public String chooseBook(
            @RequestParam(value = "chooseBook") String isbn,
            Model model) {
        BookEdition bookEdition = bookEditionsService.getByIsbn(isbn);
        if (bookEdition != null) {
            model.addAttribute("bookEdition", bookEdition);
        } else {
            model.addAttribute(MESSAGE_ATTRIBUTE, "Что то пошло не так :(");
        }
        return PAGE_NAME;
    }

    @GetMapping(value = "/addBookByUser/addBook")
    @ExceptionHandler({NumberFormatException.class, NullPointerException.class})
    public String addBook(
            @RequestParam(value = "addBook") String isbn,
            Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute(MESSAGE_ATTRIBUTE, "Доступ запрещен, пройдите авторизацию");
            return PAGE_NAME;
        }
        BookEdition bookEdition = bookEditionsService.getByIsbn(isbn);
        User user = userService.getUserByLogin(principal.getName());
        if (bookEdition == null || user == null) {
            model.addAttribute(MESSAGE_ATTRIBUTE, "Что то пошло не так:(");
            return PAGE_NAME;
        }
        if (bookCopiesService.addBook(new BookCopy(bookEdition, user))) {
            model.addAttribute(MESSAGE_ATTRIBUTE, "Книга успешно добавлена");
        } else {
            model.addAttribute(MESSAGE_ATTRIBUTE, "Что то пошло не так:(");
        }
        return PAGE_NAME;
    }

    @GetMapping(value = "/addBookByUser/sendRequest")
    public String sendRequest(
            @RequestParam(value = "bookName") String bookName,
            @RequestParam(value = "bookAuthor") String bookAuthor,
            @RequestParam(value = "bookIsbn") String bookIsbn,
            Model model) {
        model.addAttribute(MESSAGE_ATTRIBUTE, "Запрос отправлен, Вам придет уведомление на Ваш email");
        //TODO: add implement for email send
        return PAGE_NAME;
    }

    private List<BookEdition> searchBookByTypeValue(String typeValue) {
        if (typeValue == null) {
            return new ArrayList<>();
        }
        List<BookEdition> bookEditionList = bookEditionsService.getByName(typeValue);
        BookEdition bookEdition = bookEditionsService.getByIsbn(typeValue);
        //TODO добавить реализацию поиска книги по автору в сервис и DAO
        if (bookEdition != null) {
            bookEditionList.add(bookEdition);
        }
        return bookEditionList;
    }
}
