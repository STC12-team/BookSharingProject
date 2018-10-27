package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.service.BookEditionsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class BookEditionsControllerTest {
    @InjectMocks
    private BookEditionsController bookEditionsController;


    @Mock
    private Model model;

    @Mock
    private BookEditionsService bookEditionsService;

    @BeforeEach
    void setUp() {
        initMocks(this);

        bookEditionsController = new BookEditionsController();
        bookEditionsService = mock(BookEditionsService.class);
    }

    @Test
    void getBookEditionsPage() {
        when(model.addAttribute(any(), any())).thenReturn(model);
        bookEditionsController.setBookEditionsService(bookEditionsService);
        assertEquals("book_editions", bookEditionsController.getBookEditionsPage(model));
    }


    @Test
    void showAddBookEdition() {
        when(model.addAttribute(any(), any())).thenReturn(model);
        assertEquals("addBookEdition", bookEditionsController.showAddBookEdition(model));
    }

    @Test
    void addBookEdition() {
        doNothing().when(bookEditionsService).addBookEdition(isA(String.class), isA(String.class), isA(String.class));
        bookEditionsController.setBookEditionsService(bookEditionsService);
        assertEquals("addBookEdition", bookEditionsController.addBookEdition(anyString(), anyString(), anyString(), model));
    }
}