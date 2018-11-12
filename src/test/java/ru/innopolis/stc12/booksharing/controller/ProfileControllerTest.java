package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import ru.innopolis.stc12.booksharing.model.pojo.UserDetails;
import ru.innopolis.stc12.booksharing.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
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
}
