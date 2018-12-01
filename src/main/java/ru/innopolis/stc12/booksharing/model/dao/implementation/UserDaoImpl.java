package ru.innopolis.stc12.booksharing.model.dao.implementation;

import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.UserDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;

@Repository
public class UserDaoImpl extends AbstractDaoImp implements UserDao {
    @Override
    public User getUserByLogin(String login) {
        return (User)getCurrentSession()
                .createQuery("from User where login = :login")
                .setParameter("login", login)
                .getSingleResult();
    }
}
