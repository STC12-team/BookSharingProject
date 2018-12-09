package ru.innopolis.stc12.booksharing.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookCopiesDaoImplTest {
    @InjectMocks
    private BookCopiesDaoImpl bookCopiesDao;
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private BookCopy bookCopy;
    @Mock
    private BookEdition bookEdition;
    @Mock
    private User user;
    @Mock
    private Session session;
    @Mock
    private AbstractDaoImp.QueryObject queryObject;
    @Mock
    private CriteriaBuilder criteriaBuilder;
    @Mock
    private CriteriaQuery<BookCopy> criteriaQuery;
    @Mock
    private Root<BookCopy> root;
    @Mock
    private Query<BookCopy> query;
    @Mock
    private Predicate predicate;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookCopiesDao.setClazz(BookCopy.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(BookCopy.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(BookCopy.class)).thenReturn(root);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(criteriaQuery.where(any(Predicate.class))).thenReturn(criteriaQuery);
        queryObject = spy(bookCopiesDao.new QueryObject());
    }

    @Test
    void findOne() {
        when(session.get(BookCopy.class, 1)).thenReturn(bookCopy);
        assertEquals(bookCopy, bookCopiesDao.findOne(1));
    }

    @Test
    void getBookCopiesOfBookEdition() {
        List<BookCopy> list = new ArrayList<>();
        list.add(bookCopy);
        when(root.get(anyString())).thenReturn(Mockito.mock(Path.class));
        when(criteriaBuilder.equal(any(Path.class), any(BookEdition.class))).thenReturn(predicate);
        when(queryObject.executeQuery(predicate)).thenReturn(list);
        assertEquals(list, bookCopiesDao.getBookCopiesOfBookEdition(bookEdition));
    }
}
