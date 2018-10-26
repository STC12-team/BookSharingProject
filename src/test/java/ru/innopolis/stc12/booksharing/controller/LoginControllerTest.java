package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;
import ru.innopolis.stc12.booksharing.model.dao.UserDao;
import ru.innopolis.stc12.booksharing.model.pojo.User;
import ru.innopolis.stc12.booksharing.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class LoginControllerTest {
    private LoginController loginController;
    @Mock
    private Model model;

    @Mock
    private UserService userService;

    @Mock
    private UserDao userDao;

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
    void getLoginPageGet() {
        when(model.addAttribute(any(), any())).thenReturn(model);

        assertEquals("login", loginController.getLoginPage("", model));
    }

    @Test
    void getLoginPagePots() {
        when(model.addAttribute(any(), any())).thenReturn(model);
        when(userDao.getUserByLogin(any())).thenReturn(new User());
        when(userService.validateUser("login", "password")).thenReturn(true);
        when(userService.getUserByLogin(any())).thenReturn(new User());

        assertEquals("login", loginController.getLoginPage("login", "password", model));
    }
}
