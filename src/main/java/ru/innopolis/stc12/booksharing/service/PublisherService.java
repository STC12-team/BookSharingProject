package ru.innopolis.stc12.booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.model.dao.PublisherDao;
import ru.innopolis.stc12.booksharing.model.pojo.Publisher;

import java.util.List;

@Service
public class PublisherService {
    private PublisherDao publisherDao;

    @Autowired
    public void setPublisherDao(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    public Publisher getByName(String name) {
        return publisherDao.getPublisherByName(name);
    }

    public List<Publisher> getAllPublishers() {
        return publisherDao.getAllPublishers();
    }

    public boolean addPublisher(Publisher publisher) {
        return publisherDao.addPublisher(publisher);
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