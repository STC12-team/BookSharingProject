package ru.innopolis.stc12.booksharing.model.dao.implementation;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.BookCopiesDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;

@Repository
public class BookCopiesDaoImpl extends AbstractDaoImp implements BookCopiesDao {
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_COUNT_BY_BOOK_EDITION_ID_IN_STATUS_FREE =
            "select count(id) from book_copies where book_edition_id=? and status='FREE'";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getBookCopyCountByBookEditionIdInStatusFree(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_COUNT_BY_BOOK_EDITION_ID_IN_STATUS_FREE, new Object[]{id}, Integer.class);
    }

}
