package ru.innopolis.stc12.booksharing.model.dao;

import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.util.List;

public interface UserDao {
    User getUserById(int id);

    List<User> getAllUsers();

    User getUserByLogin(String login);

    User addUser(String login, String passwordHash);
}
