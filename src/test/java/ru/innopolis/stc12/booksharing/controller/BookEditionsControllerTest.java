package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.service.*;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
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
    @Mock
    private BookEdition bookEdition;
    @Mock
    private Publisher publisher;
    @Mock
    private BookCopiesService bookCopiesService;
    @Mock
    private BookQueueService bookQueueService;
    @Mock
    private UserService userService;
    @Mock
    private Principal principal;

    @BeforeEach
    void setUp() {
        initMocks(this);
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
        when(publisherService.getByNameOrCreate("Amazon")).thenReturn(publisher);

        assertEquals("addBookEdition", bookEditionsController.addBookEdition(
                "title",
                "desc",
                "Amazon",
                "isbn",
                2018,
                model));

        class BookEditionArgumentMatcher implements ArgumentMatcher {
            public boolean matches(Object o) {
                if (o instanceof BookEdition) {
                    BookEdition bookEdition = (BookEdition) o;
                    if (!bookEdition.getTitle().equals("title")) return false;
                    if (!bookEdition.getDescription().equals("desc")) return false;
                    if (!bookEdition.getPublisher().equals(publisher)) return false;
                    if (!bookEdition.getIsbn().equals("isbn")) return false;
                    if (!bookEdition.getYearOfPublication().equals(2018)) return false;
                }
                return true;
            }
        }
        verify(bookEditionsService).addBookEdition((BookEdition) argThat(new BookEditionArgumentMatcher()));
    }

    @Test
    void showBookEditionDescriptionPage() {
        when(bookEditionsService.getById(anyInt())).thenReturn(bookEdition);
        when(bookCopiesService.getBookCopyCountByBookEditionId(anyInt())).thenReturn(1);
        when(bookCopiesService.getBookCopyCountByBookEditionIdInStatusFree(anyInt())).thenReturn(1);
        when(bookQueueService.getBookQueueCountByBookEditionId(anyInt())).thenReturn(1);
        assertEquals("bookEditionDescription", bookEditionsController.showBookEditionDescriptionPage(1, model));
        verify(model, times(1)).addAttribute("bookEdition", bookEdition);
        verify(model, times(1)).addAttribute("countBookCopy", 1);
        verify(model, times(1)).addAttribute("countBookCopyInStatusFree", 1);
        verify(model, times(1)).addAttribute("userCountInQueue", 1);
    }

    @Test
    void getOutOfQueue() {
        User user = new User();
        when(principal.getName()).thenReturn("user");
        when(bookEditionsService.getById(anyInt())).thenReturn(bookEdition);
        when(userService.getUserByLogin(anyString())).thenReturn(user);
        doNothing().when(bookQueueService).deleteUserFromQueue(user, bookEdition);
        assertEquals("bookEditionDescription", bookEditionsController.getOutOfQueue(1, model, principal));
        verify(bookQueueService, times(1)).deleteUserFromQueue(user, bookEdition);
        verify(model, times(1)).addAttribute("bookEdition", bookEdition);
    }

    @Test
    void getInQueue() {
        User user = new User();
        when(principal.getName()).thenReturn("user");
        when(bookEditionsService.getById(anyInt())).thenReturn(bookEdition);
        when(userService.getUserByLogin(anyString())).thenReturn(user);
        doNothing().when(bookQueueService).addUserToBookQueue(user, bookEdition);
        assertEquals("bookEditionDescription", bookEditionsController.getInQueue(1, model, principal));
        verify(bookQueueService, times(1)).addUserToBookQueue(user, bookEdition);
        verify(model, times(1)).addAttribute("bookEdition", bookEdition);
    }


}