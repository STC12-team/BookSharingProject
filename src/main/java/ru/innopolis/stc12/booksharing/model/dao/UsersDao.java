package ru.innopolis.stc12.booksharing.model.dao;

import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.util.List;

public interface UsersDao {
    User getUserById(int id);

    List<User> getAllUsers();

    User getUserByLogin(String isbn);

    boolean addUser(User bookCopies);
}
