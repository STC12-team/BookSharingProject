package ru.innopolis.stc12.booksharing.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class UserDaoImplTest {
    @InjectMocks
    UserDaoImpl userDao;
    @Mock
    SessionFactory sessionFactory;
    @Mock
    Session session;
    @Mock
    CriteriaBuilder criteriaBuilder;
    @Mock
    CriteriaQuery<User> criteriaQuery;
    @Mock
    Root<User> root;
    @Mock
    Query<User> query;
    @Mock
    AbstractDaoImp.QueryObject queryObject;

    @BeforeEach
    void setUp() {
        initMocks(this);
        userDao.setClazz(User.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(User.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(User.class)).thenReturn(root);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(criteriaQuery.where(any(Predicate.class))).thenReturn(criteriaQuery);
        queryObject = spy(userDao.new QueryObject());
    }

    @Test
    void getUserByLogin() {
        User user = new User();
        List<User> list = new ArrayList<>();
        list.add(user);
        when(query.getResultList()).thenReturn(list);
        assertEquals(user, userDao.getUserByLogin("login"));
    }

    @Test
    void getUserByLoginWithEmptyList() {
        List<User> list = new ArrayList<>();
        when(query.getResultList()).thenReturn(list);
        assertNull(userDao.getUserByLogin("login"));
    }

    @Test
    void getUserByEmail() {
        User user = new User();
        List<User> list = new ArrayList<>();
        list.add(user);
        when(query.getResultList()).thenReturn(list);
        assertEquals(user, userDao.getUserByEmail("email"));
    }

    @Test
    void getUserByEmailWithEmptyList() {
        List<User> list = new ArrayList<>();
        when(query.getResultList()).thenReturn(list);
        assertNull(userDao.getUserByEmail("email"));
    }
}
