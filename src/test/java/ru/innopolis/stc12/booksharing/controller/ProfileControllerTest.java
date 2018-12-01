package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.view.RedirectView;
import ru.innopolis.stc12.booksharing.model.dao.entity.UserDetails;
import ru.innopolis.stc12.booksharing.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class ProfileControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private SessionStatus status;

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
//        when(userService.getAuthenticatedUserDetails()).thenReturn(new UserDetails());
        when(model.addAttribute(anyString())).thenReturn(model);
        assertEquals("userProfile", profileController.getProfilePage(model));
    }

    @Test
    void getProfileEditPageWithNullDetails() {
        assertEquals("userProfile", profileController.getProfilePage(model));
    }

    @Test
    void getProfileEditPageWithDetails() {
//        when(userService.getAuthenticatedUserDetails()).thenReturn(new UserDetails());
        when(model.addAttribute(anyString(), any())).thenReturn(model);
        profileController.setUserPasswordConfirmed(true);
        assertEquals("userEdit", profileController.getProfileEditPage(model));
    }

    @Test
    void postProfileEditPage() {
        when(model.addAttribute(anyString())).thenReturn(model);
        assertSame(profileController.postProfileEditPage(
                "firstname", "lastname", "surname", model, status).getUrl(), new RedirectView("userProfile").getUrl());
    }

    @Test
    void postProfileEditPageWithSuccessOnUpdateDetailsUpdateConfirmationFlagToFalse() {
        when(model.addAttribute(anyString())).thenReturn(model);
        when(userService.updateUserDetails(anyString(), anyString(), anyString())).thenReturn(new UserDetails());
        assertFalse(profileController.isUserPasswordConfirmed());
    }

    @Test
    void getConfirmationPage() {
        when(model.addAttribute(anyString())).thenReturn(model);
        assertSame(profileController.getConfirmationPage("secret", model).getUrl(),
                new RedirectView("userProfile").getUrl());
    }
}
