package ru.innopolis.stc12.booksharing.model.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

class UserDaoImplTest {

    @Mock
    UserDaoImpl userDao;

    @BeforeEach
    void setUp() {
        initMocks(this);

        userDao = new UserDaoImpl();
    }

    @Test
    void getUserByLogin() {
        User user = new User();
      //  when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(user);
//        assertEquals(user, userDao.getUserByLogin("admin"));
    }

    @Test
    void getUserById() {
        User user = new User();
 //       when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(user);
//        assertEquals(user, userDao.getUserById(1));
    }

    @Test
    void getAllUsers() {
        List<User> users = new ArrayList<>();
   //     when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(users);
//        assertEquals(users, userDao.getAllUsers());
    }

    @Test
    void getUserDetails() {
//        UserDetails userDetails = new UserDetails();
//        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(userDetails);
//        assertEquals(null, userDao.getUserDetails());
    }

    @Test
    void checkUserPasswordMatches() {
//        assertTrue(userDao.checkUserPasswordMatches(
  //              "$2a$06$KZiZRTRUvXjPJ0vWFZj7beAKsGy0AGGCKZtzGUnKkcl46bHX5dgMG", "sa"));
    }

    @Test
    void updateUserDetails() {
//        UserDetails userDetails = new UserDetails(1, 1, "firstname", "lastname", "surname", "admin@example.com", "secret");
//        when(jdbcTemplate.update(
//                "",
//                userDetails.getFirstName(),
//                userDetails.getLastName(),
//                userDetails.getSurname(),
//                userDetails.getUserId())).thenReturn(1);
//        assertFalse(userDao.updateUserDetails(userDetails));
    }
}
