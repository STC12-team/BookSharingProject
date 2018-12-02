package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.service.BookCopiesService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CatalogControllerTest {
    private CatalogController catalogController;

    @Mock
    private BookCopiesService bookCopiesService;

    @Mock
    private Model model;


    @BeforeEach
    void setUp() {
        initMocks(this);
        catalogController = new CatalogController();
    }

    @Test
    void contextLoads() {
        assertNotNull(catalogController);
    }

    @Test
    void getCatalogPageWithLoginSuccess() {
        List<BookCopy> list = new ArrayList<>();
        catalogController.setBookCopiesService(bookCopiesService);
        when(bookCopiesService.getBookCopiesByUser(1)).thenReturn(list);
//        assertEquals("catalog", catalogController.showCatalogPage(model));
    }

}
