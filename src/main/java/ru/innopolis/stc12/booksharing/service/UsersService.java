package ru.innopolis.stc12.booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.model.dao.UsersDaoImpl;
import ru.innopolis.stc12.booksharing.model.pojo.User;

@Service
public class UsersService {
    private UsersDaoImpl userDaoImpl;

    @Autowired
    public void setUserDaoImpl(UsersDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    public User getUserByLogin(String login) {
        return userDaoImpl.getUserByLogin(login);
    }
}
