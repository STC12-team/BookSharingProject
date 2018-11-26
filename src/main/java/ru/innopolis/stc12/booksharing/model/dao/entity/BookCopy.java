package ru.innopolis.stc12.booksharing.model.dao.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Type;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopiesStatus;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book_copies")
@TypeDef(name = "book_copies_status_enum", typeClass = PostgreSQLEnumType.class)
public class BookCopy implements Serializable {

    public BookCopy() {
    }

    public BookCopy(Integer id, BookEdition bookEdition, User owner, BookCopiesStatus status) {
        this.id = id;
        this.bookEdition = bookEdition;
        this.owner = owner;
        this.status = status;
    }

    public BookCopy(BookEdition bookEdition, User owner, BookCopiesStatus status) {
        this.bookEdition = bookEdition;
        this.owner = owner;
        this.status = status;
    }

    private int id;
    private BookEdition bookEdition;
    private User owner;
    private BookCopiesStatus status;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "bookCopiesIdSeq", sequenceName = "book_copies_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookCopiesIdSeq")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_edition_id", nullable = false)
    public BookEdition getBookEdition() { return bookEdition; }
    public void setBookEdition(BookEdition bookEdition) {
        this.bookEdition = bookEdition;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "public.book_copies_status")
    @Type(type = "book_copies_status_enum")
    public BookCopiesStatus getStatus() {
        return status;
    }
    public void setStatus(BookCopiesStatus status) {
        this.status = status;
    }

    /*
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
        BookCopy1 bookCopy = (BookCopy1) o;
        return Objects.equals(id, bookCopy.id) &&
                Objects.equals(bookEdition, bookCopy.bookEdition) &&
                Objects.equals(user, bookCopy.user) &&
                status == bookCopy.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookEdition, user, status);
    }*/

}
