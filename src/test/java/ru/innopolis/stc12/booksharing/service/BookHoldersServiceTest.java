package ru.innopolis.stc12.booksharing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookHolder;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.UserDao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookHoldersServiceTest {
    @InjectMocks
    private BookHoldersService bookHoldersService;
    @Mock
    private UserDao<User> userDao;
    @Mock
    private User user;
    @Mock
    private List<BookHolder> bookHolderList;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void getBookHoldersByUserLogin() {
        when(userDao.getUserByLogin("UserLogin")).thenReturn(user);
        when(user.getBookHolders()).thenReturn(bookHolderList);
        assertEquals(bookHolderList, bookHoldersService.getBookHoldersByUserLogin("UserLogin"));
    }
}