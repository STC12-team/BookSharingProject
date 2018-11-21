package ru.innopolis.stc12.booksharing.model.pojo;

import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;

import java.util.Objects;

public class BookCopy {
    private Integer id;
    private BookEdition bookEdition;
    private User user;
    private BookCopiesStatus status;

    public BookCopy(Integer id, BookEdition bookEdition, User user, BookCopiesStatus status) {
        this.id = id;
        this.bookEdition = bookEdition;
        this.user = user;
        this.status = status;
    }

    public BookCopy(BookEdition bookEdition, User user, BookCopiesStatus status) {
        this.bookEdition = bookEdition;
        this.user = user;
        this.status = status;
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

    public BookCopiesStatus getStatus() {
        return status;
    }

    public void setStatus(BookCopiesStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookCopy{" +
                "id=" + id +
                ", bookEdition=" + bookEdition +
                ", user=" + user +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCopy bookCopy = (BookCopy) o;
        return Objects.equals(id, bookCopy.id) &&
                Objects.equals(bookEdition, bookCopy.bookEdition) &&
                Objects.equals(user, bookCopy.user) &&
                status == bookCopy.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookEdition, user, status);
    }
}
