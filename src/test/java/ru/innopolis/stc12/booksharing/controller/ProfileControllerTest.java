package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.model.dao.UserDao;
import ru.innopolis.stc12.booksharing.model.pojo.UserDetails;
import ru.innopolis.stc12.booksharing.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class ProfileControllerTest {
    private ProfileController profileController;
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        initMocks(this);
        this.profileController = new ProfileController();
        this.userService = new UserService(userDao);
    }

    @Test
    void contextLoads() {
        assertNotNull(profileController);
    }

    @Test
    void getProfilePage() {
        profileController.setUserService(userService);
        when(userService.getAuthenticatedUserDetails()).thenReturn(new UserDetails());
        when(model.addAttribute(anyString())).thenReturn(model);
        assertEquals("userProfile", profileController.getProfilePage(model));
    }

    @Test
    void getProfileEditPageWithNullDetails() {
        profileController.setUserService(userService);
        when(userService.getAuthenticatedUserDetails()).thenReturn(new UserDetails());
        when(model.addAttribute(anyString())).thenReturn(model);
        assertEquals("userProfile", profileController.getProfilePage(model));
    }

    @Test
    void getProfileEditPageWithDetails() {
        profileController.setUserService(userService);
        when(userService.getAuthenticatedUserDetails()).thenReturn(new UserDetails());
        when(model.addAttribute(anyString(), any())).thenReturn(model);
        profileController.setAuthenticatedUserDetails(new UserDetails());
        profileController.setUserPasswordConfirmed(true);
        assertEquals("userProfile", profileController.getProfilePage(model));
    }
}
