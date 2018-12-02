package ru.innopolis.stc12.booksharing.model.dao;

import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.UserDao;

import javax.persistence.criteria.Predicate;
import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDaoImp implements UserDao {
    @Override
    public User getUserByLogin(String login) {
        Predicate predicate = getCriteriaBuilder().equal(getRoot().get("login"), login);
        List<User> list = getListByPredicates(predicate);
        if (list.isEmpty()) { return null; }
        return list.get(0);
    }
}
