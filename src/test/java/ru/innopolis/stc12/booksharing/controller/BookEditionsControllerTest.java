package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.service.BookEditionsService;
import ru.innopolis.stc12.booksharing.service.PublisherService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookEditionsControllerTest {
    @InjectMocks
    private BookEditionsController bookEditionsController;


    @Mock
    private Model model;

    @Mock
    private BookEditionsService bookEditionsService;

    @Mock
    private PublisherService publisherService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookEditionsController = new BookEditionsController();
        bookEditionsController.setBookEditionsService(bookEditionsService);
        bookEditionsController.setPublisherService(publisherService);
    }

    @Test
    void getBookEditionsPage() {
        when(model.addAttribute(any(), any())).thenReturn(model);
        assertEquals("bookEditions", bookEditionsController.getBookEditionsPage(model));
    }


    @Test
    void showAddBookEdition() {
        when(model.addAttribute(any(), any())).thenReturn(model);
        assertEquals("addBookEdition", bookEditionsController.showAddBookEdition(model));
    }

    @Test
    void addBookEdition() {
        when(bookEditionsService.addBookEdition(any())).thenReturn(true);
        assertEquals(
                "addBookEdition",
                bookEditionsController.addBookEdition(
                        "title", "desc", "Amazon", "isbn", model
                )
        );
    }
}