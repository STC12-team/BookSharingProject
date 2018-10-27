package ru.innopolis.stc12.booksharing.model.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookEditionMapper implements RowMapper<BookEdition> {
    @Override
    public BookEdition mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BookEdition(rs.getString("title"),
                rs.getString("description"),
                rs.getString("isbn"));

    }
}
