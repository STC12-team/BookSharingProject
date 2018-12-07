package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.model.dao.entity.*;
import ru.innopolis.stc12.booksharing.service.BookCopiesService;
import ru.innopolis.stc12.booksharing.service.BookHoldersService;
import ru.innopolis.stc12.booksharing.service.BookQueueService;

import java.security.Principal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class BookHoldersControllerTest {
    @InjectMocks
    private BookHoldersController bookHoldersController;
    @Mock
    private Model model;
    @Mock
    private Principal principal;
    @Mock
    private BookHoldersService bookHoldersService;
    @Mock
    private BookCopiesService bookCopiesService;
    @Mock
    private BookQueueService bookQueueService;
    @Mock
    private List<BookHolder> bookHoldersList;
    @Mock
    private List<BookQueue> bookQueueList;
    @Mock
    private BookCopy bookCopy;
    @Mock
    private BookEdition bookEdition;
    @Mock
    private BookQueue bookQueue;
    @Mock
    private User user;
    @Mock
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        initMocks(this);
        when(model.addAttribute(anyString(), anyString())).thenReturn(model);
    }

    @Test
    void takenBooksWhenPrincipalIsNull() {
        when(messageSource.getMessage(anyString(), any(), anyString(), any())).thenReturn("У Вас нет прав на просмотр данной страницы!");
        assertEquals("takenBooks", bookHoldersController.takenBooks(model, null));
        //TODO не понимает кириллицу
        verify(model, times(1)).addAttribute("message", "У Вас нет прав на просмотр данной страницы!");
    }

    @Test
    void takenBooksWhenAllOk() {
        when(principal.getName()).thenReturn("user");
        when(bookHoldersService.getBookHoldersByUserLogin("user")).thenReturn(bookHoldersList);
        assertEquals("takenBooks", bookHoldersController.takenBooks(model, principal));
        verify(model, times(1)).addAttribute("takenBooksList", bookHoldersList);
    }

    @Test
    void readEndWhenBookCopyIdIsIncorrect() {
        when(messageSource.getMessage(anyString(), any(), anyString(), any())).thenReturn("Не удалось найти книгу, попробуйте позднее.");
        when(bookCopiesService.getBookCopyById(1)).thenReturn(null);
        assertEquals("takenBooks", bookHoldersController.readEnd("1", model));
        verify(model, times(1)).addAttribute("message", "Не удалось найти книгу, попробуйте позднее.");
    }

    @Test
    void readEndWhenBookQueueListIsEmpty() {
        when(messageSource.getMessage(anyString(), any(), anyString(), any())).thenReturn("Книга отмечена как прочитанная");
        when(messageSource.getMessage(anyString(), any(), anyString(), any())).thenReturn("Эта книга ни кому не нужна...");
        when(bookCopiesService.getBookCopyById(anyInt())).thenReturn(bookCopy);
        when(bookQueueService.getBookQueueByBookEditionId(1)).thenReturn(bookQueueList);
        when(bookQueueList.isEmpty()).thenReturn(true);
        when(bookCopy.getBookEdition()).thenReturn(bookEdition);
        when(bookEdition.getId()).thenReturn(1);
        assertEquals("takenBooks", bookHoldersController.readEnd("1", model));
        verify(model, times(1)).addAttribute("message", "Книга отмечена как прочитанная");
        verify(model, times(1)).addAttribute("transfer_message", "Эта книга ни кому не нужна...");
    }

    @Test
    void readEndWhenBookQueueListIsSet() {
        when(messageSource.getMessage(anyString(), any(), anyString(), any())).thenReturn("Книга отмечена как прочитанная");
        when(messageSource.getMessage(anyString(), any(), anyString(), any())).thenReturn("Следующий на очереди:");
        when(bookCopiesService.getBookCopyById(anyInt())).thenReturn(bookCopy);
        when(bookQueueService.getBookQueueByBookEditionId(1)).thenReturn(bookQueueList);
        when(bookQueueList.isEmpty()).thenReturn(false);
        when(bookCopy.getBookEdition()).thenReturn(bookEdition);
        when(bookEdition.getId()).thenReturn(1);
        when(bookQueueList.size()).thenReturn(1);
        when(bookQueueList.get(0)).thenReturn(bookQueue);
        when(bookQueue.getUser()).thenReturn(user);
        assertEquals("takenBooks", bookHoldersController.readEnd("1", model));
        verify(model, times(1)).addAttribute("message", "Книга отмечена как прочитанная");
        verify(model, times(1)).addAttribute("transfer_message", "Следующий на очереди:");
        verify(model, times(1)).addAttribute("user", user);
    }
}