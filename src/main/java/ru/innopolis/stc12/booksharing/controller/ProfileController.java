package ru.innopolis.stc12.booksharing.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.view.RedirectView;
import ru.innopolis.stc12.booksharing.exceptions.ControllerException;
import ru.innopolis.stc12.booksharing.model.dao.entity.UserDetails;
import ru.innopolis.stc12.booksharing.service.UserService;

@Controller
public class ProfileController {
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String MODEL_UPDATED_ATTRIBUTE = "profileEditResultMessage";
    private static final String USER_DETAILS_PAGE_ATTRIBUTE = "userDetails";
    private static final String PROFILE_PAGE_ATTRIBUTE = "userProfile";
    private static final String EDIT_DETAILS_PAGE_ATTRIBUTE = "userEdit";

    private static final String MESSAGE_CANNOT_GET_USER = "Cannot get authenticated user";
    private static final String MESSAGE_REQUEST_PASS_CONFIRMATION = "Confirm your password before editing profile";
    private static final String MESSAGE_SUCCESSFULLY_UPDATE = "Record was updated";
    private static final String MESSAGE_CANNOT_UPDATE = "Cannot update user details";
    private static final String MESSAGE_WRONG_PASS = "Wrong password";

    private Logger logger = Logger.getLogger(ProfileController.class);
    private UserService userService;
    private UserDetails authenticatedUserDetails;
    private boolean userPasswordConfirmed = false;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUserPasswordConfirmed(boolean value) {
        this.userPasswordConfirmed = value;
    }

    public boolean isUserPasswordConfirmed() {
        return userPasswordConfirmed;
    }

    @GetMapping(value = "/userProfile")
    @ExceptionHandler(ControllerException.class)
    public String getProfilePage(Model model) {
        authenticatedUserDetails = userService.getAuthenticatedUserDetails().getUserDetails();
        if (authenticatedUserDetails == null) {
            model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, MESSAGE_CANNOT_GET_USER);
            logger.error(MESSAGE_CANNOT_GET_USER);
        } else {
            model.addAttribute(USER_DETAILS_PAGE_ATTRIBUTE, authenticatedUserDetails);
        }
        return PROFILE_PAGE_ATTRIBUTE;
    }

    @GetMapping(value = "/userEdit")
    @ExceptionHandler(ControllerException.class)
    public String getProfileEditPage(Model model) {
        authenticatedUserDetails = userService.getAuthenticatedUserDetails().getUserDetails();
        if (authenticatedUserDetails == null) {
            model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, MESSAGE_CANNOT_GET_USER);
            logger.error(MESSAGE_CANNOT_GET_USER);
        } else {
            model.addAttribute(USER_DETAILS_PAGE_ATTRIBUTE, authenticatedUserDetails);
            if (!userPasswordConfirmed) {
                model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, MESSAGE_REQUEST_PASS_CONFIRMATION);
                return PROFILE_PAGE_ATTRIBUTE;
            }
        }
        return EDIT_DETAILS_PAGE_ATTRIBUTE;
    }

    @PostMapping(value = "/userEdit")
    @ExceptionHandler(ControllerException.class)
    public RedirectView postProfileEditPage(@RequestParam(value = "firstName", required = false) String firstName,
                                     @RequestParam(value = "lastName", required = false) String lastName,
                                     @RequestParam(value = "surname", required = false) String surname,
                                     Model model, SessionStatus status) {
        model.addAttribute(USER_DETAILS_PAGE_ATTRIBUTE, authenticatedUserDetails);
        UserDetails userDetails = userService.updateUserDetails(firstName, lastName, surname);
        if (userDetails != null) {
            model.addAttribute(MODEL_UPDATED_ATTRIBUTE, MESSAGE_SUCCESSFULLY_UPDATE);
            setUserPasswordConfirmed(false); // set value back to false after editing for reconfirmation on next visit
            status.setComplete(); // clean up session attributes
            return new RedirectView(PROFILE_PAGE_ATTRIBUTE);
        }

        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, MESSAGE_CANNOT_UPDATE);
        return new RedirectView(PROFILE_PAGE_ATTRIBUTE);
    }

    @PostMapping(value = "/userConfirmation")
    @ExceptionHandler(ControllerException.class)
    public RedirectView getConfirmationPage(@RequestParam(value = "password__confirmation") String password,
                                     Model model) {
        if (password != null && !password.isEmpty()) {
            boolean passwordMatch = userService.confirmPassword(password);
            if (passwordMatch) {
                setUserPasswordConfirmed(true);
                return new RedirectView(EDIT_DETAILS_PAGE_ATTRIBUTE);
            }
        }
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, MESSAGE_WRONG_PASS);
        return new RedirectView(PROFILE_PAGE_ATTRIBUTE);
    }
}
