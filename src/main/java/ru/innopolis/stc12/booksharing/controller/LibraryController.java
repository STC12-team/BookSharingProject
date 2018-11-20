package ru.innopolis.stc12.booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;
import ru.innopolis.stc12.booksharing.service.BookEditionsService;

import java.util.List;

@Controller
public class LibraryController {
    private BookEditionsService bookEditionsService;

    @Autowired
    public void setBookEditionsService(BookEditionsService bookEditionsService) {
        this.bookEditionsService = bookEditionsService;
    }

    /**
     * Returns a page with BookEditions list
     *
     * @param model Spring interface model
     * @return the page with name "library"
     */
    @GetMapping("/library")
    public String getLibraryPage(
            @RequestParam(value = "searchValue", required = false) String searchValue,
            Model model) {
        //TODO предусмотреть вывод только части книг, разделить на страницы
        List<BookEdition> bookEditionList;
        if (null == (searchValue)) {
            bookEditionList = bookEditionsService.getBookEditionsByPublisher("sdf");
        } else {
            bookEditionList = bookEditionsService.getBookEditionsBySearchValue(searchValue);
        }
        model.addAttribute("bookEditionList", bookEditionList);
        return "/library";
    }

    @GetMapping("/")
    public String getWelcomePage() {
        return "/library";
    }
}
