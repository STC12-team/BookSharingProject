package ru.innopolis.stc12.booksharing.model.dao;

import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.pojo.User;
import ru.innopolis.stc12.booksharing.model.pojo.UserDetails;

import java.util.List;

public interface UserDao {
    User getUserById(int id);

    List<BookCopy> getAllBookCopy();

    List<User> getAllUsers();

    User getUserByLogin(String login);

    /**
     * Get user details for current authenticated user
     *
     * @return UserDetails
     */
    UserDetails getUserDetails();

    User addUser(String login, String passwordHash);

    boolean checkUserPasswordMatches(String currentPassword, String password);

    boolean updateUserDetails(UserDetails userDetails);
}
