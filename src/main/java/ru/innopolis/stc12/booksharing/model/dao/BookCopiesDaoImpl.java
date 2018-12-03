package ru.innopolis.stc12.booksharing.model.dao;

import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookCopiesDao;

import javax.persistence.criteria.Predicate;
import java.util.List;

@Repository
public class BookCopiesDaoImpl extends AbstractDaoImp implements BookCopiesDao {

    @Override
    public List<BookCopy> getBookCopiesOfBookEdition(BookEdition bookEdition) {
        QueryObject queryObject = new QueryObject();
        Predicate predicate = queryObject.criteriaBuilder.equal(queryObject.root.get("bookEdition"), bookEdition);
        return queryObject.executeQuery(predicate);
    }
}
