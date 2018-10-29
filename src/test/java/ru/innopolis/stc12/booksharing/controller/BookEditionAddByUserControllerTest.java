package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;
import ru.innopolis.stc12.booksharing.model.pojo.User;
import ru.innopolis.stc12.booksharing.service.BookCopiesService;
import ru.innopolis.stc12.booksharing.service.BookEditionsService;
import ru.innopolis.stc12.booksharing.service.UserService;

import java.security.Principal;
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
    @Mock
    private BookEdition bookEdition;
    @Mock
    private UserService userService;
    @Mock
    private User user;
    @Mock
    private Principal principal;
    @Mock
    private BookCopiesService bookCopiesService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookAddByUserController = new BookAddByUserController();
        bookAddByUserController.setBookEditionsService(bookEditionsService);
        bookAddByUserController.setUsersService(userService);
        bookAddByUserController.setBookCopiesService(bookCopiesService);
    }

    @Test
    void showPage() {
        assertEquals("addBookByUser", bookAddByUserController.showPage(model));
        verify(model, times(1)).addAttribute(anyString(), anyString());
    }

    @Test
    void searchBookWhenSearchValueIsNull() {
        assertEquals("addBookByUser", bookAddByUserController.searchBook(null, model));
        verify(model, times(2)).addAttribute(anyString(), anyString());
    }

    @Test
    void searchBookWhenSearchValueIsNotNull() {
        when(bookEditionsService.getByName(anyString())).thenReturn(bookEditionList);
        when(bookEditionsService.getByIsbn(anyString())).thenReturn(bookEdition);
        when(bookEditionList.isEmpty()).thenReturn(false);
        assertEquals("addBookByUser", bookAddByUserController.searchBook(anyString(), model));
        verify(bookEditionList, times(1)).isEmpty();
        verify(bookEditionsService, times(1)).getByName(anyString());
        verify(bookEditionsService, times(1)).getByIsbn(anyString());
        verify(model, times(1)).addAttribute(anyString(), any());
    }

    @Test
    void chooseBookWhenBookNotFound() {
        when(bookEditionsService.getByIsbn(anyString())).thenReturn(null);
        assertEquals("addBookByUser", bookAddByUserController.chooseBook(anyString(), model));
        verify(bookEditionsService, times(1)).getByIsbn(anyString());
    }

    @Test
    void chooseBookWhenBookIsFound() {
        when(bookEditionsService.getByIsbn(anyString())).thenReturn(bookEdition);
        assertEquals("addBookByUser", bookAddByUserController.chooseBook(anyString(), model));
        verify(bookEditionsService, times(1)).getByIsbn(anyString());
    }

    @Test
    void addBookWhenBookOrUserIsNull() {
        when(bookEditionsService.getByIsbn(anyString())).thenReturn(null);
        when(userService.getUserByLogin(anyString())).thenReturn(null);
        when(principal.getName()).thenReturn("name");
        assertEquals("addBookByUser", bookAddByUserController.addBook(anyString(), model, principal));
        verify(bookEditionsService, times(1)).getByIsbn(anyString());
        verify(userService, times(1)).getUserByLogin(anyString());
    }

    @Test
    void addBookWhenPrincipalIsNull() {
        assertEquals("addBookByUser", bookAddByUserController.addBook(anyString(), model, eq(null)));
        verify(model, times(1)).addAttribute(anyString(), any());
    }

    @Test
    void addBookWhenBookOrUserIsNotNull() {
        when(bookEditionsService.getByIsbn(anyString())).thenReturn(bookEdition);
        when(userService.getUserByLogin(anyString())).thenReturn(user);
        when(principal.getName()).thenReturn("name");
        when(bookCopiesService.addBook(any())).thenReturn(true);
        assertEquals("addBookByUser", bookAddByUserController.addBook(anyString(), model, principal));
        verify(bookEditionsService, times(1)).getByIsbn(anyString());
        verify(userService, times(1)).getUserByLogin(anyString());
        verify(model, times(1)).addAttribute(anyString(), any());
    }

    @Test
    void sendRequest() {
        assertEquals("addBookByUser", bookAddByUserController.sendRequest("", "", "", model));
        verify(model, times(1)).addAttribute(anyString(), any());
    }
}