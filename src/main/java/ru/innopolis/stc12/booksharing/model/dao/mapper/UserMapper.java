package ru.innopolis.stc12.booksharing.model.dao.mapper;

import ru.innopolis.stc12.booksharing.model.pojo.User;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getLong("id"), rs.getString("login"), rs.getString("password"), rs.getString("role"));
    }
}