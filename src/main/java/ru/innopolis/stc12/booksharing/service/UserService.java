package ru.innopolis.stc12.booksharing.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.model.dao.UserDao;
import ru.innopolis.stc12.booksharing.model.pojo.User;
import ru.innopolis.stc12.booksharing.model.pojo.UserDetails;

import java.util.List;

@Service
public class UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDao userDao;

    public UserService() {

    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<User> getUsers() {
        return this.userDao.getAllUsers();
    }

    public User getUserByLogin(String login) {
        User user = null;
        if (login != null) {
            user = this.userDao.getUserByLogin(login);
        }
        return user;
    }

    public User addUser(String login, String password) {
        String cryptPassword = bCryptPasswordEncoder.encode(password);
        return userDao.addUser(login, cryptPassword);
    }

    /**
     *
     * @return ru.innopolis.stc12.booksharing.model.pojo.UserDetails
     */
    public UserDetails getAuthenticatedUserDetails() {
        return userDao.getUserDetails();
    }

    /**
     * Used for user password confirmation
     *
     * @param password
     * @return boolean password confirmed
     */
    public boolean confirmPassword(String password) {
        String currentPassword =  getAuthenticatedUserDetails().getPassword();
        return userDao.checkUserPasswordMatches(currentPassword, password);
    }
}
