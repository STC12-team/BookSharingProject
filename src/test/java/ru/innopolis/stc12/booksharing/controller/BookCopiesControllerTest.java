package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
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

class BookCopiesControllerTest {
    @InjectMocks
    private BookCopiesController bookCopiesController;
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
    }

    @Test
    void showPage() {
        assertEquals("addBookByUser", bookCopiesController.showPage(model));
        verify(model, times(1)).addAttribute(anyString(), anyString());
    }

    @Test
    void searchBookWhenSearchValueIsNull() {
        assertEquals("addBookByUser", bookCopiesController.searchBook(null, model));
        verify(model, times(2)).addAttribute(anyString(), anyString());
    }

    @Test
    void searchBookWhenSearchValueIsNotNull() {
        when(bookEditionsService.getBookEditionsBySearchValue(anyString())).thenReturn(bookEditionList);
        when(bookEditionList.isEmpty()).thenReturn(false);
        assertEquals("addBookByUser", bookCopiesController.searchBook(anyString(), model));
        verify(bookEditionList, times(1)).isEmpty();
        verify(bookEditionsService, times(1)).getBookEditionsBySearchValue(anyString());
        verify(model, times(1)).addAttribute(anyString(), any());
        when(bookEditionsService.getByIsbn(anyString())).thenReturn(bookEdition);
        assertEquals("addBookByUser", bookCopiesController.searchBook("ISBN 978-3-642-11746-6", model));
    }

    @Test
    void chooseBookWhenBookNotFound() {
        when(bookEditionsService.getByIsbn(anyString())).thenReturn(null);
        assertEquals("addBookByUser", bookCopiesController.chooseBook(anyString(), model));
        verify(bookEditionsService, times(1)).getByIsbn(anyString());
        assertEquals("addBookByUser", bookCopiesController.chooseBook("ISBN 0-596-00681-0", model));
    }

    @Test
    void chooseBookWhenBookIsFound() {
        when(bookEditionsService.getByIsbn(anyString())).thenReturn(bookEdition);
        assertEquals("addBookByUser", bookCopiesController.chooseBook(anyString(), model));
        verify(bookEditionsService, times(1)).getByIsbn(anyString());
    }

    @Test
    void addBookWhenBookOrUserIsNull() {
        when(bookEditionsService.getByIsbn(anyString())).thenReturn(null);
        when(userService.getUserByLogin(anyString())).thenReturn(null);
        when(principal.getName()).thenReturn("name");
        assertEquals("addBookByUser", bookCopiesController.addBook(anyString(), model, principal));
        verify(bookEditionsService, times(1)).getByIsbn(anyString());
        verify(userService, times(1)).getUserByLogin(anyString());
    }

    @Test
    void addBookWhenPrincipalIsNull() {
        assertEquals("addBookByUser", bookCopiesController.addBook(anyString(), model, eq(null)));
        verify(model, times(1)).addAttribute(anyString(), any());
    }

    @Test
    void addBookWhenBookOrUserIsNotNull() {
        when(bookEditionsService.getByIsbn(anyString())).thenReturn(bookEdition);
        when(userService.getUserByLogin(anyString())).thenReturn(user);
        when(principal.getName()).thenReturn("name");
        assertEquals("addBookByUser", bookCopiesController.addBook(anyString(), model, principal));
        verify(bookEditionsService, times(1)).getByIsbn(anyString());
        verify(userService, times(1)).getUserByLogin(anyString());
        verify(model, times(1)).addAttribute(anyString(), any());
    }

    @Test
    void sendRequest() {
        assertEquals("addBookByUser", bookCopiesController.sendRequest("", "", "", model));
        verify(model, times(1)).addAttribute(anyString(), any());
    }
}