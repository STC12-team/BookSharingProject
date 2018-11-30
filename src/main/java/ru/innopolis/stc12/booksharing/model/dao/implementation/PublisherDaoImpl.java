package ru.innopolis.stc12.booksharing.model.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.PublisherDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PublisherDaoImpl extends AbstractDaoImp implements PublisherDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Publisher getPublisherByName(String name) {
        QueryObject queryObject = new QueryObject();
        Predicate predicate = queryObject.criteriaBuilder.equal(queryObject.root.get("name"), name);
        List<Publisher> list = queryObject.executeQuery(predicate);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private class QueryObject {
        Session session;
        CriteriaBuilder criteriaBuilder;
        CriteriaQuery<Publisher> criteriaQuery;
        Root<Publisher> root;

        QueryObject() {
            session = sessionFactory.getCurrentSession();
            criteriaBuilder = session.getCriteriaBuilder();
            criteriaQuery = criteriaBuilder.createQuery(Publisher.class);
            root = criteriaQuery.from(Publisher.class);
        }

        List<Publisher> executeQuery(Predicate... args) {
            if (args.length == 0) {
                criteriaQuery.select(root);
            } else {
                criteriaQuery.select(root).where(args);
            }
            Query<Publisher> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }
}
