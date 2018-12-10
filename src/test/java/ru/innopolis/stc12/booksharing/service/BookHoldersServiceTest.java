package ru.innopolis.stc12.booksharing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookHolder;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookHoldersDao;
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
    @Mock
    private BookHoldersDao<BookHolder> bookHoldersDao;
    @Mock
    private BookHolder bookHolder;
    @Mock
    private BookCopy bookCopy;
    @Mock
    private BookEdition bookEdition;

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

    @Test
    void getById() {
        when(bookHoldersDao.findOne(1)).thenReturn(bookHolder);
        when(bookHolder.getBookCopy()).thenReturn(bookCopy);
        when(bookCopy.getBookEdition()).thenReturn(bookEdition);
        assertEquals(bookHolder, bookHoldersService.getById(1));
    }

    @Test
    void update() {
        when(bookHoldersDao.update(bookHolder)).thenReturn(bookHolder);
        assertEquals(bookHolder, bookHoldersService.update(bookHolder));
    }
}