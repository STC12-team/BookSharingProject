package ru.innopolis.stc12.booksharing.model.dao.implementation;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.innopolis.stc12.booksharing.model.dao.AbstractDao;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractDaoImp<T extends Serializable> {

    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    protected void setClazz(final Class<T> clazzToSet) {
        clazz = clazzToSet;
    }

    public T findOne(long id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    public void save(final T entity) {
        getCurrentSession().persist(entity);
    }

    public T update(final T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(final long id) {
        final T entity = findOne(id);
        delete(entity);
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}