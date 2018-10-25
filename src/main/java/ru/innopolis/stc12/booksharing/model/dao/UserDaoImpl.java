package ru.innopolis.stc12.booksharing.model.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.connection.ConnectionManager;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private ApplicationContext context;
    private ConnectionManager connectionManager;

    public UserDaoImpl() {
        this.context = new ClassPathXmlApplicationContext("appContext.xml");
        this.connectionManager = (ConnectionManager) context.getBean("connectionManager");
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
