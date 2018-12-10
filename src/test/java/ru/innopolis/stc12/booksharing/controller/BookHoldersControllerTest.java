package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.model.dao.entity.*;
import ru.innopolis.stc12.booksharing.service.BookCopiesService;
import ru.innopolis.stc12.booksharing.service.BookHoldersService;
import ru.innopolis.stc12.booksharing.service.BookQueueService;
import ru.innopolis.stc12.booksharing.service.UserService;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    private BookHolder bookHolder;
    @Mock
    private User user;
    @Mock
    private MessageSource messageSource;
    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        when(model.addAttribute(anyString(), anyString())).thenReturn(model);
    }

    @Test
    void takenBooksWhenPrincipalIsNull() {
        when(messageSource.getMessage(anyString(), any(), anyString(), any())).thenReturn("У Вас нет прав на просмотр данной страницы!");
        assertEquals("userBooks", bookHoldersController.takenBooks(model, null));
        verify(model, times(1)).addAttribute("message", "У Вас нет прав на просмотр данной страницы!");
    }

    @Test
    void takenBooksWhenAllOk() {
        when(principal.getName()).thenReturn("user");
        when(bookHoldersService.getBookHoldersByUserLogin("user")).thenReturn(bookHoldersList);
        assertEquals("userBooks", bookHoldersController.takenBooks(model, principal));
    }

    @Test
    void readEndWhenBookCopyIdIsIncorrect() {
        when(messageSource.getMessage(anyString(), any(), anyString(), any())).thenReturn("Не удалось найти книгу, попробуйте позднее.");
        when(bookCopiesService.getBookCopyById(1)).thenReturn(null);
        assertEquals("userBooks", bookHoldersController.readEnd("1", model));
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
        assertEquals("userBooks", bookHoldersController.readEnd("1", model));
        //verify(model, times(1)).addAttribute("message", "Книга отмечена как прочитанная");
        verify(model, times(1)).addAttribute("transfer_message", "Эта книга ни кому не нужна...");
    }

    @Test
    void readEndWhenBookQueueListIsSetOneValue() {
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
        assertEquals("userBooks", bookHoldersController.readEnd("1", model));
        //verify(model, times(1)).addAttribute("message", "Книга отмечена как прочитанная");
        verify(model, times(1)).addAttribute("transfer_message", "Следующий на очереди:");
        verify(model, times(1)).addAttribute("user", user);
    }

    @Test
    void readEndWhenBookQueueListIsSetTwoNullValue() {
        List<BookQueue> bookQueueTestList = new ArrayList<>();
        Model modelTest = new ExtendedModelMap();

        User user = new User("login", "password", null, 1, "email");
        BookQueue bookQueue1 = new BookQueue(null, user, new Timestamp(System.currentTimeMillis()), null);
        BookQueue bookQueue2 = new BookQueue(null, null, new Timestamp(System.currentTimeMillis() + 1000), null);
        bookQueueTestList.add(bookQueue2);
        bookQueueTestList.add(bookQueue1);

        when(bookCopiesService.getBookCopyById(12)).thenReturn(bookCopy);
        when(bookQueueService.getBookQueueByBookEditionId(111)).thenReturn(bookQueueTestList);
        when(bookCopy.getBookEdition()).thenReturn(bookEdition);
        when(bookEdition.getId()).thenReturn(111);
        assertEquals("userBooks", bookHoldersController.readEnd("12", modelTest));
        assertEquals(modelTest.asMap().get("user"), user);
    }

    @Test
    void showBookEditionDescriptionPage() {
        when(bookHoldersService.getById(1)).thenReturn(new BookHolder());
        assertEquals("bookHolderDescription", bookHoldersController.showBookEditionDescriptionPage(1, model));
    }

    @Test
    void queueBooks() {
        List<BookQueue> bookQueueList = new ArrayList<>();
        bookQueueList.add(new BookQueue());
        when(principal.getName()).thenReturn("name");
        when(userService.getBookQueueByUserLogin("user")).thenReturn(bookQueueList);
        assertEquals("userBooks", bookHoldersController.queueBooks(model, principal));
    }

    @Test
    void showBookQueueDescriptionPage() {
        when(bookQueueService.getById(1)).thenReturn(new BookQueue());
        assertEquals("bookQueueDescription", bookHoldersController.showBookQueueDescriptionPage(1, model));
    }

    @Test
    void shareBook() {
        when(bookHoldersService.getById(1)).thenReturn(bookHolder);
        when(bookHoldersService.update(bookHolder)).thenReturn(bookHolder);
        assertEquals("userBooks", bookHoldersController.shareBook("1", model));
    }
}