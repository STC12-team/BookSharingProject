package ru.innopolis.stc12.booksharing.model.dao;

import ru.innopolis.stc12.booksharing.model.pojo.BookQueue;

import java.util.List;

public interface BookQueueDao {
    List<BookQueue> getBookQueueByBookEditionId(Integer id);

    boolean updateBookQueue(BookQueue bookQueue);
}
