package ru.innopolis.stc12.booksharing.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.stc12.booksharing.exceptions.ControllerException;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.service.*;

import java.security.Principal;
import java.util.List;

@Controller
public class BookEditionsController {

    private BookEditionsService bookEditionsService;
    private PublisherService publisherService;
    private BookCopiesService bookCopiesService;
    private BookQueueService bookQueueService;
    private UserService userService;
    private static final String BOOK_EDITION_DESCRIPTION_PAGE = "bookEditionDescription";
    private static final String BOOK_EDITION_ATTRIBUTE = "bookEdition";

    @Autowired
    public void setUsersService(UserService userService) {
        this.userService = userService;
    }

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
            @RequestParam(value = "bookEditionYear") int yearOfPublication,
            Model model)
    {
        //TODO надо проверить издателя
        Publisher publisher = publisherService.getByName(publisherName);
        BookEdition bookEdition = new BookEdition(title, description, isbn, publisher, yearOfPublication);
        bookEditionsService.addBookEdition(bookEdition);
        return "addBookEdition";
    }

    @GetMapping(value = "/bookEditionDesc/{id}")
    @Transactional
    public String showBookEditionDescriptionPage(@PathVariable int id, Model model) {
        BookEdition bookEdition = bookEditionsService.getById(id);

        int countBookCopy = bookCopiesService.getBookCopyCountByBookEditionId(id);
        int countBookCopyInStatusFree = bookCopiesService.getBookCopyCountByBookEditionIdInStatusFree(id);
        int countUserInQueue = bookQueueService.getBookQueueCountByBookEditionId(id);
        int userPlaceInQueue = bookEditionsService.getUserPlaceInQueue();

        model.addAttribute(BOOK_EDITION_ATTRIBUTE, bookEdition);
        model.addAttribute("countBookCopy", countBookCopy);
        model.addAttribute("countBookCopyInStatusFree", countBookCopyInStatusFree);
        model.addAttribute("userCountInQueue", countUserInQueue);
        model.addAttribute("userPlaceInQueue", userPlaceInQueue);

        //TODO need added using on view
        List<BookCopy> bookCopyList = bookEditionsService.getBookCopiesByBookEditionIdInStatusFree(id);
        model.addAttribute("bookCopyListInStatusFree", bookCopyList);

        return BOOK_EDITION_DESCRIPTION_PAGE;
    }

    @GetMapping(value = "/bookEditionDesc/{id}/getOutOfQueue")
    public String getOutOfQueue(@PathVariable int id, Model model, Principal principal) {
        BookEdition bookEdition = bookEditionsService.getById(id);
        User user = userService.getUserByLogin(principal.getName());
        bookQueueService.deleteUserFromQueue(user, bookEdition);
        model.addAttribute(BOOK_EDITION_ATTRIBUTE, bookEdition);
        return BOOK_EDITION_DESCRIPTION_PAGE;
    }

    @GetMapping(value = "/bookEditionDesc/{id}/getInQueue")
    public String getInQueue(@PathVariable int id, Model model, Principal principal) {
        BookEdition bookEdition = bookEditionsService.getById(id);
        User user = userService.getUserByLogin(principal.getName());
        bookQueueService.addUserToBookQueue(user, bookEdition);
        model.addAttribute(BOOK_EDITION_ATTRIBUTE, bookEdition);
        return BOOK_EDITION_DESCRIPTION_PAGE;
    }
}
