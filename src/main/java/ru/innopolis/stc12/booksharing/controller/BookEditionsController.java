package ru.innopolis.stc12.booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.stc12.booksharing.exceptions.ControllerException;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;
import ru.innopolis.stc12.booksharing.service.BookCopiesService;
import ru.innopolis.stc12.booksharing.service.BookEditionsService;
import ru.innopolis.stc12.booksharing.service.BookQueueService;
import ru.innopolis.stc12.booksharing.service.PublisherService;

@Controller
public class BookEditionsController {
    private BookEditionsService bookEditionsService;
    private PublisherService publisherService;
    private BookCopiesService bookCopiesService;
    private BookQueueService bookQueueService;

    @Autowired
    public void setBookEditionsService(BookEditionsService bookEditionsService) {
        this.bookEditionsService = bookEditionsService;
    }

    @Autowired
    public void setPublisherService(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @Autowired
    public void setBookCopiesService(BookCopiesService bookCopiesService) {
        this.bookCopiesService = bookCopiesService;
    }

    @Autowired
    public void setBookQueueService(BookQueueService bookQueueService) {
        this.bookQueueService = bookQueueService;
    }

    @GetMapping(value = "/bookEditions")
    @ExceptionHandler(ControllerException.class)
    public String getBookEditionsPage(Model model) {
        model.addAttribute("bookEditions", bookEditionsService.getAllBookEditions());
        return "bookEditions";
    }

    @GetMapping(value = "/addBookEdition")
    public String showAddBookEdition(Model model) {
        return "addBookEdition";
    }

    //TODO реализовать согласно предложенной таблицы БД.
    @PostMapping(value = "/addBookEditionUrl")
    public String addBookEdition(
            @RequestParam(value = "bookEditionTitle") String title,
            @RequestParam(value = "bookEditionDescription") String description,
            @RequestParam(value = "bookEditionPublisher") String publisherName,
            @RequestParam(value = "bookEditionIsbn") String isbn,
            Model model
    ) {
        Publisher publisher = publisherService.getByNameOrCreate(publisherName);
        BookEdition bookEdition = new BookEdition(title, description, isbn, publisher, 2018);
        bookEditionsService.addBookEdition(bookEdition);
        return "addBookEdition";
    }

    @GetMapping(value = "/bookEditionDesc/{id}")
    public String showBookEditionDescriptionPage(@PathVariable Integer id, Model model) {
        BookEdition bookEdition = bookEditionsService.getById(id);

        int countBookCopy = bookCopiesService.getBookCopyCountByBookEditionId(id);
        int countBookCopyInStatusFree = bookCopiesService.getBookCopyCountByBookEditionIdInStatusFree(id);
        int countUserInQueue = bookQueueService.getBookQueueCountByBookEditionId(id);

        model.addAttribute("bookEdition", bookEdition);
        model.addAttribute("countBookCopy", countBookCopy);
        model.addAttribute("countBookCopyInStatusFree", countBookCopyInStatusFree);
        model.addAttribute("userCountInQueue", countUserInQueue);
        return "bookEditionDescription";
    }
}
