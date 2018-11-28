package ru.innopolis.stc12.booksharing.model.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface AbstractDao<T extends Serializable> {
    T findOne(long id);

    List<T> findAll();

    void save(T entity);

    T update(T entity);

    void delete(T entity);

    void deleteById(long id);
}
