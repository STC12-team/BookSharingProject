package ru.innopolis.stc12.booksharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryController {
    @GetMapping("/library")
    public String getLibraryPage(Model model) {
        return "/library";
    }
}
