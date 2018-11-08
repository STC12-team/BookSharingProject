package ru.innopolis.stc12.booksharing.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
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

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/userProfile")
    @ExceptionHandler(ControllerException.class)
    public String getProfilePage(Model model) {
        authenticatedUserDetails = userService.getAuthenticatedUserDetails();
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
        authenticatedUserDetails = userService.getAuthenticatedUserDetails();
        if (authenticatedUserDetails == null) {
            model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Cannot get authenticated user");
            logger.error("Cannot get authenticated user");
        } else {
            model.addAttribute("userDetails", authenticatedUserDetails);
        }
        return "userEdit";
    }

    @PostMapping(value = "/userEdit")
    @ExceptionHandler(ControllerException.class)
    public ModelAndView getProfileEditPage(@RequestParam(value = "firstName", required = false) String firstName,
                                     @RequestParam(value = "lastName", required = false) String lastName,
                                     @RequestParam(value = "surname", required = false) String surname,
                                     Model model) {
        // TODO check values, update database, redirect with message
        model.addAttribute(MODEL_UPDATED_ATTRIBUTE, "Record was updated");
        model.addAttribute("userDetails", authenticatedUserDetails);

        return new ModelAndView("userProfile");
    }
}
