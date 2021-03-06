package ru.innopolis.stc12.booksharing.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;

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

class PublisherDaoImplTest {
    @InjectMocks
    PublisherDaoImpl publisherDao;
    @Mock
    SessionFactory sessionFactory;
    @Mock
    Session session;
    @Mock
    CriteriaBuilder criteriaBuilder;
    @Mock
    CriteriaQuery<Publisher> criteriaQuery;
    @Mock
    Root<Publisher> root;
    @Mock
    Query<Publisher> query;
    @Mock
    AbstractDaoImp.QueryObject queryObject;

    @BeforeEach
    void setUp() {
        initMocks(this);
        publisherDao.setClazz(Publisher.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Publisher.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Publisher.class)).thenReturn(root);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(criteriaQuery.where(any(Predicate.class))).thenReturn(criteriaQuery);
        queryObject = spy(publisherDao.new QueryObject());
    }

    @Test
    void getPublisherByName() {
        Publisher publisher = new Publisher();
        List<Publisher> list = new ArrayList<>();
        list.add(publisher);
        when(query.getResultList()).thenReturn(list);
        assertEquals(publisher, publisherDao.getPublisherByName("name"));
    }

    @Test
    void getPublisherByNameWithEmptyList() {
        List<Publisher> list = new ArrayList<>();
        when(query.getResultList()).thenReturn(list);
        assertNull(publisherDao.getPublisherByName("name"));
    }
}