package ru.innopolis.stc12.booksharing.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.UserDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.dao.entity.UserDetails;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {
    @Mock
    UserDao userDao;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUsers() {
        List<User> users = new ArrayList<>();
//        when(userDao.getAllUsers()).thenReturn(users);
        Assertions.assertEquals(users, userService.getUsers());
    }

    @Test
    void getUserByLogin() {
        User user = new User();
        when(userDao.getUserByLogin("TestUser")).thenReturn(user);
        Assertions.assertEquals(user, userService.getUserByLogin("TestUser"));
        Assertions.assertNull(userService.getUserByLogin(null));
        Assertions.assertNull(userService.getUserByLogin(""));
    }

    @Test
    void getAuthenticatedUserDetails() {
//        when(userDao.getUserDetails()).thenReturn(userDetails);
//        Assertions.assertEquals(userDetails, userService.getAuthenticatedUserDetails());
    }

    @Test
    void addUser() {
        User user = new User();
        user.setId(1);
//        when(userDao.addUser("TestLogin", "Hash")).thenReturn(user);
        when(bCryptPasswordEncoder.encode("TestPassword")).thenReturn("Hash");
//        Assertions.assertEquals(user, userService.addUser("TestLogin", "TestPassword"));
    }

    @Test
    void confirmCorrectPassword() {
//        UserDetails userDetails = new UserDetails(1, 1, "firstname", "lastname", "surname", "admin@example.com", "sa");
//        when(userDao.getUserDetails()).thenReturn(userDetails);
//        when(userDao.checkUserPasswordMatches(anyString(), anyString())).thenReturn(true);
//        assertTrue(userService.confirmPassword("sa"));
    }

    @Test
    void confirmWrongPassword() {
//        UserDetails userDetails = new UserDetails(1, 1, "firstname", "lastname", "surname", "admin@example.com", "secret");
//        when(userDao.getUserDetails()).thenReturn(userDetails);
//        when(userDao.checkUserPasswordMatches(anyString(), anyString())).thenReturn(false);
//        assertFalse(userService.confirmPassword("sa"));
    }

    @Test
    void updateUserDetails() {
//        userService.updateUserDetails("", "", "");
//        verify(userDao, times(0)).updateUserDetails(userDetails);
    }
}
