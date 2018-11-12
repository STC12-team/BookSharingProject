package ru.innopolis.stc12.booksharing.model.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.mapper.BookEditionMapper;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookEditionsDaoImpl implements BookEditionsDao {
    private JdbcTemplate jdbcTemplate;
    private static Logger logger = Logger.getLogger(BookEditionsDaoImpl.class);
    private static final String SQL_SELECT_BY_ID =
            "select b.id as b_id, b.isbn as b_isbn, b.title as b_title, b.description as b_description, b.year_of_publication as b_year, p.id as p_id, p.name as p_name from book_editions as b inner join publishers as p on b.publisher_id = p.id where b.id=?";
    private static final String SQL_SELECT_ALL =
            "select b.id as b_id, b.isbn as b_isbn, b.title as b_title, b.description as b_description, b.year_of_publication as b_year, p.id as p_id, p.name as p_name from book_editions as b inner join publishers as p on b.publisher_id = p.id";
    private static final String SQL_SELECT_BY_ISBN =
            "select b.id as b_id, b.isbn as b_isbn, b.title as b_title, b.description as b_description, b.year_of_publication as b_year, p.id as p_id, p.name as p_name from book_editions as b inner join publishers as p on b.publisher_id = p.id where b.isbn like ?";
    private static final String SQL_INSERT =
            "insert into book_editions (isbn, publisher_id, title, description, year_of_publication) values (?,?,?,?,?)";
    private static final String SQL_SELECT_BY_TITLE =
            "select b.id as b_id, b.isbn as b_isbn, b.title as b_title, b.description as b_description, b.year_of_publication as b_year, p.id as p_id, p.name as p_name from book_editions as b inner join publishers as p on b.publisher_id = p.id where b.title like ?";

    private static final String SQL_SELECT_BY_PUBLISHER =
            "select b.id as b_id, b.isbn as b_isbn, b.title as b_title, b.description as b_description, b.year_of_publication as b_year, p.id as p_id, p.name as p_name from book_editions as b inner join publishers as p on b.publisher_id = p.id where p.name like ?";

    private static final String SQL_SELECT_BY_SEARCH_VALUE =
            "select b.id as b_id, b.isbn as b_isbn, b.title as b_title, b.description as b_description, b.year_of_publication as b_year, p.id as p_id, p.name as p_name from book_editions as b inner join publishers as p on b.publisher_id = p.id where b.title like ? OR b.isbn like ? OR p.name like ?";


    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BookEdition getBookEditionById(int id) {
        BookEdition bookEdition;
        try {
            bookEdition = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, new BookEditionMapper());
        } catch (DataAccessException daException) {
            logger.debug("BookEdition not found by id: " + id);
            return null;
        }
        return bookEdition;
    }

    @Override
    public List<BookEdition> getAllBookEditions() {
        return jdbcTemplate.query(SQL_SELECT_ALL, new BookEditionMapper());
    }

    @Override
    public BookEdition getBookEditionByIsbn(String isbn) {
        BookEdition bookEdition;
        try {
            bookEdition = jdbcTemplate.queryForObject(SQL_SELECT_BY_ISBN, new Object[]{isbn}, new BookEditionMapper());
        } catch (DataAccessException daException) {
            logger.debug("BookEdition not found by isbn: " + isbn);
            return null;
        }
        return bookEdition;
    }

    @Override
    public boolean addBookEdition(BookEdition bookEdition) {
        int rows = jdbcTemplate.update(SQL_INSERT,
                bookEdition.getIsbn(),
                bookEdition.getPublisher().getId(),
                bookEdition.getTitle(),
                bookEdition.getDescription(),
                bookEdition.getYearOfPublication());
        return rows > 0;
    }

    @Override
    public List<BookEdition> getBookEditionByTitle(String title) {
        return jdbcTemplate.query(SQL_SELECT_BY_TITLE, new Object[]{'%' + title + '%'}, new BookEditionMapper());
    }

    @Override
    public List<BookEdition> getBookEditionsByPublisher(String publisher) {
        try {
            return jdbcTemplate.query(SQL_SELECT_BY_PUBLISHER, new Object[]{publisher}, new BookEditionMapper());
        } catch (DataAccessException daException) {
            logger.debug("BookEdition not found by publisher: " + publisher);
            return new ArrayList<>();
        }
    }

    @Override
    public List<BookEdition> getBookEditionsBySearchValue(String searchValue) {
        try {
            return jdbcTemplate.query(SQL_SELECT_BY_SEARCH_VALUE, new Object[]{'%' + searchValue + '%', '%' + searchValue + '%', '%' + searchValue + '%'}, new BookEditionMapper());
        } catch (DataAccessException daException) {
            logger.debug("BookEditions not found by searchValue: " + searchValue);
            return new ArrayList<>();
        }
    }
}
