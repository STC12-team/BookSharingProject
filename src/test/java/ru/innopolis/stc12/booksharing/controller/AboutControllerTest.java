package ru.innopolis.stc12.booksharing.controller;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    private AboutController aboutController;
    @Mock
    ApplicationContext context;

    @Mock
    private Model model;
    private Locale locale;

    @BeforeEach
    void setUp() {
        initMocks(this);
        aboutController = new AboutController();
        locale = new Locale("English");
    }

    @Ignore
    void getAboutPage() {
        when(model.addAttribute(any(), any())).thenReturn(model);
        when(context.getMessage("message.about", new Object[]{}, locale)).thenReturn("test");
        assertEquals("about", aboutController.getAboutPage(locale, model));
    }

    @Test
    void exceptionCalledProperly() {
        when(model.addAttribute(any(), any())).thenThrow(new ControllerException("Exception is thrown"));
        when(context.getMessage("message.about", new Object[]{}, locale)).thenReturn("test");
        assertThrows(NullPointerException.class, () -> aboutController.getAboutPage(locale, model));
    }
}