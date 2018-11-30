package ru.innopolis.stc12.booksharing.model.dao;

import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;

import java.io.Serializable;
import java.util.List;

public interface PublisherDao<T extends Serializable> extends AbstractDao<T> {

    Publisher getPublisherByName(String name);
}
