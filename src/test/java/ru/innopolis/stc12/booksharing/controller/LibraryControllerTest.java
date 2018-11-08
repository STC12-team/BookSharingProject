package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.service.BookEditionsService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class LibraryControllerTest {
    private LibraryController libraryController;
    @Mock
    private Model model;
    @Mock
    private BookEditionsService bookEditionsService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        libraryController = new LibraryController();
        libraryController.setBookEditionsService(bookEditionsService);
    }

    @Test
    void getLibraryPage() {
        when(bookEditionsService.getAllBookEditions()).thenReturn(new ArrayList<>());
        assertEquals("/library", libraryController.getLibraryPage("", model));
    }
}