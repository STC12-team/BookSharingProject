package ru.innopolis.stc12.booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc12.booksharing.model.pojo.*;
import ru.innopolis.stc12.booksharing.service.BookCopiesService;
import ru.innopolis.stc12.booksharing.service.BookHoldersService;
import ru.innopolis.stc12.booksharing.service.BookQueueService;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;

@Controller
public class BookHoldersController {
    private BookHoldersService bookHoldersService;
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String TRANSFER_MESSAGE_ATTRIBUTE = "transfer_message";
    private static final String PAGE_NAME = "takenBooks";
    private BookCopiesService bookCopiesService;
    private BookQueueService bookQueueService;

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
    public String takenBooks(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute(MESSAGE_ATTRIBUTE, "У Вас нет прав на просмотр данной страницы!");
            return PAGE_NAME;
        }
        String login = principal.getName();
        List<BookHolder> bookHolderList = bookHoldersService.getBookHoldersByUserLogin(login);
        model.addAttribute("takenBooksList", bookHolderList);
        return PAGE_NAME;
    }

    @GetMapping("/takenBooks/readEnd")
    public String readEnd(
            @RequestParam(value = "bookCopyId") String bookCopyId,
            Model model) {
        BookCopy bookCopy = bookCopiesService.getBookCopyById(Integer.valueOf(bookCopyId));
        bookCopy.setStatus(BookCopiesStatus.FREE);
        if (bookCopiesService.updateBookCopy(bookCopy)) {
            model.addAttribute(MESSAGE_ATTRIBUTE, "Книга отмечена как прочитанная");
        } else {
            model.addAttribute(MESSAGE_ATTRIBUTE, "Что то пошло не так:(");
        }
        List<BookQueue> bookQueueList = bookQueueService.getBookQueueByBookEditionId(bookCopy.getBookEdition().getId());
        if (bookQueueList.isEmpty()) {
            model.addAttribute(TRANSFER_MESSAGE_ATTRIBUTE, "Эта книга ни кому не нужна...");
        } else {
            model.addAttribute(TRANSFER_MESSAGE_ATTRIBUTE, "Следующий на очереди:");
            BookQueue bookQueue = getFirstUserFromQueue(bookQueueList);
            bookQueue.setStatus(BookQueueStatus.GETTING);
            bookQueueService.updateBookQueue(bookQueue);
            model.addAttribute("user", bookQueue.getUser());
        }
        return PAGE_NAME;
    }

    private BookQueue getFirstUserFromQueue(List<BookQueue> list) {
        if (list.size() <= 1) {
            return list.get(0);
        }
        return list.stream().min(Comparator.comparing(BookQueue::getAddedAt)).get();
    }
}
