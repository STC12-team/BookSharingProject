package ru.innopolis.stc12.booksharing.service;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.stc12.booksharing.model.dao.entity.Role;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.dao.entity.UserDetails;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.RoleDao;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.UserDao;

import java.util.List;
import java.util.Objects;

@EnableTransactionManagement
@Service
@Transactional
public class UserService {
    private Logger logger = Logger.getLogger(UserService.class);
    private static final int ROLE_USER_ID = 2;
    private static final int USER_ENABLED = 1;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDao<User> userDao;
    private RoleDao roleDao;
    private UserDao<UserDetails> userDetailsDao;

    @Autowired
    public void setUserDao(UserDao<User> userDao) {
        this.userDao = userDao;
        this.userDao.setClazz(User.class);
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
        this.roleDao.setClazz(Role.class);
    }

    @Autowired
    public void setUserDetailsDao(UserDao<UserDetails> userDetailsDao) {
        this.userDetailsDao = userDetailsDao;
        this.userDetailsDao.setClazz(UserDetails.class);
    }

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean checkUserPasswordMatches(String currentPassword, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, currentPassword);
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

    public User getUserByEmail(String email) {
        User user = null;
        if (email != null && !email.isEmpty()) {
            user = userDao.getUserByEmail(email);
        }
        return user;
    }

    public User getAuthenticatedUserDetails() {
        return userDao.getUserByLogin(getAuthenticatedUserLogin());
    }

    public boolean confirmPassword(String password) {
        String currentPassword = getAuthenticatedUserDetails().getPassword();
        return checkUserPasswordMatches(currentPassword, password);
    }

    public User addNewUser(String login, String password, String email) {
        logger.debug("Insert User login = " + login);

        Role role = roleDao.findOne(ROLE_USER_ID);
        String cryptPassword = bCryptPasswordEncoder.encode(password);
        User user = new User(login, cryptPassword, role, USER_ENABLED, email);

        userDao.save(user);
        return getUserByLogin(login);
    }

    public UserDetails updateUserDetails(String firstName, String lastName, String surname) {
        UserDetails currentUserDetails = getAuthenticatedUserDetails().getUserDetails();
        if (currentUserDetails == null) {
            return null;
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

        return userDetailsDao.update(currentUserDetails);
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
