package ru.innopolis.stc12.booksharing.model.dao.entity;

import ru.innopolis.stc12.booksharing.model.pojo.BookQueueStatus;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "book_queue")
public class BookQueue {
    private Integer id;
    private BookEdition bookEdition;
    private User user;
    private Timestamp addedAt;
    private BookQueueStatus status;

    public BookQueue() {
    }

    public BookQueue(Integer id, BookEdition bookEdition, User user, Timestamp addedAt, BookQueueStatus status) {
        this.id = id;
        this.bookEdition = bookEdition;
        this.user = user;
        this.addedAt = addedAt;
        this.status = status;
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "bookQueueIdSeq", sequenceName = "bookQueue_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookQueueIdSeq")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "book_edition_id")
    @ManyToOne
    @JoinColumn(name="id")
    public BookEdition getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(BookEdition bookEdition) {
        this.bookEdition = bookEdition;
    }

    @Column(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "added_at")
    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }

    @Column(name = "status")
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
