package ru.innopolis.stc12.booksharing.model.dao;

import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookEditionsDao;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Repository
public class BookEditionsDaoImpl extends AbstractDaoImp implements BookEditionsDao {
    @Override
    public List<BookEdition> getBookEditionsByPublisher(String publisher) {
        QueryObject queryObject = new QueryObject();
        Join<BookEdition, Publisher> join = queryObject.root.join("publisher", JoinType.LEFT);
        Predicate predicate = queryObject.criteriaBuilder.like(join.get("name"), "%" + publisher + "%");
        return queryObject.executeQuery(predicate);
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

}
