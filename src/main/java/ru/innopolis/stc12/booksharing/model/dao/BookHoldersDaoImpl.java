package ru.innopolis.stc12.booksharing.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.mapper.BookHolderMapper;
import ru.innopolis.stc12.booksharing.model.pojo.BookHolder;

import java.util.List;

@Repository
public class BookHoldersDaoImpl implements BookHoldersDao {
    //TODO было лень писать длинный запрос...
    private static final String SQL_SELECT_BY_USER_LOGIN = "select * from book_holders as bh left join book_copies as bc on bh.book_copy_id = bc.id left join book_editions as be on bc.book_edition_id = be.id left join publishers as p on be.publisher_id = p.id left join users as u on bc.owner_id = u.id left join roles as r on u.role_id = r.id left join users as u2 on bh.user_id = u2.id left join roles as r2 on u2.role_id = r2.id where u2.login = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BookHolder> getBookHoldersByUserLogin(String login) {
        return jdbcTemplate.query(SQL_SELECT_BY_USER_LOGIN, new Object[]{login}, new BookHolderMapper());
    }
}
