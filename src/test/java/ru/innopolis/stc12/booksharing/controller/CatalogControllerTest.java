package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CatalogControllerTest {
    private CatalogController catalogController;
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
        when(model.containsAttribute(any())).thenReturn(true);

        assertEquals("catalog", catalogController.getCatalogPage(any(), model));
    }

    @Test
    void getCatalogPageWithLoginError() {
        when(model.containsAttribute(any())).thenReturn(false);

        assertEquals("redirect:login", catalogController.getCatalogPage(any(), model));
    }
}
