package ru.innopolis.stc12.booksharing.model.dao.implementation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.UserDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.Role;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.dao.entity.UserDetails;

import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImpl extends AbstractDaoImp implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private static Logger logger = Logger.getLogger(UserDaoImpl.class);


    private static final String SQL_SELECT_BY_LOGIN =
            "select u.id, u.login, u.password, u.enabled, u.role_id, r.name as role_name from users as u inner join roles r on u.role_id = r.id where u.login=?";
    private static final String SQL_INSERT =
            "insert into users (login, password, role_id, enabled) values (?,?,?,?)";
    private static final String SQL_SELECT_USER_DETAILS =
            "select d.id, d.user_id, d.firstname, d.surname, d.lastname, u.email, u.password from users as u inner join user_details as d on u.id = d.user_id where d.user_id = ?";
    private static final String SQL_UPDATE_USER_DETAILS =
            "update user_details set firstname=?, lastname=?, surname=? where user_id=?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BookCopy> getBookCopyByUser(int userId) {
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = (User)getCurrentSession()
                .createQuery("from users where login = :login")
                .setParameter("login", login)
                .getSingleResult();

        return user;
    }

    @Override
    public boolean updateUserDetails(UserDetails userDetails) {
        int rows = jdbcTemplate.update(SQL_UPDATE_USER_DETAILS,
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getSurname(),
                userDetails.getUserId());
        return rows > 0;
    }
}
