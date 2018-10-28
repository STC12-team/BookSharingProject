package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;
import ru.innopolis.stc12.booksharing.service.BookEditionsService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class BookEditionAddByUserControllerTest {
    private BookAddByUserController bookAddByUserController;
    @Mock
    private Model model;
    @Mock
    private BookEditionsService bookEditionsService;
    @Mock
    private List<BookEdition> bookEditionList;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookAddByUserController = new BookAddByUserController();
        bookAddByUserController.setBookEditionsService(bookEditionsService);
    }

    @Test
    void showPage() {
        when(model.addAttribute(any())).thenReturn(model);
        assertEquals("addBookByUser", bookAddByUserController.showPage(model));
        verify(model, times(1)).addAttribute(anyString(), anyString());
    }

    @Test
    void searchBookWhenSearchValueIsNull() {
        when(model.addAttribute(any())).thenReturn(model);
        assertEquals("addBookByUser", bookAddByUserController.searchBook(null, model));
        verify(model, times(2)).addAttribute(anyString(), anyString());
    }

    @Test
    void searchBookWhenSearchValueIsName() {
/*        when(model.addAttribute(any())).thenReturn(model);
        when(bookEditionList.isEmpty()).thenReturn(false);
        when(bookEditionsService.getByName(anyString())).thenReturn(bookEditionList);
        assertEquals("addBookByUser", bookAddByUserController.searchBook(anyString(), model));
        verify(bookEditionList, times(2)).isEmpty();
        verify(bookEditionsService, times(1)).getByName(anyString());
        verify(model, times(1)).addAttribute(anyString(), any());*/
    }

    @Test
    void searchBookWhenSearchValueIsAuthor() {
/*
        when(model.addAttribute(any())).thenReturn(model);
        when(bookEditionList.isEmpty()).thenReturn(true).thenReturn(false);
        when(bookEditionsService.getByName(anyString())).thenReturn(bookEditionList);
        assertEquals("addBookByUser", bookAddByUserController.searchBook(anyString(), model));
        verify(bookEditionList, times(3)).isEmpty();
        verify(bookEditionsService, times(1)).getByName(anyString());
        verify(model, times(1)).addAttribute(anyString(), any());
*/
    }

    @Test
    void searchBookWhenSearchValueIsIsbn() {
/*
        when(model.addAttribute(any())).thenReturn(model);
        when(bookEditionList.isEmpty()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(booksService.getByName(anyString())).thenReturn(bookEditionList);
        when(booksService.getByAuthor(anyString())).thenReturn(bookEditionList);
        when(booksService.getByIsbn(anyInt())).thenReturn(new BookEdition());
        assertEquals("addBookByUser", bookAddByUserController.searchBook(anyString(), model));
        verify(bookEditionList, times(3)).isEmpty();
        verify(booksService, times(1)).getByName(anyString());
        verify(booksService, times(1)).getByAuthor(anyString());
        verify(booksService, times(1)).getByIsbn(anyInt());
        verify(model, times(1)).addAttribute(anyString(), any());
*/
    }

    @Test
    void chooseBook() {
    }

    @Test
    void addBook() {
    }

    @Test
    void sendRequest() {
    }
}