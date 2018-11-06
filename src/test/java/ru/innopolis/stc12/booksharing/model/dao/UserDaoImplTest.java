package ru.innopolis.stc12.booksharing.model.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.stc12.booksharing.model.pojo.User;
import ru.innopolis.stc12.booksharing.model.pojo.UserDetails;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class UserDaoImplTest {

    @Mock
    UserDaoImpl userDao;

    @Mock
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        initMocks(this);

        userDao = new UserDaoImpl();
        jdbcTemplate = mock(JdbcTemplate.class);
        userDao.setJdbcTemplate(jdbcTemplate);
    }

    @Test
    void getUserByLogin() {
        User user = new User();
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(user);
        assertEquals(user, userDao.getUserByLogin("admin"));
    }

    @Test
    void getUserById() {
        User user = new User();
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(user);
        assertEquals(user, userDao.getUserById(1));
    }

    @Test
    void getAllUsers() {
        List<User> users = new ArrayList<>();
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(users);
        assertEquals(users, userDao.getAllUsers());
    }

    @Test
    void getUserDetails() {
        UserDetails userDetails = new UserDetails();
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(userDetails);
        assertEquals(null, userDao.getUserDetails());
    }
}
