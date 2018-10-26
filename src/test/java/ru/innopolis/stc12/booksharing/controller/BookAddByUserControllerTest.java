package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.entitys.Book;
import ru.innopolis.stc12.booksharing.services.BooksService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class BookAddByUserControllerTest {
    private BookAddByUserController bookAddByUserController;
    @Mock
    private Model model;
    @Mock
    private BooksService booksService;
    @Mock
    private List<Book> bookList;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookAddByUserController = new BookAddByUserController();
        bookAddByUserController.setBooksService(booksService);
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
        when(model.addAttribute(any())).thenReturn(model);
        when(bookList.isEmpty()).thenReturn(false);
        when(booksService.getByName(anyString())).thenReturn(bookList);
        assertEquals("addBookByUser", bookAddByUserController.searchBook(anyString(), model));
        verify(bookList, times(2)).isEmpty();
        verify(booksService, times(1)).getByName(anyString());
        verify(model, times(1)).addAttribute(anyString(), any());
    }

    @Test
    void searchBookWhenSearchValueIsAuthor() {
        when(model.addAttribute(any())).thenReturn(model);
        when(bookList.isEmpty()).thenReturn(true).thenReturn(false);
        when(booksService.getByName(anyString())).thenReturn(bookList);
        when(booksService.getByAuthor(anyString())).thenReturn(bookList);
        assertEquals("addBookByUser", bookAddByUserController.searchBook(anyString(), model));
        verify(bookList, times(3)).isEmpty();
        verify(booksService, times(1)).getByName(anyString());
        verify(booksService, times(1)).getByAuthor(anyString());
        verify(model, times(1)).addAttribute(anyString(), any());
    }

    @Test
    void searchBookWhenSearchValueIsIsbn() {
/*
        when(model.addAttribute(any())).thenReturn(model);
        when(bookList.isEmpty()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(booksService.getByName(anyString())).thenReturn(bookList);
        when(booksService.getByAuthor(anyString())).thenReturn(bookList);
        when(booksService.getByIsbn(anyInt())).thenReturn(new Book());
        assertEquals("addBookByUser", bookAddByUserController.searchBook(anyString(), model));
        verify(bookList, times(3)).isEmpty();
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