package ru.innopolis.stc12.booksharing.model.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;
import ru.innopolis.stc12.booksharing.model.pojo.BookQueue;
import ru.innopolis.stc12.booksharing.model.pojo.BookQueueStatus;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookQueueMapper implements RowMapper<BookQueue> {

    @Override
    public BookQueue mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookEdition bookEdition = new BookEdition(
                rs.getInt(6),
                rs.getString(7),
                rs.getString(8),
                rs.getString(9),
                new Publisher(rs.getInt(12), rs.getString(13)),
                rs.getInt(11)
        );
        User user = new User(
                rs.getInt(14),
                rs.getString(15),
                rs.getString(16),
                rs.getInt(17),
                rs.getString(20),
                rs.getInt(18)
        );
        return new BookQueue(
                rs.getInt(1),
                bookEdition,
                user,
                rs.getTimestamp(4),
                BookQueueStatus.valueOf(rs.getString(5))
        );
    }
}
