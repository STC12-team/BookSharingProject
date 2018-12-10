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
import ru.innopolis.stc12.booksharing.model.dao.entity.BookHolder;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookQueue;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopiesStatus;
import ru.innopolis.stc12.booksharing.model.pojo.BookQueueStatus;
import ru.innopolis.stc12.booksharing.service.BookCopiesService;
import ru.innopolis.stc12.booksharing.service.BookHoldersService;
import ru.innopolis.stc12.booksharing.service.BookQueueService;
import ru.innopolis.stc12.booksharing.service.UserService;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;

@Controller
public class BookHoldersController {
    private static final Logger LOGGER = Logger.getLogger(BookHoldersController.class);
    private BookHoldersService bookHoldersService;
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String TRANSFER_MESSAGE_ATTRIBUTE = "transfer_message";
    private static final String PAGE_NAME = "userBooks";
    private static final String BOOK_HOLDER_ATTRIBUTE = "bookHolder";
    private static final String BOOK_QUEUE_ATTRIBUTE = "bookQueue";
    private static final String BOOK_HOLDER_DESCRIPTION_PAGE = "bookHolderDescription";
    private static final String BOOK_QUEUE_DESCRIPTION_PAGE = "bookQueueDescription";
    private BookCopiesService bookCopiesService;
    private BookQueueService bookQueueService;
    private MessageSource messageSource;
    private UserService userService;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Autowired
    public void setBookHoldersService(BookHoldersService bookHoldersService) {
        this.bookHoldersService = bookHoldersService;
    }

    @Autowired
    public void setBookCopiesService(BookCopiesService bookCopiesService) {
        this.bookCopiesService = bookCopiesService;
    }

    @Autowired
    public void setBookQueueService(BookQueueService bookQueueService) {
        this.bookQueueService = bookQueueService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/takenBooks")
    String takenBooks(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute(MESSAGE_ATTRIBUTE,
                    messageSource.getMessage("model.errorBookHoldersControllerAccess", null, "", LocaleContextHolder.getLocale()));
            return PAGE_NAME;
        }
        String login = principal.getName();
        List<BookHolder> bookHolderList = userService.getBookHoldersByUserLogin(login);
        if (bookHolderList.isEmpty()) {
            model.addAttribute(MESSAGE_ATTRIBUTE, messageSource.getMessage("model.errorCatalogController", null, "", LocaleContextHolder.getLocale()));
        } else {
            model.addAttribute("takenBooksList", bookHolderList);
        }
        return PAGE_NAME;
    }

    @GetMapping("/takenBooks/readEnd")
    @ExceptionHandler(NumberFormatException.class)
    String readEnd(
            @RequestParam(value = "bookCopyId") String bookCopyId,
            Model model) {
        BookCopy bookCopy = bookCopiesService.getBookCopyById(Integer.valueOf(bookCopyId));
        if (bookCopy == null) {
            LOGGER.warn("Не удалось найти книгу с id - " + bookCopyId);
            model.addAttribute(MESSAGE_ATTRIBUTE,
                    messageSource.getMessage("model.errorBookHoldersControllerCannotGetBook", null, "", LocaleContextHolder.getLocale()));
            return PAGE_NAME;
        }
        bookCopy.setStatus(BookCopiesStatus.FREE);
        bookCopiesService.updateBookCopy(bookCopy);
        model.addAttribute(MESSAGE_ATTRIBUTE, messageSource.getMessage("model.messageBookMarkedAsRead", null, "", LocaleContextHolder.getLocale()));
        List<BookQueue> bookQueueList = bookQueueService.getBookQueueByBookEditionId(bookCopy.getBookEdition().getId());
        if (bookQueueList.isEmpty()) {
            model.addAttribute(TRANSFER_MESSAGE_ATTRIBUTE,
                    messageSource.getMessage("model.errorBookHoldersControllerBookNoNeed", null, "", LocaleContextHolder.getLocale()));
            //TODO предложить вернуть владельцу
        } else {
            model.addAttribute(TRANSFER_MESSAGE_ATTRIBUTE,
                    messageSource.getMessage("model.errorBookHoldersControllerNextInQueue", null, "", LocaleContextHolder.getLocale()));
            BookQueue bookQueue = getFirstUserFromQueue(bookQueueList);
            if (bookQueue != null) {
                bookQueue.setStatus(BookQueueStatus.GETTING);
                bookQueueService.updateBookQueue(bookQueue);
                model.addAttribute("user", bookQueue.getUser());
            } else {
                model.addAttribute("user", null);
            }

            //TODO отправить уведомление следующему читателю, о том, что он может взять книгу
        }
        return PAGE_NAME;
    }

    @GetMapping(value = "/bookHolderDesc/{id}")
    public String showBookEditionDescriptionPage(@PathVariable int id, Model model) {
        BookHolder bookHolder = bookHoldersService.getById(id);
        model.addAttribute(BOOK_HOLDER_ATTRIBUTE, bookHolder);
        return BOOK_HOLDER_DESCRIPTION_PAGE;
    }

    @GetMapping("/queueBooks")
    String queueBooks(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute(MESSAGE_ATTRIBUTE,
                    messageSource.getMessage("model.errorBookHoldersControllerAccess", null, "", LocaleContextHolder.getLocale()));
            return PAGE_NAME;
        }
        String login = principal.getName();
        List<BookQueue> bookQueueList = userService.getBookQueueByUserLogin(login);
        if (bookQueueList.isEmpty()) {
            model.addAttribute(MESSAGE_ATTRIBUTE, messageSource.getMessage("model.errorCatalogController", null, "", LocaleContextHolder.getLocale()));
        } else {
            model.addAttribute("bookQueueList", bookQueueList);
        }

        return PAGE_NAME;
    }

    @GetMapping(value = "/bookQueueDesc/{id}")
    public String showBookQueueDescriptionPage(@PathVariable int id, Model model) {
        BookQueue bookQueue = bookQueueService.getById(id);
        model.addAttribute(BOOK_QUEUE_ATTRIBUTE, bookQueue);
        return BOOK_QUEUE_DESCRIPTION_PAGE;
    }


    private BookQueue getFirstUserFromQueue(List<BookQueue> list) {
        if (list.size() == 1) {
            return list.get(0);
        }
        return list.stream().min(Comparator.comparing(BookQueue::getAddedAt)).orElse(null);
    }
}
