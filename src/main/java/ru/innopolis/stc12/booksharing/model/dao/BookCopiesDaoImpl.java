package ru.innopolis.stc12.booksharing.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.mapper.BookCopyMapper;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopy;

import java.util.List;

@Repository
public class BookCopiesDaoImpl implements BookCopiesDao {
    private JdbcTemplate jdbcTemplate;
    private static final String SQL_SELECT_BY_ID =
            "select bc.id as bc_id, be.id as be_id, be.isbn as be_isbn, be.title as be_title, be.description as be_description, be.year_of_publication as be_year, p.id as p_id, p.name as p_name, u.id as u_id, u.login as u_login, u.password as u_password, u.enabled as u_enabled, r.id as r_id, r.name as r_name from book_copies as bc inner join book_editions as be on bc.book_edition_id=be.id inner join publishers as p on be.publisher_id = p.id inner join users as u on bc.owner_id=u.id inner join roles r on u.role_id = r.id where bc.id=?";
    private static final String SQL_SELECT_ALL =
            "select bc.id as bc_id, be.id as be_id, be.isbn as be_isbn, be.title as be_title, be.description as be_description, be.year_of_publication as be_year, p.id as p_id, p.name as p_name, u.id as u_id, u.login as u_login, u.password as u_password, u.enabled as u_enabled, r.id as r_id, r.name as r_name from book_copies as bc inner join book_editions as be on bc.book_edition_id=be.id inner join publishers as p on be.publisher_id = p.id inner join users as u on bc.owner_id=u.id inner join roles r on u.role_id = r.id";
    private static final String SQL_SELECT_BY_ISBN =
            "select bc.id as bc_id, be.id as be_id, be.isbn as be_isbn, be.title as be_title, be.description as be_description, be.year_of_publication as be_year, p.id as p_id, p.name as p_name, u.id as u_id, u.login as u_login, u.password as u_password, u.enabled as u_enabled, r.id as r_id, r.name as r_name from book_copies as bc inner join book_editions as be on bc.book_edition_id=be.id inner join publishers as p on be.publisher_id = p.id inner join users as u on bc.owner_id=u.id inner join roles r on u.role_id = r.id where bc.isbn like ?";
    private static final String SQL_INSERT =
            "insert into book_copies (book_edition_id, owner_id) values (?,?)";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public BookCopy getBookCopiesById(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, new BookCopyMapper());
    }

    @Override
    public List<BookCopy> getAllBookCopies() {
        return jdbcTemplate.query(SQL_SELECT_ALL, new BookCopyMapper());
    }

    @Override
    public BookCopy getBookCopiesByIsbn(String isbn) {
        //TODO если передать ISBN, которого нет в базе, вылетает исключение
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ISBN, new Object[]{isbn}, new BookCopyMapper());
    }

    @Override
    public boolean addBookCopies(BookCopy bookCopy) {
        int rows = jdbcTemplate.update(SQL_INSERT, bookCopy.getBookEdition().getId(), bookCopy.getUser().getId());
        return rows > 0;
    }
}
