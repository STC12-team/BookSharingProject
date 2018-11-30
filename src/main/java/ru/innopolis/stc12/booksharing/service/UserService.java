package ru.innopolis.stc12.booksharing.service;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.model.dao.UserDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.Role;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.dao.entity.UserDetails;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private Logger logger = Logger.getLogger(UserService.class);
    private static final int ROLE_USER_ID = 2;
    private static final int USER_ENABLED = 1;

    public boolean checkUserPasswordMatches(String currentPassword, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(password, currentPassword);
    }

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDao<User> userDao;
    private UserDao<Role> roleDao;

    @Autowired
    public void setUserDao(UserDao<User> userDao) {
        this.userDao = userDao;
        this.userDao.setClazz(User.class);
    }

    @Autowired
    public void setRoleDao(UserDao<Role> roleDao) {
        this.roleDao = roleDao;
        this.roleDao.setClazz(Role.class);
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<User> getUsers() {
        return this.userDao.findAll();
    }

    public User getUserByLogin(String login) {
        User user = null;
        if (login != null && !login.isEmpty()) {
            user = userDao.getUserByLogin(login);
        }
        return user;
    }

    public User getAuthenticatedUserDetails() {
        return userDao.getUserByLogin(getAuthenticatedUserLogin());
    }

    /**
     * Used for user password confirmation
     *
     * @param password
     * @return boolean password confirmed
     */
    public boolean confirmPassword(String password) {
        String currentPassword = getAuthenticatedUserDetails().getPassword();
        return checkUserPasswordMatches(currentPassword, password);
    }

    public User addNewUser(String login, String password) {
        logger.debug("Insert User login = " + login);

        Role role = roleDao.findOne(ROLE_USER_ID);
        String cryptPassword = bCryptPasswordEncoder.encode(password);
        User user = new User(login, cryptPassword, role, USER_ENABLED);

        userDao.save(user);
        return getUserByLogin(login);
    }

    public boolean updateUserDetails(String firstName, String lastName, String surname) {
        UserDetails currentUserDetails = getAuthenticatedUserDetails().getUserDetails();
        if (currentUserDetails == null) {
            return false;
        }
        // TODO: after email confirmation & edit with password edit will be available refactor this
        if (!firstName.equals("")) {
            currentUserDetails.setFirstName(firstName);
        }

        if (!lastName.equals("")) {
            currentUserDetails.setLastName(lastName);
        }

        if (!surname.equals("")) {
            currentUserDetails.setSurname(surname);
        }

        return userDao.updateUserDetails(currentUserDetails);
    }

    private static String getAuthenticatedUserLogin(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            return Objects.requireNonNull(user).getUsername();
        }
        return null;
    }
}
