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
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopiesStatus;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookCopiesDaoImplTest {

    @InjectMocks
    private BookCopiesDaoImpl bookCopiesDao;
    @Mock
    private JdbcTemplate jdbcTemplate;
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
        BookCopy bookCopy = new BookCopy();
        when(session.get(BookCopy.class, 1)).thenReturn(bookCopy);
//todo        assertEquals(bookCopy, bookCopiesDao.findOne(1));
    }

    @Test
    void addBookCopiesWhenFalse() {
        when(jdbcTemplate.update(anyString(), any(Object.class))).thenReturn(0);
        when(bookCopy.getBookEdition()).thenReturn(bookEdition);
//        when(bookCopy.getOwner()).thenReturn(user);
        when(bookEdition.getId()).thenReturn(1);
        when(user.getId()).thenReturn((int) 1);
        when(bookCopy.getBookEdition().getId()).thenReturn(1);
//        when(bookCopy.getOwner().getId()).thenReturn((int) 1);
        when(bookCopy.getStatus()).thenReturn(BookCopiesStatus.FREE);
//todo        bookCopiesDao.save(bookCopy);
//todo        verify(bookCopy, times()).
    }

    @Test
    void getBookCopyCountByBookEditionId() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(Integer.class))).thenReturn(1);
//        assertEquals(1, bookCopiesDao.getBookCopyCountByBookEditionId(1));
    }

    @Test
    void getBookCopiesOfBookEdition() {
        List<BookCopy> list = new ArrayList<>();
        list.add(bookCopy);
        when(root.get(anyString())).thenReturn(Mockito.mock(Path.class));
        when(criteriaBuilder.equal(any(Path.class), any(BookEdition.class))).thenReturn(predicate);
        when(queryObject.executeQuery(predicate)).thenReturn(list);
        List<BookCopy> list2 = bookCopiesDao.getBookCopiesOfBookEdition(bookEdition);
        assertEquals(list, list2);
    }
}
