package ru.innopolis.stc12.booksharing.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.mapper.UserMapper;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.util.List;

@Repository
public class UsersDaoImpl implements UsersDao {
    private JdbcTemplate jdbcTemplate;

    public UsersDaoImpl() {
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String s = bCryptPasswordEncoder.encode("1111");
        System.out.println(s);

    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserById(int id) {
        String getUserQuery = "select " +
                "u.id as u_id, " +
                "u.login as u_login, " +
                "u.password as u_password, " +
                "u.enabled as u_enabled, " +
                "r.id as r_id, " +
                "r.name as r_name " +
                "from users as u " +
                "inner join roles r on u.role_id = r.id " +
                "where u.id=?";
        List<User> list = jdbcTemplate.query(getUserQuery, new Object[]{id}, new UserMapper());
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<User> getAllUsers() {
        String getUserQuery = "select " +
                "u.id as u_id, " +
                "u.login as u_login, " +
                "u.password as u_password, " +
                "u.enabled as u_enabled, " +
                "r.id as r_id, " +
                "r.name as r_name " +
                "from users as u " +
                "inner join roles r on u.role_id = r.id ";
        return jdbcTemplate.query(getUserQuery, new UserMapper());
    }

    @Override
    public User getUserByLogin(String login) {
        String getUserQuery = "select " +
                "u.id as u_id, " +
                "u.login as u_login, " +
                "u.password as u_password, " +
                "u.enabled as u_enabled, " +
                "r.id as r_id, " +
                "r.name as r_name " +
                "from users as u " +
                "inner join roles r on u.role_id = r.id " +
                "where u.login=?";
        List<User> list = jdbcTemplate.query(getUserQuery, new Object[]{login}, new UserMapper());
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public boolean addUser(User user) {
        String getUserQuery = "insert into " +
                "users (login, password, role_id, enabled)" +
                " values (?,?,?,?)";
        int rows = jdbcTemplate.update(getUserQuery,
                user.getLogin(),
                user.getPassword(),
                user.getRoleId(),
                "true");
        if (rows <= 0) {
            return false;
        }
        return true;
    }

}
