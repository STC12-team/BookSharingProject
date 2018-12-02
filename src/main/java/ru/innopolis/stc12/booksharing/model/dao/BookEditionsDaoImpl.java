package ru.innopolis.stc12.booksharing.model.dao;

import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookEditionsDao;

import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class BookEditionsDaoImpl extends AbstractDaoImp implements BookEditionsDao {
    @Override
    public List<BookEdition> getBookEditionsByPublisher(String publisher) {
        Join<BookEdition, Publisher> join = getRoot().join("publisher", JoinType.LEFT);
        Predicate predicate = getCriteriaBuilder().like(join.get("name"), "%" + publisher + "%");
        return getListByPredicates(predicate);
    }

    @Override
    public BookEdition getBookEditionByIsbn(String isbn) {
        Predicate predicate = getCriteriaBuilder().equal(getRoot().get("isbn"), isbn);
        List<BookEdition> list = getListByPredicates(predicate);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<BookEdition> getBookEditionByTitle(String title) {
        Predicate predicate = getCriteriaBuilder().like(getRoot().get("title"), "%" + title + "%");
        return getListByPredicates(predicate);
    }

    @Override
    public List<BookEdition> getBookEditionsBySearchValue(String searchValue) {
        Join<BookEdition, Publisher> join = getRoot().join("publisher", JoinType.LEFT);
        Predicate predicatePublisher = getCriteriaBuilder().like(join.get("name"), "%" + searchValue + "%");
        Predicate predicateTitle = getCriteriaBuilder().like(getRoot().get("title"), "%" + searchValue + "%");
        Predicate predicateIsbn = getCriteriaBuilder().equal(getRoot().get("isbn"), searchValue);
        Predicate predicateOr = getCriteriaBuilder().or(predicatePublisher, predicateTitle, predicateIsbn);
        return getListByPredicates(predicateOr);
    }

}
