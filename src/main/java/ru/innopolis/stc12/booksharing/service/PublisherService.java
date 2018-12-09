package ru.innopolis.stc12.booksharing.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.PublisherDao;

import java.util.List;

@EnableTransactionManagement
@Service
@Transactional
public class PublisherService {

    private PublisherDao<Publisher> publisherDao;

    @Autowired
    public void setPublisherDao(PublisherDao<Publisher> publisherDao) {
        this.publisherDao = publisherDao;
        this.publisherDao.setClazz(Publisher.class);
    }

    public Publisher getByName(String name) {
        return publisherDao.getPublisherByName(name);
    }

    public void addPublisher(Publisher publisher) {
        publisherDao.save(publisher);
    }
}
