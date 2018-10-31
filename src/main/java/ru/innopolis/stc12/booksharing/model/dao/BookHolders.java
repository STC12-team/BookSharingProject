package ru.innopolis.stc12.booksharing.model.dao;

import ru.innopolis.stc12.booksharing.model.pojo.BookHolder;

import java.util.List;

public interface BookHolders {
    List<BookHolder> getBookHoldersByUserLogin(String login);
}
