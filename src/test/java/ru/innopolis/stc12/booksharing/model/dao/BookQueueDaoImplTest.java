package ru.innopolis.stc12.booksharing.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookQueue;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.pojo.BookQueueStatus;

import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookQueueDaoImplTest {
    @InjectMocks
    private BookQueueDaoImpl bookQueueDao;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private List<BookQueue> bookQueueList;
    @Mock
    private BookQueue bookQueue;
    @Mock
    private Timestamp timestamp;
    @Mock
    private BookEdition bookEdition;
    @Mock
    private User user;
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private AbstractDaoImp.QueryObject queryObject;
    @Mock
    private CriteriaBuilder criteriaBuilder;
    @Mock
    private CriteriaQuery<BookQueue> criteriaQuery;
    @Mock
    private Root<BookQueue> root;
    @Mock
    private Query<BookQueue> query;
    @Mock
    private Predicate predicate;

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
    void getBookQueueByBookEditionId() {
//        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(bookQueueList);
//        assertEquals(bookQueueList, bookQueueDao.getBookQueueByBookEditionId(1));
    }

    @Test
    void updateBookQueue() {
        //TODO переделать
        when(bookQueue.getBookEdition()).thenReturn(bookEdition);
        when(bookQueue.getUser()).thenReturn(user);
        when(bookQueue.getStatus()).thenReturn(BookQueueStatus.WAIT);
        when(bookEdition.getId()).thenReturn(1);
        when(user.getId()).thenReturn((int) 1);
        when(bookQueue.getAddedAt()).thenReturn(timestamp);
        when(bookQueue.getId()).thenReturn(1);
        when(jdbcTemplate.update(anyString(), anyInt(), anyInt(), any(), anyString(), anyInt())).thenReturn(0);
//        assertEquals(false, bookQueueDao.updateBookQueue(bookQueue));
    }

    @Test
    void getUserCountByBookEditionId() {
//        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(Integer.class))).thenReturn(1);
//        assertEquals(1, bookQueueDao.getUserCountByBookEditionId(1));
    }

    @Test
    void getUserBookQueueByBookEdition() {
        List<BookQueue> list = new ArrayList<>();
        list.add(bookQueue);
        when(root.get(anyString())).thenReturn(Mockito.mock(Path.class));
        when(criteriaBuilder.equal(any(Path.class), any(BookEdition.class))).thenReturn(predicate);
        when(queryObject.executeQuery(predicate)).thenReturn(list);
        BookQueue result = bookQueueDao.getUserBookQueueByBookEdition(user, bookEdition);
        assertEquals(bookQueue, result);
    }
}