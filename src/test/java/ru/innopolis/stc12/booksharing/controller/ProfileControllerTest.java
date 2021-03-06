package ru.innopolis.stc12.booksharing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.dao.entity.UserDetails;
import ru.innopolis.stc12.booksharing.service.FileUploadService;
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
    MultipartFile file;

    @Mock
    private Model model;

    @Mock
    private User user;

    @Mock
    UserDetails userDetails;
    @Mock
    private FileUploadService fileUploadService;

    @Mock
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        initMocks(this);
        when(model.addAttribute(anyString(), anyString())).thenReturn(model);
    }

    @Test
    void contextLoads() {
        assertNotNull(profileController);
    }

    @Test
    void getProfilePage() {
        when(userService.getAuthenticatedUserDetails()).thenReturn(user);
        when(user.getUserDetails()).thenReturn(userDetails);
        assertEquals("userProfile", profileController.getProfilePage(model));
        verify(model, times(1)).addAttribute("userDetails", userDetails);
    }

    @Test
    void getProfileEditPageWithNullDetails() {
        when(messageSource.getMessage(anyString(), any(), anyString(), any())).thenReturn("Cannot get authenticated user");
        when(userService.getAuthenticatedUserDetails()).thenReturn(user);
        when(user.getUserDetails()).thenReturn(null);
        assertEquals("userProfile", profileController.getProfilePage(model));
        verify(model, times(1)).addAttribute("errorMessage", "Cannot get authenticated user");
    }

    @Test
    void getProfileEditPageWithDetails() {
        when(userService.getAuthenticatedUserDetails()).thenReturn(user);
        when(user.getUserDetails()).thenReturn(userDetails);
        when(model.addAttribute(anyString(), any())).thenReturn(model);
        profileController.setUserPasswordConfirmed(true);
        assertEquals("userEdit", profileController.getProfileEditPage(model));
    }

    @Test
    void postProfileEditPage() {
        when(model.addAttribute(anyString())).thenReturn(model);
        when(fileUploadService.uploadMultipartFile(file)).thenReturn("some URL");
        assertSame(profileController.postProfileEditPage(
                "firstname", "lastname", "surname", file, model, status).getUrl(), new RedirectView("userProfile").getUrl());
    }

    @Test
    void postProfileEditPageWithNotNullableUserDetails() {
        when(model.addAttribute(anyString())).thenReturn(model);
        when(userService.updateUserDetails(anyString(), anyString(), anyString(), anyString())).thenReturn(userDetails);
        when(fileUploadService.uploadMultipartFile(file)).thenReturn("some URL");
        assertSame(profileController.postProfileEditPage(
                "firstname", "lastname", "surname", file, model, status).getUrl(), new RedirectView("userProfile").getUrl());
    }

    @Test
    void postProfileEditPageWithSuccessOnUpdateDetailsUpdateConfirmationFlagToFalse() {
        when(model.addAttribute(anyString())).thenReturn(model);
        when(userService.updateUserDetails(anyString(), anyString(), anyString(), anyString())).thenReturn(new UserDetails());
        assertFalse(profileController.isUserPasswordConfirmed());
    }

    @Test
    void getConfirmationPage() {
        when(model.addAttribute(anyString())).thenReturn(model);
        assertSame(profileController.getConfirmationPage("secret", model).getUrl(),
                new RedirectView("userProfile").getUrl());
    }
}
