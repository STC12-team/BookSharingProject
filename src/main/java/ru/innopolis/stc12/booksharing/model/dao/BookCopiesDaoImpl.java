package ru.innopolis.stc12.booksharing.model.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;

import java.io.Serializable;
import java.util.List;

@Repository
public class BookCopiesDaoImpl extends BaseDaoSupport implements BookCopiesDao {
    private JdbcTemplate jdbcTemplate;
    private static final String SQL_SELECT_COUNT_BY_BOOK_EDITION_ID =
            "select count(id) from book_copies where book_edition_id=?";
    private static final String SQL_SELECT_COUNT_BY_BOOK_EDITION_ID_IN_STATUS_FREE =
            "select count(id) from book_copies where book_edition_id=? and status='FREE'";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BookCopy getBookCopyById(int id) {
        return getHibernateTemplate().get(BookCopy.class, id);
    }

    @Override
    public Serializable addBookCopy(BookCopy bookCopy) {
        return getHibernateTemplate().save(bookCopy);
    }

    @Override
    public void updateBookCopy(BookCopy bookCopy) {
        getHibernateTemplate().update(bookCopy);
    }

    @Override
    public int getBookCopyCountByBookEditionIdInStatusFree(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_COUNT_BY_BOOK_EDITION_ID_IN_STATUS_FREE, new Object[]{id}, Integer.class);
    }

    @Override
    public int getBookCopyCountByBookEditionId(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_COUNT_BY_BOOK_EDITION_ID, new Object[]{id}, Integer.class);
    }
}
