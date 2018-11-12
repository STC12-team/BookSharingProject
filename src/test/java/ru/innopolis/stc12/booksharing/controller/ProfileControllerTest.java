package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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

    @Mock
    private UserService userService;

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void contextLoads() {
        assertNotNull(profileController);
    }

    @Test
    void getProfilePage() {
        when(userService.getAuthenticatedUserDetails()).thenReturn(new UserDetails());
        when(model.addAttribute(anyString())).thenReturn(model);
        assertEquals("userProfile", profileController.getProfilePage(model));
    }

    @Test
    void getProfileEditPageWithNullDetails() {
        assertEquals("userProfile", profileController.getProfilePage(model));
    }

    @Test
    void getProfileEditPageWithDetails() {
        when(userService.getAuthenticatedUserDetails()).thenReturn(new UserDetails());
        when(model.addAttribute(anyString(), any())).thenReturn(model);
        profileController.setUserPasswordConfirmed(true);
        assertEquals("userEdit", profileController.getProfileEditPage(model));
    }
}
