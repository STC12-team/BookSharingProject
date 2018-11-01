package ru.innopolis.stc12.booksharing.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.mapper.BookQueueMapper;
import ru.innopolis.stc12.booksharing.model.pojo.BookQueue;

import java.util.List;

@Repository
public class BookQueueDaoImpl implements BookQueueDao {
    private static final String SQL_SELECT_BY_BOOK_EDITION_ID =
            "select * from book_queue as bq left join book_editions as be on bq.book_edition_id = be.id left join publishers as p on be.publisher_id = p.id left join users as u on bq.user_id = u.id left join roles as r on u.role_id = r.id where bq.book_edition_id=?";
    private static final String SQL_UPDATE =
            "update book_queue set book_edition_id=?, user_id=?, added_at=?, status=book_queue_status(?) where id=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BookQueue> getBookQueueByBookEditionId(Integer id) {
        return jdbcTemplate.query(SQL_SELECT_BY_BOOK_EDITION_ID, new Object[]{id}, new BookQueueMapper());
    }

    @Override
    public boolean updateBookQueue(BookQueue bookQueue) {
        int rows = jdbcTemplate.update(SQL_UPDATE,
                bookQueue.getBookEdition().getId(),
                bookQueue.getUser().getId(),
                bookQueue.getAddedAt(),
                bookQueue.getStatus().name(),
                bookQueue.getId());
        return rows > 0;
    }
}
