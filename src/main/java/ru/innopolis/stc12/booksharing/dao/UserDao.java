package ru.innopolis.stc12.booksharing.dao;

import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.entitys.User;

@Repository
public class UserDao {
    public User getByLogin(String login) {
        return new User(1, "vasay", "Sasha", "123456789", "pupkin@mail.ru");
    }
}
