package ru.innopolis.stc12.booksharing.model.dao;

import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookQueue;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookQueueDao;

import javax.persistence.criteria.Predicate;

@Repository
public class BookQueueDaoImpl extends AbstractDaoImp implements BookQueueDao {

    @Override
    public BookQueue getUserBookQueueByBookEdition(User user, BookEdition bookEdition) {
        QueryObject queryObject = new QueryObject();
        Predicate predicateUser = queryObject.criteriaBuilder.equal(queryObject.root.get("user"), user);
        Predicate predicateBookEdition = queryObject.criteriaBuilder.equal(queryObject.root.get("bookEdition"), bookEdition);
        Predicate predicate = queryObject.criteriaBuilder.and(predicateUser, predicateBookEdition);
        return (BookQueue) queryObject.executeQuery(predicate).get(0);
    }
}
