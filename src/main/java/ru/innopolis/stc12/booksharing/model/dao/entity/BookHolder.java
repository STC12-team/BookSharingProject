package ru.innopolis.stc12.booksharing.model.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "book_holders")
public class BookHolder implements Serializable {
    private int id;
    private BookCopy bookCopy;
    private User user;
    private Timestamp getAt;
    private Timestamp gaveAt;
    private Integer nextHolder;

    public BookHolder(BookCopy bookCopy, User user, Timestamp getAt, Timestamp gaveAt, Integer nextHolder) {
        this.bookCopy = bookCopy;
        this.user = user;
        this.getAt = getAt;
        this.gaveAt = gaveAt;
        this.nextHolder = nextHolder;
    }

    public BookHolder() {
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "bookHolderIdGenerator", sequenceName = "book_holders_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookHolderIdGenerator")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_copy_id")
    public BookCopy getBookCopy() {
        return bookCopy;
    }
    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
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
    public Integer getNextHolder() {
        return nextHolder;
    }

    public void setNextHolder(Integer nextHolder) {
        this.nextHolder = nextHolder;
    }

    @Override
    public String toString() {
        return "BookHolder{" +
                "id=" + id +
                ", bookCopy=" + bookCopy +
                ", user=" + user +
                ", getAt=" + getAt +
                ", gaveAt=" + gaveAt +
                ", nextHolder=" + nextHolder +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookHolder that = (BookHolder) o;
        return id == that.id &&
                Objects.equals(bookCopy, that.bookCopy) &&
                Objects.equals(user, that.user) &&
                Objects.equals(getAt, that.getAt) &&
                Objects.equals(gaveAt, that.gaveAt) &&
                Objects.equals(nextHolder, that.nextHolder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookCopy, user, getAt, gaveAt, nextHolder);
    }
}
