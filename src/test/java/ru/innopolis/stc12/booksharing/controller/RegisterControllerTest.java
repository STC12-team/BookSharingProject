package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RegisterControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private User user;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private RegisterController registerController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        when(model.addAttribute(anyString(), anyString())).thenReturn(model);
    }

    @Test
    void contextLoads() {
        assertNotNull(registerController);
    }

    @Test
    void getRegisterPage() {
        when(model.addAttribute(anyString())).thenReturn(model);
        assertEquals("register", registerController.getRegisterPage(model));
    }

    @Test
    void postRegisterPage() {
        when(model.addAttribute(anyString())).thenReturn(model);
        when(userService.getUserByLogin("newLogin")).thenReturn(user);
        assertSame(true,
                registerController.postRegisterPage("newLogin", "newPassword", "newEmail", model).isRedirectView());
    }
}
