package ru.innopolis.stc12.booksharing.model.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.mapper.UserMapper;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private static Logger logger = Logger.getLogger(UserDaoImpl.class);
  
    private static final int ROLE_USER_ID = 2;
    private static final String SQL_SELECT_ALL =
            "SELECT u.id, u.login, u.password, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id";
    private static final String SQL_SELECT_USER_BY_LOGIN =
            "SELECT u.id, u.login, u.password, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.login = ?";
    private static final String SQL_INSERT_USER =
        "INSERT INTO users (login, password, role_id, enabled) values (?,?,?,?)";

    public UserDaoImpl() {

    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserByLogin(String login) {
        logger.debug("Get user by login: " + login);
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_LOGIN, new Object[]{login}, new UserMapper());
        } catch (DataAccessException daException) {
            logger.debug("User not found: " + login);
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
    public User addUser(String login, String passwordHash) {
        logger.debug("Insert User login = " + login);
        jdbcTemplate.update(SQL_INSERT_USER,    login, passwordHash, ROLE_USER_ID, 1);
        return getUserByLogin(login);
    }
}
