package ru.innopolis.stc12.booksharing.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.mapper.PublisherMapper;
import ru.innopolis.stc12.booksharing.model.pojo.Publisher;

import java.util.List;

@Repository
public class PublisherDaoImpl implements PublisherDao {
    private JdbcTemplate jdbcTemplate;
    private static final String SQL_SELECT_BY_ID =
            "select id, name from publishers where id = ?";
    private static final String SQL_SELECT_BY_NAME =
            "select id, name from publishers where name like ?";
    private static final String SQL_SELECT_ALL =
            "select id, name from publishers";
    private static final String SQL_INSERT =
            "insert into publishers (name) values (?)";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Publisher getPublisherById(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, new PublisherMapper());
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return jdbcTemplate.query(SQL_SELECT_ALL, new PublisherMapper());
    }

    @Override
    public Publisher getPublisherByName(String name) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME, new Object[]{'%' + name + '%'}, new PublisherMapper());
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

    @Override
    public boolean addPublisher(Publisher publisher) {
        int rows = jdbcTemplate.update(SQL_INSERT, publisher.getName());
        return rows > 0;
    }
}
