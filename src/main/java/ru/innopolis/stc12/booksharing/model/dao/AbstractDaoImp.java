package ru.innopolis.stc12.booksharing.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.AbstractDao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
public abstract class AbstractDaoImp<T extends Serializable> implements AbstractDao<T>  {
    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public final void setClazz(final Class<T> clazzToSet) {
        clazz = clazzToSet;
    }

    public T findOne(int id) {
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

    public void deleteById(final int id) {
        final T entity = findOne(id);
        delete(entity);
    }

    class QueryObject {
        Session session;
        CriteriaBuilder criteriaBuilder;
        CriteriaQuery<T> criteriaQuery;
        Root<T> root;

        QueryObject() {
            session = sessionFactory.getCurrentSession();
            criteriaBuilder = session.getCriteriaBuilder();
            criteriaQuery = criteriaBuilder.createQuery(clazz);
            root = criteriaQuery.from(clazz);
        }

        List<T> executeQuery(Predicate... args) {
            if (args.length == 0) {
                criteriaQuery.select(root);
            } else {
                criteriaQuery.select(root).where(args);
            }
            Query<T> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }
}