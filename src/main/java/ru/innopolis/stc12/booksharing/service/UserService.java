package ru.innopolis.stc12.booksharing.service;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.model.dao.UserDao;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
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
            return Objects.equals(user.getPassword(), Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString());
        }

        return false;
    }
}
