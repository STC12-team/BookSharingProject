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
import ru.innopolis.stc12.booksharing.model.pojo.UserDetails;
import ru.innopolis.stc12.booksharing.service.UserService;

@Controller
public class ProfileController {
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String MODEL_UPDATED_ATTRIBUTE = "profileEditResultMessage";
    private Logger logger = Logger.getLogger(ProfileController.class);
    private UserService userService;
    private UserDetails authenticatedUserDetails;
    private boolean userPasswordConfirmed = false;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setAuthenticatedUserDetails(UserDetails userDetails) {
        this.authenticatedUserDetails = userDetails;
    }

    public void setUserPasswordConfirmed(boolean value) {
        this.userPasswordConfirmed = value;
    }

    @GetMapping(value = "/userProfile")
    @ExceptionHandler(ControllerException.class)
    public String getProfilePage(Model model) {
        setAuthenticatedUserDetails(userService.getAuthenticatedUserDetails());
        if (authenticatedUserDetails == null) {
            model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Cannot get authenticated user");
            logger.error("Cannot get authenticated user");
        } else {
            model.addAttribute("userDetails", authenticatedUserDetails);
        }
        return "userProfile";
    }

    @GetMapping(value = "/userEdit")
    @ExceptionHandler(ControllerException.class)
    public String getProfileEditPage(Model model) {
        setAuthenticatedUserDetails(userService.getAuthenticatedUserDetails());
        if (authenticatedUserDetails == null) {
            model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Cannot get authenticated user");
            logger.error("Cannot get authenticated user");
        } else {
            model.addAttribute("userDetails", authenticatedUserDetails);
            if (!userPasswordConfirmed) {
                model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Confirm your password before editing profile");
                return "userProfile";
            }
        }
        return "userEdit";
    }

    @PostMapping(value = "/userEdit")
    @ExceptionHandler(ControllerException.class)
    public RedirectView getProfileEditPage(@RequestParam(value = "firstName", required = false) String firstName,
                                     @RequestParam(value = "lastName", required = false) String lastName,
                                     @RequestParam(value = "surname", required = false) String surname,
                                     Model model, SessionStatus status) {
        model.addAttribute("userDetails", authenticatedUserDetails);
        boolean updated = userService.updateUserDetails(firstName, lastName, surname);
        if (updated) {
            model.addAttribute(MODEL_UPDATED_ATTRIBUTE, "Record was updated");
            setUserPasswordConfirmed(false); // set value back to false after editing for reconfirmation on next visit
            status.setComplete(); // clean up session attributes
            return new RedirectView("userProfile");
        }

        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Cannot update user details");
        return new RedirectView("userProfile");
    }

    @PostMapping(value = "/userConfirmation")
    @ExceptionHandler(ControllerException.class)
    public RedirectView getProfileEditPage(@RequestParam(value = "password__confirmation") String password,
                                     Model model) {
        if (password != null && !password.isEmpty()) {
            boolean passwordMatch = userService.confirmPassword(password);
            if (passwordMatch) {
                setUserPasswordConfirmed(true);
                return new RedirectView("userEdit");
            }
        }
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Wrong password");
        return new RedirectView("userProfile");
    }
}
