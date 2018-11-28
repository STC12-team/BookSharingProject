package ru.innopolis.stc12.booksharing.model.dao.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "book_holders")
public class BookHolder {
    private Integer id;
    private BookCopyEntity bookCopy;
    //TODO переделать на User entity
    private Integer user;
    private Timestamp getAt;
    private Timestamp gaveAt;
    private Integer nextHolderId;

    public BookHolder(Integer id, BookCopyEntity bookCopy, Integer user, Timestamp getAt, Timestamp gaveAt, Integer nextHolderId) {
        this.id = id;
        this.bookCopy = bookCopy;
        this.user = user;
        this.getAt = getAt;
        this.gaveAt = gaveAt;
        this.nextHolderId = nextHolderId;
    }

    public BookHolder(BookCopyEntity bookCopy, Integer user, Timestamp getAt, Timestamp gaveAt, Integer nextHolderId) {
        this.bookCopy = bookCopy;
        this.user = user;
        this.getAt = getAt;
        this.gaveAt = gaveAt;
        this.nextHolderId = nextHolderId;
    }

    public BookHolder() {
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "bookHolderIdGenerator", sequenceName = "book_holders_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookHolderIdGenerator")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_copy_id")
    public BookCopyEntity getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopyEntity bookCopy) {
        this.bookCopy = bookCopy;
    }

    @Column(name = "user_id")
    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    @Column(name = "get_at")
    public Timestamp getGetAt() {
        return getAt;
    }

    public void setGetAt(Timestamp getAt) {
        this.getAt = getAt;
    }

    @Column(name = "gave_at")
    public Timestamp getGaveAt() {
        return gaveAt;
    }

    public void setGaveAt(Timestamp gaveAt) {
        this.gaveAt = gaveAt;
    }

    @Column(name = "next_holder_id")
    public Integer getNextHolderId() {
        return nextHolderId;
    }

    public void setNextHolderId(Integer nextHolderId) {
        this.nextHolderId = nextHolderId;
    }

    @Override
    public String toString() {
        return "BookHolder{" +
                "id=" + id +
                ", bookCopy=" + bookCopy +
                ", user=" + user +
                ", getAt=" + getAt +
                ", gaveAt=" + gaveAt +
                ", nextHolderId=" + nextHolderId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookHolder that = (BookHolder) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(bookCopy, that.bookCopy) &&
                Objects.equals(user, that.user) &&
                Objects.equals(getAt, that.getAt) &&
                Objects.equals(gaveAt, that.gaveAt) &&
                Objects.equals(nextHolderId, that.nextHolderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookCopy, user, getAt, gaveAt, nextHolderId);
    }
}
