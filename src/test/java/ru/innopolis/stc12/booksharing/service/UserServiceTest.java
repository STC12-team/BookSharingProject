package ru.innopolis.stc12.booksharing.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.innopolis.stc12.booksharing.model.dao.UserDao;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setUp() {
        initMocks(this);
        userService = new UserService(userDao);
    }

    @Test
    void getUsers() {
        List<User> users = new ArrayList<>();
        when(userDao.getAllUsers()).thenReturn(users);
        Assertions.assertEquals(users, userService.getUsers());
    }

    @Test
    void getUserByLogin() {
        User user = new User();
        when(userDao.getUserByLogin(anyString())).thenReturn(user);
        userService.setUserDao(userDao);
        Assertions.assertEquals(user, userService.getUserByLogin(anyString()));
    }

    @Test
    void addUser() {
        // TODO add this test!
    }
}
