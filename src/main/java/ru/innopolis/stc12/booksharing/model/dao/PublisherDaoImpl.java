package ru.innopolis.stc12.booksharing.model.dao;

import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.PublisherDao;

import javax.persistence.criteria.Predicate;
import java.util.List;

@Repository
public class PublisherDaoImpl extends AbstractDaoImp implements PublisherDao {
    @Override
    public Publisher getPublisherByName(String name) {
        Predicate predicate = getCriteriaBuilder().equal(getRoot().get("name"), name);
        List<Publisher> list = getListByPredicates(predicate);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
