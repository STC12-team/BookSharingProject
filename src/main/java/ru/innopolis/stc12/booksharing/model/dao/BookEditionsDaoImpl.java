package ru.innopolis.stc12.booksharing.model.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.mapper.BookEditionMapper;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookEditionsDaoImpl implements BookEditionsDao {
    private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;
    private static Logger logger = Logger.getLogger(BookEditionsDaoImpl.class);
    private static final String SQL_SELECT_BY_PUBLISHER =
            "select b.id as b_id, b.isbn as b_isbn, b.title as b_title, b.description as b_description, b.year_of_publication as b_year, p.id as p_id, p.name as p_name from book_editions as b inner join publishers as p on b.publisher_id = p.id where p.name like ?";

    private static final String SQL_SELECT_BY_SEARCH_VALUE =
            "select b.id as b_id, b.isbn as b_isbn, b.title as b_title, b.description as b_description, b.year_of_publication as b_year, p.id as p_id, p.name as p_name from book_editions as b inner join publishers as p on b.publisher_id = p.id where b.title like ? OR b.isbn like ? OR p.name like ?";

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BookEdition getBookEditionById(int id) {
        QueryObject queryObject = new QueryObject();
        Predicate predicate = queryObject.criteriaBuilder.equal(queryObject.root.get("id"), id);
        List<BookEdition> list = queryObject.executeQuery(predicate);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<BookEdition> getAllBookEditions() {
        QueryObject queryObject = new QueryObject();
        return queryObject.executeQuery();
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
    public boolean addBookEdition(BookEdition bookEdition) {
        Session session = sessionFactory.getCurrentSession();
        session.save(bookEdition);
        return true;
    }

    @Override
    public List<BookEdition> getBookEditionByTitle(String title) {
        QueryObject queryObject = new QueryObject();
        Predicate predicate = queryObject.criteriaBuilder.like(queryObject.root.get("title"), "%" + title + "%");
        return queryObject.executeQuery(predicate);
    }

    private class QueryObject {
        Session session;
        CriteriaBuilder criteriaBuilder;
        CriteriaQuery<BookEdition> criteriaQuery;
        Root<BookEdition> root;

        public QueryObject() {
            session = sessionFactory.getCurrentSession();
            criteriaBuilder = session.getCriteriaBuilder();
            criteriaQuery = criteriaBuilder.createQuery(BookEdition.class);
            root = criteriaQuery.from(BookEdition.class);
        }

        List<BookEdition> executeQuery(Predicate... args) {
            if (args.length == 0) {
                criteriaQuery.select(root);
            } else {
                criteriaQuery.select(root).where(args);
            }
            Query<BookEdition> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }

    @Override
    public List<BookEdition> getBookEditionsByPublisher(String publisher) {
        try {
            return jdbcTemplate.query(SQL_SELECT_BY_PUBLISHER, new Object[]{publisher}, new BookEditionMapper());
        } catch (DataAccessException daException) {
            logger.debug("BookEdition not found by publisher: " + publisher);
            return new ArrayList<>();
        }
    }

    @Override
    public List<BookEdition> getBookEditionsBySearchValue(String searchValue) {
        try {
            return jdbcTemplate.query(SQL_SELECT_BY_SEARCH_VALUE, new Object[]{'%' + searchValue + '%', '%' + searchValue + '%', '%' + searchValue + '%'}, new BookEditionMapper());
        } catch (DataAccessException daException) {
            logger.debug("BookEditions not found by searchValue: " + searchValue);
            return new ArrayList<>();
        }
    }
}
