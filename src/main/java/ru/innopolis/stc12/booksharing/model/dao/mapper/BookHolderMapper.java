package ru.innopolis.stc12.booksharing.model.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopiesStatus;
import ru.innopolis.stc12.booksharing.model.pojo.BookHolder;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookHolderMapper implements RowMapper<BookHolder> {
    @Override
    public BookHolder mapRow(ResultSet rs, int rowNum) throws SQLException {
        //TODO последствия того, что было лень писать длинный запрос
        BookEdition bookEdition = new BookEdition(
                rs.getInt(11),
                rs.getString(12),
                rs.getString(13),
                rs.getString(14),
                new Publisher(rs.getInt(17), rs.getString(18)),
                rs.getInt(16)
        );
        User owner = new User(
                rs.getInt(19),
                rs.getString(20),
                rs.getString(21),
                rs.getInt(24),
                rs.getString(25),
                rs.getInt(23)
        );
        BookCopy bookCopy = new BookCopy(
                rs.getInt(7),
                bookEdition,
                owner,
                BookCopiesStatus.valueOf(rs.getString(10))
        );
        User user = new User(
                rs.getInt(26),
                rs.getString(27),
                rs.getString(28),
                rs.getInt(31),
                rs.getString(32),
                rs.getInt(30)
        );
        return new BookHolder(
                rs.getInt(1),
                bookCopy,
                user,
                rs.getTimestamp(4),
                rs.getTimestamp(5),
                rs.getInt(6)
        );
    }
}
