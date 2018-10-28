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
        String getBookEditionsQuery = "select " +
                "b.id as b_id, " +
                "b.isbn as b_isbn, " +
                "b.title as b_title, " +
                "b.description as b_description, " +
                "b.year_of_publication as b_year, " +
                "p.id as p_id, " +
                "p.name as p_name " +
                "from book_editions as b " +
                "inner join publishers as p on b.publisher_id = p.id " +
                "where b.id=?";
        List<BookEdition> list = jdbcTemplate.query(getBookEditionsQuery, new Object[]{id}, new BookEditionMapper());
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<BookEdition> getAllBookEditions() {
        String getBookEditionsQuery = "select " +
                "b.id as b_id, " +
                "b.isbn as b_isbn, " +
                "b.title as b_title, " +
                "b.description as b_description, " +
                "b.year_of_publication as b_year, " +
                "p.id as p_id, " +
                "p.name as p_name " +
                "from book_editions as b " +
                "inner join publishers as p on b.publisher_id = p.id";
        return jdbcTemplate.query(getBookEditionsQuery, new BookEditionMapper());
    }

    @Override
    public BookEdition getBookEditionByIsbn(String isbn) {
        String getBookEditionsQuery = "select " +
                "b.id as b_id, " +
                "b.isbn as b_isbn, " +
                "b.title as b_title, " +
                "b.description as b_description, " +
                "b.year_of_publication as b_year, " +
                "p.id as p_id, " +
                "p.name as p_name " +
                "from book_editions as b " +
                "inner join publishers as p on b.publisher_id = p.id " +
                "where b.isbn=?";
        List<BookEdition> list = jdbcTemplate.query(getBookEditionsQuery, new Object[]{isbn}, new BookEditionMapper());
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public boolean addBookEdition(BookEdition bookEdition) {
        String addBookEditionQuery = "insert into " +
                "book_editions (isbn, publisher_id, title, description, year_of_publication)" +
                " values (?,?,?,?,?)";
        int rows = jdbcTemplate.update(addBookEditionQuery,
                bookEdition.getIsbn(),
                bookEdition.getPublisher().getId(),
                bookEdition.getTitle(),
                bookEdition.getDescription(),
                bookEdition.getYearOfPublication());
        if (rows <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public List<BookEdition> getBookEditionByTitle(String title) {
        String getBookEditionsQuery = "select " +
                "b.id as b_id, " +
                "b.isbn as b_isbn, " +
                "b.title as b_title, " +
                "b.description as b_description, " +
                "b.year_of_publication as b_year, " +
                "p.id as p_id, " +
                "p.name as p_name " +
                "from book_editions as b " +
                "inner join publishers as p on b.publisher_id = p.id " +
                "where b.title like ?";
        return jdbcTemplate.query(getBookEditionsQuery, new Object[]{'%' + title + '%'}, new BookEditionMapper());
    }
}
