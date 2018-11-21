package ru.innopolis.stc12.booksharing.model.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopiesStatus;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopy;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookCopyMapper implements RowMapper<BookCopy> {
    @Override
    public BookCopy mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookCopy result = new BookCopy();
        BookEdition bookEdition = new BookEdition();
        bookEdition.setId(rs.getInt("be_id"));
        bookEdition.setIsbn(rs.getString("be_isbn"));
        bookEdition.setPublisher(new Publisher(rs.getInt("p_id"), rs.getString("p_name")));
        bookEdition.setTitle(rs.getString("be_title"));
        bookEdition.setDescription(rs.getString("be_description"));
        bookEdition.setYearOfPublication(rs.getInt("be_year"));
        User user = new User();
        user.setId(rs.getInt("u_id"));
        user.setLogin(rs.getString("u_login"));
        user.setPassword(rs.getString("u_password"));
        user.setRole(rs.getString("r_name"));
        user.setRoleId(rs.getInt("r_id"));
        result.setId(rs.getInt("bc_id"));
        result.setBookEdition(bookEdition);
        result.setUser(user);
        result.setStatus(BookCopiesStatus.valueOf(rs.getString("bc_status")));
        return result;
    }
}
