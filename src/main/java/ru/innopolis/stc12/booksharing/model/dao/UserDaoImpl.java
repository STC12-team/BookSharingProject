package ru.innopolis.stc12.booksharing.model.dao;

import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookHolder;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookQueue;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.UserDao;

import javax.persistence.criteria.Predicate;
import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDaoImp implements UserDao {
    @Override
    public User getUserByLogin(String login) {
        QueryObject queryObject = new QueryObject();
        Predicate predicate = queryObject.criteriaBuilder.equal(queryObject.root.get("login"), login);
        List<User> list = queryObject.executeQuery(predicate);
        if (list.isEmpty()) { return null; }
        return list.get(0);
    }

    @Override
    public User getUserByEmail(String email) {
        QueryObject queryObject = new QueryObject();
        Predicate predicate = queryObject.criteriaBuilder.equal(queryObject.root.get("email"), email);
        List<User> list = queryObject.executeQuery(predicate);
        if (list.isEmpty()) { return null; }
        return list.get(0);
    }

    @Override
    public List<BookHolder> getBookHoldersByUserLogin(String login) {
        User user = getUserByLogin(login);
        return user.getBookHolders();
    }

    @Override
    public List<BookCopy> getBookCopiesByUserLogin(String login) {
        User user = getUserByLogin(login);
        return user.getBookCopies();
    }

    @Override
    public List<BookQueue> getBookQueueByUserLogin(String login) {
        User user = getUserByLogin(login);
        return user.getBookQueues();
    }
}
