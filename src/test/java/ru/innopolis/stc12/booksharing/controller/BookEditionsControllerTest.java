package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;
import ru.innopolis.stc12.booksharing.service.BookCopiesService;
import ru.innopolis.stc12.booksharing.service.BookEditionsService;
import ru.innopolis.stc12.booksharing.service.BookQueueService;
import ru.innopolis.stc12.booksharing.service.PublisherService;

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
    private BookCopiesService bookCopiesService;
    @Mock
    private BookQueueService bookQueueService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookEditionsController = new BookEditionsController();
        bookEditionsController.setBookEditionsService(bookEditionsService);
        bookEditionsController.setPublisherService(publisherService);
        bookEditionsController.setBookCopiesService(bookCopiesService);
        bookEditionsController.setBookQueueService(bookQueueService);
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

    @Test
    void showBookEditionDescriptionPage() {
        when(bookEditionsService.getById(anyInt())).thenReturn(bookEdition);
        when(bookCopiesService.getBookCopyCountByBookEditionId(anyInt())).thenReturn(1);
        when(bookCopiesService.getBookCopyCountByBookEditionIdInStatusFree(anyInt())).thenReturn(1);
        when(bookQueueService.getUserCountByBookEditionId(anyInt())).thenReturn(1);
        assertEquals("bookEditionDescription", bookEditionsController.showBookEditionDescriptionPage(1, model));
        verify(model, times(1)).addAttribute("bookEdition", bookEdition);
        verify(model, times(1)).addAttribute("countBookCopy", 1);
        verify(model, times(1)).addAttribute("countBookCopyIsStatusFree", 1);
        verify(model, times(1)).addAttribute("userCountInQueue", 1);
    }
}