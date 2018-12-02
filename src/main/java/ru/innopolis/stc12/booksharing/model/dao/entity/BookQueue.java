package ru.innopolis.stc12.booksharing.model.dao.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.innopolis.stc12.booksharing.model.pojo.BookQueueStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "book_queue")
@TypeDef(name = "book_queue_status_enum", typeClass = PostgreSQLEnumType.class)
public class BookQueue implements Serializable {
    private long id;
    private BookEdition bookEdition;
    private User user;
    private Timestamp addedAt;
    private BookQueueStatus status;

    public BookQueue() {
    }

    public BookQueue(BookEdition bookEdition, User user, Timestamp addedAt, BookQueueStatus status) {
        this.bookEdition = bookEdition;
        this.user = user;
        this.addedAt = addedAt;
        this.status = status;
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "bookQueueSeq", sequenceName = "book_queue_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookQueueSeq")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(optional=false)
    @ElementCollection(targetClass=BookEdition.class)
    @JoinColumn(name="book_edition_id", referencedColumnName = "id")
    public BookEdition getBookEdition() {
        return bookEdition;
    }
    public void setBookEdition(BookEdition bookEdition) {
        this.bookEdition = bookEdition;
    }

    @ManyToOne
    @ElementCollection(targetClass=User.class)
    @JoinColumn(name="user_id", referencedColumnName = "id")
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "public.book_queue_status")
    @Type(type = "book_queue_status_enum")
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
        return id == bookQueue.id &&
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
