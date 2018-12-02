package ru.innopolis.stc12.booksharing.model.dao.interfaces;

import java.io.Serializable;
import java.util.List;

public interface AbstractDao<T extends Serializable> {
    T findOne(int id);

    List<T> findAll();

    void save(T entity);

    T update(T entity);

    void delete(T entity);

    void deleteById(int id);

    void setClazz(Class<T> clazzToSet);
}
