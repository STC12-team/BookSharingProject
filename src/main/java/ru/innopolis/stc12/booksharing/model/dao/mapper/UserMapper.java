package ru.innopolis.stc12.booksharing.model.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.stc12.booksharing.model.pojo.Role;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User result = new User();
        result.setId(rs.getInt("u_id"));
        result.setLogin(rs.getString("u_login"));
        result.setPassword(rs.getString("u_password"));
        result.setRole(new Role(rs.getInt("r_id"), rs.getString("r_name")));
        result.setEnabled(rs.getString("u_enabled"));
        return result;
    }
}
