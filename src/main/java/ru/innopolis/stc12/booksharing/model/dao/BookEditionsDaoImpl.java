package ru.innopolis.stc12.booksharing.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;

import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class BookEditionsDaoImpl implements BookEditionsDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<BookEdition> getBookEditionsByPublisher(String publisher) {
        QueryObject queryObject = new QueryObject();
        Join<BookEdition, Publisher> join = queryObject.root.join("publisher", JoinType.LEFT);
        Predicate predicate = queryObject.criteriaBuilder.like(join.get("name"), "%" + publisher + "%");
        return queryObject.executeQuery(predicate);
    }

    @Override
    public BookEdition getBookEditionById(int id) {
        QueryObject queryObject = new QueryObject();
        Predicate predicate = queryObject.criteriaBuilder.equal(queryObject.root.get("id"), id);
        List<BookEdition> list = queryObject.executeQuery(predicate);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<BookEdition> getAllBookEditions() {
        QueryObject queryObject = new QueryObject();
        return queryObject.executeQuery();
    }

    @Override
    public BookEdition getBookEditionByIsbn(String isbn) {
        QueryObject queryObject = new QueryObject();
        Predicate predicate = queryObject.criteriaBuilder.equal(queryObject.root.get("isbn"), isbn);
        List<BookEdition> list = queryObject.executeQuery(predicate);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public boolean addBookEdition(BookEdition bookEdition) {
        Session session = sessionFactory.getCurrentSession();
        session.save(bookEdition);
        return true;
    }

    @Override
    public List<BookEdition> getBookEditionByTitle(String title) {
        QueryObject queryObject = new QueryObject();
        Predicate predicate = queryObject.criteriaBuilder.like(queryObject.root.get("title"), "%" + title + "%");
        return queryObject.executeQuery(predicate);
    }

    @Override
    public List<BookEdition> getBookEditionsBySearchValue(String searchValue) {
        QueryObject queryObject = new QueryObject();
        Join<BookEdition, Publisher> join = queryObject.root.join("publisher", JoinType.LEFT);
        Predicate predicatePublisher = queryObject.criteriaBuilder.like(join.get("name"), "%" + searchValue + "%");
        Predicate predicateTitle = queryObject.criteriaBuilder.like(queryObject.root.get("title"), "%" + searchValue + "%");
        Predicate predicateIsbn = queryObject.criteriaBuilder.equal(queryObject.root.get("isbn"), searchValue);
        Predicate predicateOr = queryObject.criteriaBuilder.or(predicatePublisher, predicateTitle, predicateIsbn);
        return queryObject.executeQuery(predicateOr);
    }

    private class QueryObject {
        Session session;
        CriteriaBuilder criteriaBuilder;
        CriteriaQuery<BookEdition> criteriaQuery;
        Root<BookEdition> root;

        QueryObject() {
            session = sessionFactory.getCurrentSession();
            criteriaBuilder = session.getCriteriaBuilder();
            criteriaQuery = criteriaBuilder.createQuery(BookEdition.class);
            root = criteriaQuery.from(BookEdition.class);
        }

        List<BookEdition> executeQuery(Predicate... args) {
            if (args.length == 0) {
                criteriaQuery.select(root);
            } else {
                criteriaQuery.select(root).where(args);
            }
            Query<BookEdition> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }
}
