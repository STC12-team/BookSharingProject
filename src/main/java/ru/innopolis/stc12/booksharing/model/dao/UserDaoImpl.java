package ru.innopolis.stc12.booksharing.model.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.mapper.UserDetailsMapper;
import ru.innopolis.stc12.booksharing.model.dao.mapper.UserMapper;
import ru.innopolis.stc12.booksharing.model.pojo.User;
import ru.innopolis.stc12.booksharing.model.pojo.UserDetails;

import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private static Logger logger = Logger.getLogger(UserDaoImpl.class);

    private static final int ROLE_USER_ID = 2;
    private static final int USER_ENABLED = 1;
    private static final String SQL_SELECT_BY_ID =
            "select u.id as u_id, u.login as u_login, u.password as u_password, u.enabled as u_enabled, r.id as r_id, r.name as r_name from users as u inner join roles r on u.role_id = r.id where u.id=?";
    private static final String SQL_SELECT_ALL =
            "select u.id as u_id, u.login as u_login, u.password as u_password, u.enabled as u_enabled, r.id as r_id, r.name as r_name from users as u inner join roles r on u.role_id = r.id ";
    private static final String SQL_SELECT_BY_LOGIN =
            "select u.id, u.login, u.password, u.enabled, u.role_id, r.name as role_name from users as u inner join roles r on u.role_id = r.id where u.login=?";
    private static final String SQL_INSERT =
            "insert into users (login, password, role_id, enabled) values (?,?,?,?)";
    private static final String SQL_SELECT_USER_DETAILS =
            "select d.id, d.user_id, d.firstname, d.surname, d.lastname, u.email, u.password from users as u inner join user_details as d on u.id = d.user_id where d.user_id = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserById(int id) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, new UserMapper());
        } catch (DataAccessException daException) {
            logger.debug("User not found by id: " + id);
            return null;
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        logger.debug("Get all users");
        return jdbcTemplate.query(SQL_SELECT_ALL, new UserMapper());
    }

    @Override
    public User getUserByLogin(String login) {
        logger.debug("Get user by login: " + login);
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_LOGIN, new Object[]{login}, new UserMapper());
        } catch (DataAccessException daException) {
            logger.debug("User not found: " + login);
            return null;
        }

        return user;
    }

    @Override
    public UserDetails getUserDetails() {
        User currentUser;
        UserDetails authenticatedUserDetails = null;

        try {
            String authenticatedUserLogin = Objects.requireNonNull(UserDaoImpl.getCurrentUserDetails()).getUsername();
            currentUser = getUserByLogin(authenticatedUserLogin);

            authenticatedUserDetails = jdbcTemplate.queryForObject(
                    SQL_SELECT_USER_DETAILS,
                    new Object[]{currentUser.getId()},
                    new UserDetailsMapper());
        } catch (NullPointerException | DataAccessException e) {
            logger.error("Cannot find authenticated user details");
        }

        return authenticatedUserDetails;
    }


    /**
     * Get authenticated user from session helper
     *
     * @return User
     */
    private static org.springframework.security.core.userdetails.User getCurrentUserDetails(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            return (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        }
        return null;
    }

    @Override
    public User addUser(String login, String passwordHash) {
        logger.debug("Insert User login = " + login);
        jdbcTemplate.update(SQL_INSERT, login, passwordHash, ROLE_USER_ID, USER_ENABLED);
        return getUserByLogin(login);
    }

    @Override
    public boolean checkUserPasswordMatches(String currentPassword, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(password, currentPassword);
    }
}
