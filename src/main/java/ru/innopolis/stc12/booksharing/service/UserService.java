package ru.innopolis.stc12.booksharing.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.model.dao.UserDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.pojo.UserDetails;

import java.util.List;

@Service
public class UserService {

    public boolean checkUserPasswordMatches(String currentPassword, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(password, currentPassword);
    }




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

    public List<User> getUsers() {
        return this.userDao.findAll();
    }

    public User getUserByLogin(String login) {
        User user = null;
        if (login != null && !login.isEmpty()) {
            user = this.userDao.getUserByLogin(login);
        }
        return user;
    }

    public User addUser(String login, String password) {
        String cryptPassword = bCryptPasswordEncoder.encode(password);
        return userDao.addUser(login, cryptPassword);
    }

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
        return checkUserPasswordMatches(currentPassword, password);
    }

    public boolean updateUserDetails(String firstName, String lastName, String surname) {
        UserDetails currentUserDetails = getAuthenticatedUserDetails();
        if (currentUserDetails == null) {
            return false;
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

        return userDao.updateUserDetails(currentUserDetails);
    }
}
