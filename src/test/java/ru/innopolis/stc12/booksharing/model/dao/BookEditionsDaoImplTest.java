package ru.innopolis.stc12.booksharing.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookEditionsDaoImplTest {
    @InjectMocks
    private BookEditionsDaoImpl bookEditionsDao;
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private BookEdition bookEdition;
    @Mock
    private Session session;
    @Mock
    private AbstractDaoImp.QueryObject queryObject;
    @Mock
    private CriteriaBuilder criteriaBuilder;
    @Mock
    private CriteriaQuery<BookEdition> criteriaQuery;
    @Mock
    private Root<BookEdition> root;
    @Mock
    private Query<BookEdition> query;
    @Mock
    private Predicate predicate;
    @Mock
    Join<BookEdition, Publisher> join;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookEditionsDao.setClazz(BookEdition.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(BookEdition.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(BookEdition.class)).thenReturn(root);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(criteriaQuery.where(any(Predicate.class))).thenReturn(criteriaQuery);
        queryObject = spy(bookEditionsDao.new QueryObject());
    }

    @Test
    void getBookEditionsByPublisher() {
        List<BookEdition> list = new ArrayList<>();
        when(query.getResultList()).thenReturn(list);
        when(root.join("publisher", JoinType.LEFT)).thenAnswer(invocationOnMock -> join);
        assertEquals(list, bookEditionsDao.getBookEditionsByPublisher("publisher"));
    }

    @Test
    void getBookEditionByIsbn() {
        List<BookEdition> list = new ArrayList<>();
        list.add(bookEdition);
        when(query.getResultList()).thenReturn(list);
        assertEquals(bookEdition, bookEditionsDao.getBookEditionByIsbn("isbn"));
        list = new ArrayList<>();
        when(query.getResultList()).thenReturn(list);
        assertEquals(null, bookEditionsDao.getBookEditionByIsbn("isbn"));
    }

    @Test
    void getBookEditionByTitle() {
        List<BookEdition> list = new ArrayList<>();
        when(query.getResultList()).thenReturn(list);
        assertEquals(list, bookEditionsDao.getBookEditionByTitle("title"));
    }

    @Test
    void getBookEditionsBySearchValue() {
        List<BookEdition> list = new ArrayList<>();
        when(query.getResultList()).thenReturn(list);
        when(root.join("publisher", JoinType.LEFT)).thenAnswer(invocationOnMock -> join);
        assertEquals(list, bookEditionsDao.getBookEditionsBySearchValue("search"));
    }

    @Test
    void getBookCopiesByBookEditionId() {
        List<BookCopy> bookCopyList = new ArrayList<>();
        when(bookEdition.getBookCopies()).thenReturn(bookCopyList);
        when(session.load(BookEdition.class, 1)).thenReturn(bookEdition);
        assertEquals(bookCopyList, bookEditionsDao.getBookCopiesByBookEditionId(1));
    }
}