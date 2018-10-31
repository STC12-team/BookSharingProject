package ru.innopolis.stc12.booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.innopolis.stc12.booksharing.service.BookHoldersService;

@Controller
public class BookHoldersController {
    private BookHoldersService bookHoldersService;

    @Autowired
    public void setBookHoldersService(BookHoldersService bookHoldersService) {
        this.bookHoldersService = bookHoldersService;
    }


}
