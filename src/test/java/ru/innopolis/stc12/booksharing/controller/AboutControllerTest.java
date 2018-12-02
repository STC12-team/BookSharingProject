package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.exceptions.ControllerException;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class AboutControllerTest {
    @InjectMocks
    private AboutController aboutController;
    @Mock
    ApplicationContext appContext;

    @Mock
    private Model model;
    private Locale locale;

    @BeforeEach
    void setUp() {
        initMocks(this);
        locale = new Locale("English");
    }

    @Test
    void getAboutPage() {
        when(model.addAttribute(any(), any())).thenReturn(model);
        when(appContext.getMessage("message.about", new Object[]{}, locale)).thenReturn("test");
        assertEquals("about", aboutController.getAboutPage(locale, model));
    }

    @Test
    void exceptionCalledProperly() {
        when(model.addAttribute(any(), any())).thenThrow(new ControllerException("Exception is thrown"));
        when(appContext.getMessage("message.about", new Object[]{}, locale)).thenReturn("test");
        assertThrows(ControllerException.class, () -> aboutController.getAboutPage(locale, model));
    }
}