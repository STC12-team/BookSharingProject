package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
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

    @InjectMocks
    private RegisterController registerController;

    @BeforeEach
    void setUp() {
        initMocks(this);
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
        assertSame(new ModelAndView("register").getView(),
                registerController.postRegisterPage("newLogin", "newPassword", "newEmail", model).getView());
    }
}
