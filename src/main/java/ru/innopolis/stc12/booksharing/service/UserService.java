package ru.innopolis.stc12.booksharing.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.model.dao.UserDao;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.util.List;

@Service
public class UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserService() {

    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
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

    public boolean validateUser(String login, String password) {
        User user;
        if (login != null && password != null) {
            user = this.getUserByLogin(login);
            if (user != null) {
                return bCryptPasswordEncoder.matches(password, user.getPassword());
            }
        }
        return false;
    }

    public User addUser(String login, String password) {
        String cryptPassword = bCryptPasswordEncoder.encode(password);
        return userDao.addUser(login, cryptPassword);
    }

}
