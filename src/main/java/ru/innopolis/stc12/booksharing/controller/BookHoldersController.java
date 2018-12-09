package ru.innopolis.stc12.booksharing.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookHolder;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookQueue;
import ru.innopolis.stc12.booksharing.model.pojo.*;
import ru.innopolis.stc12.booksharing.service.BookCopiesService;
import ru.innopolis.stc12.booksharing.service.BookHoldersService;
import ru.innopolis.stc12.booksharing.service.BookQueueService;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;

@Controller
public class BookHoldersController {
    private static final Logger LOGGER = Logger.getLogger(BookHoldersController.class);
    private BookHoldersService bookHoldersService;
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String TRANSFER_MESSAGE_ATTRIBUTE = "transfer_message";
    private static final String PAGE_NAME = "takenBooks";
    private BookCopiesService bookCopiesService;
    private BookQueueService bookQueueService;
    private MessageSource messageSource;

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

    @GetMapping("/takenBooks")
    String takenBooks(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute(MESSAGE_ATTRIBUTE,
                    messageSource.getMessage("model.errorBookHoldersControllerAccess", null, "", LocaleContextHolder.getLocale()));
            return PAGE_NAME;
        }
        String login = principal.getName();
        List<BookHolder> bookHolderList = bookHoldersService.getBookHoldersByUserLogin(login);
        model.addAttribute("takenBooksList", bookHolderList);
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
        model.addAttribute(MESSAGE_ATTRIBUTE, "Книга отмечена как прочитанная");
        List<BookQueue> bookQueueList = bookQueueService.getBookQueueByBookEditionId(bookCopy.getBookEdition().getId());
        if (bookQueueList.isEmpty()) {
            model.addAttribute(TRANSFER_MESSAGE_ATTRIBUTE,
                    messageSource.getMessage("model.errorBookHoldersControllerBookNoNeed", null, "", LocaleContextHolder.getLocale()));
            //TODO предложить вернуть владельцу
        } else {
            model.addAttribute(TRANSFER_MESSAGE_ATTRIBUTE,
                    messageSource.getMessage("model.errorBookHoldersControllerNextInQueue", null, "", LocaleContextHolder.getLocale()));
            BookQueue bookQueue = getFirstUserFromQueue(bookQueueList);
            bookQueue.setStatus(BookQueueStatus.GETTING);
            bookQueueService.updateBookQueue(bookQueue);
            model.addAttribute("user", bookQueue.getUser());
            //TODO отправить уведомление следующему читателю, о том, что он может взять книгу
        }
        return PAGE_NAME;
    }

    private BookQueue getFirstUserFromQueue(List<BookQueue> list) {
        if (list.size() == 1) {
            return list.get(0);
        }
        return list.stream().min(Comparator.comparing(BookQueue::getAddedAt)).get();
    }
}
