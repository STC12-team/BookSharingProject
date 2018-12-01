package ru.innopolis.stc12.booksharing.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.PublisherDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;

import java.util.List;

@EnableTransactionManagement
@Service
@Transactional
public class PublisherService {
    private Logger logger = Logger.getLogger(PublisherService.class);

    private PublisherDao<Publisher> publisherDao;

    @Autowired
    public void setPublisherDao(PublisherDao<Publisher> publisherDao) {
        this.publisherDao = publisherDao;
        this.publisherDao.setClazz(Publisher.class);
    }

    public Publisher getByName(String name) {
        return publisherDao.getPublisherByName(name);
    }

    public List<Publisher> getAllPublishers() {
        return publisherDao.findAll();
    }

    public void addPublisher(Publisher publisher) {
        publisherDao.save(publisher);
    }

    public Publisher getByNameOrCreate(String name) {
        Publisher publisher = this.getByName(name);
        if (publisher == null) {
            this.addPublisher(new Publisher(name));
            publisher = this.getByName(name);
        }
        return publisher;
    }
}
