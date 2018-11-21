package ru.innopolis.stc12.booksharing.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;
import ru.innopolis.stc12.booksharing.model.pojo.Publisher;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookEditionsDaoImplTest {

    @InjectMocks
    BookEditionsDaoImpl bookEditionsDao;

    @Mock
    SessionFactory sessionFactory;

    @Mock
    Session session;

    @Mock
    CriteriaBuilder criteriaBuilder;

    @Mock
    CriteriaQuery<BookEdition> criteriaQuery;

    @Mock
    Root<BookEdition> root;

    @Mock
    Query<BookEdition> query;

    @Mock
    Join<BookEdition, Publisher> join;

    @BeforeEach
    void setUp() {
        initMocks(this);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(BookEdition.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(BookEdition.class)).thenReturn(root);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
    }


    @Test
    void getBookEditionById() {
        BookEdition bookEdition = new BookEdition();
        List<BookEdition> list = new ArrayList<>();
        list.add(bookEdition);
        when(query.getResultList()).thenReturn(list);
        assertEquals(bookEdition, bookEditionsDao.getBookEditionById(6));
    }

    @Test
    void getAllBookEditions() {
        List<BookEdition> list = new ArrayList<>();
        when(query.getResultList()).thenReturn(list);
        assertEquals(list, bookEditionsDao.getAllBookEditions());
    }

    @Test
    void getBookEditionByIsbn() {
        BookEdition bookEdition = new BookEdition();
        List<BookEdition> list = new ArrayList<>();
        list.add(bookEdition);
        when(query.getResultList()).thenReturn(list);
        assertEquals(bookEdition, bookEditionsDao.getBookEditionByIsbn("isbn"));
    }


    @Test
    void getBookEditionsByPublisher() {
        List<BookEdition> list = new ArrayList<>();
        when(query.getResultList()).thenReturn(list);
        when(root.join("publisher", JoinType.LEFT)).thenAnswer(invocationOnMock -> join);
        assertEquals(list, bookEditionsDao.getBookEditionsByPublisher("publisher"));
    }

    @Test
    void addBookEdition() {
        BookEdition bookEdition = new BookEdition();
        assertTrue(bookEditionsDao.addBookEdition(bookEdition));
    }

    @Test
    void getBookEditionsBySearchValue() {
        List<BookEdition> list = new ArrayList<>();
        when(query.getResultList()).thenReturn(list);
        when(root.join("publisher", JoinType.LEFT)).thenAnswer(invocationOnMock -> join);
        assertEquals(list, bookEditionsDao.getBookEditionsBySearchValue("search"));
    }

    @Test
    void getBookEditionByTitle() {
        List<BookEdition> list = new ArrayList<>();
        when(query.getResultList()).thenReturn(list);
        assertEquals(list, bookEditionsDao.getBookEditionByTitle("title"));
    }
}