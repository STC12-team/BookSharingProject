package ru.innopolis.stc12.booksharing.model.pojo;

import java.util.Objects;

public class BookCopy {
    private Integer id;
    private BookEdition bookEdition;
    private User user;

    public BookCopy(Integer id, BookEdition bookEdition, User user) {
        this.id = id;
        this.bookEdition = bookEdition;
        this.user = user;
    }

    public BookCopy(BookEdition bookEdition, User user) {
        this.bookEdition = bookEdition;
        this.user = user;
    }

    public BookCopy() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookEdition getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(BookEdition bookEdition) {
        this.bookEdition = bookEdition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BookCopy{" +
                "id=" + id +
                ", bookEdition=" + bookEdition +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCopy that = (BookCopy) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(bookEdition, that.bookEdition) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookEdition, user);
    }
}
