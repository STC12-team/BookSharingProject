package ru.innopolis.stc12.booksharing.model.dao;

import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;

import java.util.List;

public interface PublisherDao {
    Publisher getPublisherById(int id);

    List<Publisher> getAllPublishers();

    Publisher getPublisherByName(String name);

    boolean addPublisher(Publisher publisher);
}
