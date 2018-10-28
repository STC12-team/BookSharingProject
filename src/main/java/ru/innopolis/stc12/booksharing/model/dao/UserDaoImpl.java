package ru.innopolis.stc12.booksharing.model.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.mapper.UserMapper;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private static Logger logger = Logger.getLogger(UserDaoImpl.class);
    private static final String SQL_SELECT_ALL =
        "SELECT u.id, u.login, u.password, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id";
    private static final String SQL_SELECT_USER_BY_LOGIN =
        "SELECT u.id, u.login, u.password, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.login = ?";

    public UserDaoImpl() {

    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserByLogin(String login) {
        logger.debug("Get user by login: " + login);
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_LOGIN, new Object[]{login}, new UserMapper());
    }

    @Override
    public List<User> getAllUsers() {
        logger.debug("Get all users");
        return jdbcTemplate.query(SQL_SELECT_ALL, new UserMapper());
    }
}
