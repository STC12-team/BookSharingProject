package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.service.BookCopiesService;
import ru.innopolis.stc12.booksharing.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CatalogControllerTest {

    @InjectMocks
    private CatalogController catalogController;
    @Mock
    private BookCopiesService bookCopiesService;
    @Mock
    private UserService userService;
    @Mock
    private User user;
    @Mock
    List<BookCopy> bookCopies;
    @Mock
    private Model model;
    @Mock
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        initMocks(this);
        when(model.addAttribute(anyString(), anyString())).thenReturn(model);
    }

    @Test
    void contextLoads() {
        assertNotNull(catalogController);
    }

    @Test
    void getCatalogPageWithoutBookCopies() {
        when(userService.getAuthenticatedUserDetails()).thenReturn(user);
        when(bookCopiesService.getBookCopiesByUser(user)).thenReturn(bookCopies);
        when(bookCopies.isEmpty()).thenReturn(false);
        assertEquals("catalog", catalogController.showCatalogPage(model));
        verify(model, times(1)).addAttribute("bookCopies", bookCopies);
    }

    @Test
    void getCatalogPageWithBookCopies() {
        when(messageSource.getMessage(anyString(), any(), anyString(), any())).thenReturn("У этого пользователя нет книжных экземпляров");
        when(userService.getAuthenticatedUserDetails()).thenReturn(user);
        when(bookCopiesService.getBookCopiesByUser(user)).thenReturn(bookCopies);
        when(bookCopies.isEmpty()).thenReturn(true);
        assertEquals("catalog", catalogController.showCatalogPage(model));
        verify(model, times(1)).addAttribute("errorMessage", "У этого пользователя нет книжных экземпляров");
    }

}
