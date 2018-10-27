package ru.innopolis.stc12.booksharing.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.mapper.BookEditionMapper;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;

import java.util.List;

@Repository
public class BookEditionsDaoImpl implements BookEditionsDao {
    private JdbcTemplate jdbcTemplate;

    public BookEditionsDaoImpl() {
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BookEdition getBookEditionById(int id) {
        String getBookEditionsQuery = "select * from book_editions where id=?";
        return jdbcTemplate.queryForObject(getBookEditionsQuery,
                new Object[]{id}, new BookEditionMapper());
    }

    @Override
    public List<BookEdition> getAllBookEditions() {
        String getBookEditionsQuery = "select * from book_editions";
        return jdbcTemplate.query(getBookEditionsQuery, new BookEditionMapper());
    }

    @Override
    public BookEdition getBookEditionByIsbn(String isbn) {
        String getBookEditionsQuery = "select * from book_editions where isbn=?";
        return jdbcTemplate.queryForObject(getBookEditionsQuery,
                new Object[]{isbn}, new BookEditionMapper());
    }

    @Override
    public void addBookEdition(BookEdition bookEdition) {
        String addBookEditionQuery = "insert into book_editions (title, description, isbn)" +
                " values (?,?,?)";
        jdbcTemplate.update(addBookEditionQuery, bookEdition.getTitle(), bookEdition.getDescription(),
                bookEdition.getIsbn());
    }
}
