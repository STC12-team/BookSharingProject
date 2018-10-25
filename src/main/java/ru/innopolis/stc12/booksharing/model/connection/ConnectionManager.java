package ru.innopolis.stc12.booksharing.model.connection;

import java.sql.Connection;

public interface ConnectionManager {
    Connection getConnection();
}
