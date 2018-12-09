package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.service.BookEditionsService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class LibraryControllerTest {
    @InjectMocks
    private LibraryController libraryController;
    @Mock
    private Model model;
    @Mock
    private BookEditionsService bookEditionsService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void getLibraryPage() {
        when(bookEditionsService.getAllBookEditions()).thenReturn(new ArrayList<>());
        when(bookEditionsService.getBookEditionsBySearchValue(anyString())).thenReturn(new ArrayList<>());
        assertEquals("/library", libraryController.getLibraryPage("", model));
        assertEquals("/library", libraryController.getLibraryPage(null, model));
        assertEquals("/library", libraryController.getLibraryPage(anyString(), model));
    }

    @Test
    void getWelcomePage() {
        assertEquals("/library", libraryController.getWelcomePage());
    }


}