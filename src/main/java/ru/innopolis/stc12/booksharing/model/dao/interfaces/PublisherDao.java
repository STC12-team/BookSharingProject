package ru.innopolis.stc12.booksharing.model.dao.interfaces;

import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;

import java.io.Serializable;

public interface PublisherDao<T extends Serializable> extends AbstractDao<T> {

    Publisher getPublisherByName(String name);
}
