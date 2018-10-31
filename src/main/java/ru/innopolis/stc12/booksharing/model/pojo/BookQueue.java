package ru.innopolis.stc12.booksharing.model.pojo;

import java.sql.Timestamp;
import java.util.Objects;

public class BookQueue {
    private Integer id;
    private BookEdition bookEdition;
    private User user;
    private Timestamp addedAt;
    private BookQueueStatus status;

    public BookQueue(Integer id, BookEdition bookEdition, User user, Timestamp addedAt, BookQueueStatus status) {
        this.id = id;
        this.bookEdition = bookEdition;
        this.user = user;
        this.addedAt = addedAt;
        this.status = status;
    }

    public BookQueue(BookEdition bookEdition, User user, Timestamp addedAt, BookQueueStatus status) {
        this.bookEdition = bookEdition;
        this.user = user;
        this.addedAt = addedAt;
        this.status = status;
    }

    public BookQueue() {
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

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }

    public BookQueueStatus getStatus() {
        return status;
    }

    public void setStatus(BookQueueStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookQueue{" +
                "id=" + id +
                ", bookEdition=" + bookEdition +
                ", user=" + user +
                ", addedAt=" + addedAt +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookQueue bookQueue = (BookQueue) o;
        return Objects.equals(id, bookQueue.id) &&
                Objects.equals(bookEdition, bookQueue.bookEdition) &&
                Objects.equals(user, bookQueue.user) &&
                Objects.equals(addedAt, bookQueue.addedAt) &&
                status == bookQueue.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookEdition, user, addedAt, status);
    }
}
