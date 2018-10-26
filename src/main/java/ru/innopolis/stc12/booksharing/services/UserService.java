package ru.innopolis.stc12.booksharing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.dao.UserDao;
import ru.innopolis.stc12.booksharing.entitys.User;

@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserByLogin(String login) {
        return userDao.getByLogin(login);
    }
}
