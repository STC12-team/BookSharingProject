package ru.innopolis.stc12.booksharing.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import ru.innopolis.stc12.booksharing.exceptions.TestException;
import ru.innopolis.stc12.booksharing.model.pojo.UserDetails;
import ru.innopolis.stc12.booksharing.service.UserService;

@Controller
public class ProfileController {
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private Logger logger = Logger.getLogger(ProfileController.class);
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/userProfile")
    @ExceptionHandler(TestException.class)
    public String getAboutPage(Model model) {

        UserDetails authenticatedUserDetails = userService.getAuthenticatedUserDetails();
        if (authenticatedUserDetails == null) {
            model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Cannot get authenticated user");
            logger.error("Cannot get authenticated user");
        } else {
            model.addAttribute("userDetails", authenticatedUserDetails);
        }

        return "userProfile";
    }
}
