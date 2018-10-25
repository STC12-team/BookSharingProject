package ru.innopolis.stc12.booksharing.model.dao;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.connection.ConnectionManager;
import ru.innopolis.stc12.booksharing.model.connection.ConnectionManagerJdbcImpl;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private ConnectionManager connectionManager;
    private static Logger logger = Logger.getLogger(UserDaoImpl.class);
    private static final String SQL_SELECT_ALL = "SELECT id, login, password, role_id FROM users";

    public UserDaoImpl() {
        ApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");
        this.connectionManager = (ConnectionManagerJdbcImpl) context.getBean("connectionManager");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(new User(
                            resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getInt("role_id")
                    ));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return result; // empty collection return must be better than null
        }
        return result;
    }
}
