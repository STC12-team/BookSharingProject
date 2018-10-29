package ru.innopolis.stc12.booksharing.model.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;
import ru.innopolis.stc12.booksharing.model.pojo.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookEditionMapper implements RowMapper<BookEdition> {
    @Override
    public BookEdition mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookEdition result = new BookEdition();
        result.setId(rs.getInt("b_id"));
        result.setIsbn(rs.getString("b_isbn"));
        result.setPublisher(new Publisher(rs.getInt("p_id"), rs.getString("p_name")));
        result.setTitle(rs.getString("b_title"));
        result.setDescription(rs.getString("b_description"));
        result.setYearOfPublication(rs.getInt("b_year"));
        return result;
    }
}
