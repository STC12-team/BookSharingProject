package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.exceptions.TestException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class AboutControllerTest {
    private AboutController aboutController;
    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        initMocks(this);
        aboutController = new AboutController();
    }

    @Test
    void getAboutPage() {
        when(model.addAttribute(any(), any())).thenReturn(model);
        assertEquals("about", aboutController.getAboutPage(model));
    }

    @Test
    void exceptionCalledProperly() {
        when(model.addAttribute(any(), any())).thenThrow(new TestException("Exception is thrown"));

        assertThrows(TestException.class, () -> {
            aboutController.getAboutPage(model);
        });
    }
}