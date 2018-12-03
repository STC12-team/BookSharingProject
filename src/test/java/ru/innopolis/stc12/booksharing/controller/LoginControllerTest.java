package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.exceptions.ControllerException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class LoginControllerTest {
    private LoginController loginController;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        initMocks(this);
        loginController = new LoginController();
    }

    @Test
    void contextLoads() {
        assertNotNull(loginController);
    }

    @Test
    void getLoginPageWithError() {
        assertThrows(ControllerException.class, () -> loginController.getLoginPage("error", model));
    }

    @Test
    void getLoginPage() {
        assertEquals("login", loginController.getLoginPage(null, model));
    }
}
