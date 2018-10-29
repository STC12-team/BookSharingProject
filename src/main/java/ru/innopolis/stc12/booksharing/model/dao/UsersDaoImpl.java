package ru.innopolis.stc12.booksharing.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.mapper.UserMapper;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.util.List;

//TODO своя реализация, на проверку, какую будем оставлять? (pojo надо будет доработать)
@Repository
public class UsersDaoImpl implements UsersDao {
    private JdbcTemplate jdbcTemplate;
    private static final String SQL_SELECT_BY_ID =
            "select u.id as u_id, u.login as u_login, u.password as u_password, u.enabled as u_enabled, r.id as r_id, r.name as r_name from users as u inner join roles r on u.role_id = r.id where u.id=?";
    private static final String SQL_SELECT_ALL =
            "select u.id as u_id, u.login as u_login, u.password as u_password, u.enabled as u_enabled, r.id as r_id, r.name as r_name from users as u inner join roles r on u.role_id = r.id ";
    private static final String SQL_SELECT_BY_LOGIN =
            "select u.id as u_id, u.login as u_login, u.password as u_password, u.enabled as u_enabled, r.id as r_id, r.name as r_name from users as u inner join roles r on u.role_id = r.id where u.login=?";
    private static final String SQL_INSERT =
            "insert into users (login, password, role_id, enabled) values (?,?,?,?)";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserById(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, new UserMapper());
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_SELECT_ALL, new UserMapper());
    }

    @Override
    public User getUserByLogin(String login) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_LOGIN, new Object[]{login}, new UserMapper());
    }

    @Override
    public boolean addUser(User user) {
        int rows = jdbcTemplate.update(SQL_INSERT, user.getLogin(), user.getPassword(), user.getRoleId(), "true");
        return rows > 0;
    }

}
