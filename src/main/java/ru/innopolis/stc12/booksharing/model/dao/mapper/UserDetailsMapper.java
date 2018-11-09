package ru.innopolis.stc12.booksharing.model.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.stc12.booksharing.model.pojo.UserDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsMapper implements RowMapper<UserDetails> {
    @Override
    public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserDetails(rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("surname"),
                rs.getString("email"),
                rs.getString("password"));
    }
}
