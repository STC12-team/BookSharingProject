package ru.innopolis.stc12.booksharing.dao;

import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.entitys.Book;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BooksDao {
    public Book getByIsbn(Integer isbn) {
        return new Book(1, "Java", "Ivanov", 123456789);
    }

    public List<Book> getByName(String name) {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "Java", "Иванов", 11111111));
        bookList.add(new Book(2, "C++", "Петров", 22222222));
        bookList.add(new Book(3, "Унесенные ветром", "Митчел", 33333333));
        bookList.add(new Book(4, "Война и мир", "Толстой", 44444444));
        bookList.add(new Book(5, "Мертвые души", "Гоголь", 55555555));
        bookList.add(new Book(6, "Phyton", "Сидоров", 66666666));
        bookList.add(new Book(7, "Сантехника", "Мазлов", 77777777));
        bookList.add(new Book(8, "Роботы", "Пупкин", 88888888));
        return bookList;
    }

    public List<Book> getByAuthor(String author) {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "Java", "Ivanov", 123456789));
        return bookList;
    }

}
