package ru.innopolis.stc12.booksharing.model.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherMapper implements RowMapper<Publisher> {
    @Override
    public Publisher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Publisher result = new Publisher();
        result.setId(rs.getInt("id"));
        result.setName(rs.getString("name"));
        return result;
    }
}
