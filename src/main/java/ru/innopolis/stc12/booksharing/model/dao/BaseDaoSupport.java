package ru.innopolis.stc12.booksharing.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public abstract class BaseDaoSupport extends HibernateDaoSupport {
    @Autowired
    public void setHibernateSessionFactory(SessionFactory sessionFactory)
    {
        setSessionFactory(sessionFactory);
    }
}
