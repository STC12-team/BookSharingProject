package ru.innopolis.stc12.booksharing.model.pojo;

import java.sql.Timestamp;
import java.util.Objects;

public class BookHolder {
    private Integer id;
    private BookCopy bookCopy;
    private User user;
    private Timestamp getAt;
    private Timestamp gaveAt;
    private Integer hextHolderId;

    public BookHolder(Integer id, BookCopy bookCopy, User user, Timestamp getAt, Timestamp gaveAt, Integer hextHolderId) {
        this.id = id;
        this.bookCopy = bookCopy;
        this.user = user;
        this.getAt = getAt;
        this.gaveAt = gaveAt;
        this.hextHolderId = hextHolderId;
    }

    public BookHolder(BookCopy bookCopy, User user, Timestamp getAt, Timestamp gaveAt, Integer hextHolderId) {
        this.bookCopy = bookCopy;
        this.user = user;
        this.getAt = getAt;
        this.gaveAt = gaveAt;
        this.hextHolderId = hextHolderId;
    }

    public BookHolder() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getGetAt() {
        return getAt;
    }

    public void setGetAt(Timestamp getAt) {
        this.getAt = getAt;
    }

    public Timestamp getGaveAt() {
        return gaveAt;
    }

    public void setGaveAt(Timestamp gaveAt) {
        this.gaveAt = gaveAt;
    }

    public Integer getHextHolderId() {
        return hextHolderId;
    }

    public void setHextHolderId(Integer hextHolderId) {
        this.hextHolderId = hextHolderId;
    }

    @Override
    public String toString() {
        return "BookHolder{" +
                "id=" + id +
                ", bookCopy=" + bookCopy +
                ", user=" + user +
                ", getAt=" + getAt +
                ", gaveAt=" + gaveAt +
                ", hextHolderId=" + hextHolderId +
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
                Objects.equals(hextHolderId, that.hextHolderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookCopy, user, getAt, gaveAt, hextHolderId);
    }
}
