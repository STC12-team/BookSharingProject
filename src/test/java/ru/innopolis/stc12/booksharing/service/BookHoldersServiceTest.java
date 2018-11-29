package ru.innopolis.stc12.booksharing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookHolder;
import ru.innopolis.stc12.booksharing.model.dao.implementation.BookHoldersDaoImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookHoldersServiceTest {
    private BookHoldersService bookHoldersService;
    @Mock
    private BookHoldersDaoImpl bookHoldersDao;
    @Mock
    private List<BookHolder> bookHolderList;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookHoldersService = new BookHoldersService();
        bookHoldersService.setBookHoldersDao(bookHoldersDao);
    }

    @Test
    void getBookHoldersByUserLogin() {
//        when(bookHoldersDao.getBookHoldersByUserLogin(anyString())).thenReturn(bookHolderList);
  //      assertEquals(bookHolderList, bookHoldersService.getBookHoldersByUserLogin(anyString()));
    }
}