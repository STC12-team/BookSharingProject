package ru.innopolis.stc12.booksharing.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopiesStatus;
import ru.innopolis.stc12.booksharing.service.BookCopiesService;
import ru.innopolis.stc12.booksharing.service.BookEditionsService;
import ru.innopolis.stc12.booksharing.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class BookCopiesController {
    private Logger logger = Logger.getLogger(BookCopiesController.class);
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String PAGE_NAME = "addBookByUser";
    private static final String MY_BOOKS_PAGE_NAME = "userBooks";
    private static final String BOOK_COPY_ATTRIBUTE = "bookCopy";
    private static final String BOOK_COPY_DESCRIPTION_PAGE = "bookCopyDescription";
    private BookEditionsService bookEditionsService;
    private UserService userService;
    private BookCopiesService bookCopiesService;
    private MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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
        List<BookEdition> bookEditionList = bookEditionsService.getBookEditionsBySearchValue(searchValue);
        if (!bookEditionList.isEmpty()) {
            model.addAttribute("bookEditionList", bookEditionList);
        } else {
            logger.info("Книга не найдена");
            model.addAttribute(MESSAGE_ATTRIBUTE,
                    messageSource.getMessage("model.errorBookCopiesControllerNotFound", null, "", LocaleContextHolder.getLocale()));
            model.addAttribute("showSendRequestForm", "true");
        }
        return PAGE_NAME;
    }

    @GetMapping(value = "/addBookByUser/chooseBook")
    @ExceptionHandler(NumberFormatException.class)
    public String chooseBook(
            @RequestParam(value = "chooseBook") String isbn,
            Model model) {
        BookEdition bookEdition = bookEditionsService.getBookEditionByIsbn(isbn);
        if (bookEdition != null) {
            model.addAttribute("bookEdition", bookEdition);
        } else {
            model.addAttribute(MESSAGE_ATTRIBUTE,
                    messageSource.getMessage("model.errorBookCopiesControllerSomethingWrong", null, "", LocaleContextHolder.getLocale()));
        }
        return PAGE_NAME;
    }

    @GetMapping(value = "/addBookByUser/addBook")
    @ExceptionHandler({NumberFormatException.class, NullPointerException.class})
    public String addBook(
            @RequestParam(value = "addBook") String isbn,
            Model model,
            Principal principal) {
        if (principal == null) {
            model.addAttribute(MESSAGE_ATTRIBUTE,
                    messageSource.getMessage("model.errorBookCopiesControllerAccessDenied", null, "", LocaleContextHolder.getLocale()));
            return PAGE_NAME;
        }
        BookEdition bookEdition = bookEditionsService.getBookEditionByIsbn(isbn);
        User user = userService.getUserByLogin(principal.getName());
        if (bookEdition == null || user == null) {
            model.addAttribute(MESSAGE_ATTRIBUTE,
                    messageSource.getMessage("model.errorBookCopiesControllerSomethingWrong", null, "", LocaleContextHolder.getLocale()));
            return PAGE_NAME;
        }

        bookCopiesService.addBook(new BookCopy(bookEdition, user, BookCopiesStatus.FREE));
        model.addAttribute(MESSAGE_ATTRIBUTE,
                messageSource.getMessage("model.messageBookCopiesControllerSuccessfullyAdded", null, "", LocaleContextHolder.getLocale()));
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

    @GetMapping(value = "/myBooks")
    public String getMyBooks(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute(MESSAGE_ATTRIBUTE,
                    messageSource.getMessage("model.errorBookHoldersControllerAccess", null, "", LocaleContextHolder.getLocale()));
            return MY_BOOKS_PAGE_NAME;
        }
        String login = principal.getName();
        List<BookCopy> bookCopyList = userService.getBookCopiesByUserLogin(login);
        if (bookCopyList.isEmpty()) {
            model.addAttribute(MESSAGE_ATTRIBUTE, messageSource.getMessage("model.errorCatalogController", null, "", LocaleContextHolder.getLocale()));
        } else {
            model.addAttribute("bookCopyList", bookCopyList);
        }

        return MY_BOOKS_PAGE_NAME;
    }

    @GetMapping(value = "/bookCopyDesc/{id}")
    public String showBookEditionDescriptionPage(@PathVariable int id, Model model) {
        BookCopy bookCopy = bookCopiesService.getBookCopyById(id);
        model.addAttribute(BOOK_COPY_ATTRIBUTE, bookCopy);
        return BOOK_COPY_DESCRIPTION_PAGE;
    }
}