package ru.innopolis.stc12.booksharing.model.dao;

import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.util.List;

//TODO своя реализация, на проверку, какую будем оставлять? (pojo надо будет доработать)
public interface UsersDao {
    User getUserById(int id);

    List<User> getAllUsers();

    User getUserByLogin(String login);

    boolean addUser(User bookCopies);
}
