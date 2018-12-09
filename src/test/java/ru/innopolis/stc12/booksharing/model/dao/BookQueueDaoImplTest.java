package ru.innopolis.stc12.booksharing.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookQueue;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookQueueDaoImplTest {
    @InjectMocks
    BookQueueDaoImpl bookQueueDao;
    @Mock
    private User user;
    @Mock
    SessionFactory sessionFactory;
    @Mock
    Session session;
    @Mock
    CriteriaBuilder criteriaBuilder;
    @Mock
    CriteriaQuery<BookQueue> criteriaQuery;
    @Mock
    Root<BookQueue> root;
    @Mock
    Query<BookQueue> query;
    @Mock
    AbstractDaoImp.QueryObject queryObject;
    @Mock
    private BookEdition bookEdition;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookQueueDao.setClazz(BookQueue.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(BookQueue.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(BookQueue.class)).thenReturn(root);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(criteriaQuery.where(any(Predicate.class))).thenReturn(criteriaQuery);
        queryObject = spy(bookQueueDao.new QueryObject());
    }

    @Test
    void getUserBookQueueByBookEdition() {
        BookQueue bookQueue = new BookQueue();
        List<BookQueue> list = new ArrayList<>();
        list.add(bookQueue);
        when(query.getResultList()).thenReturn(list);
        assertEquals(bookQueue, bookQueueDao.getUserBookQueueByBookEdition(user, bookEdition));
    }
}