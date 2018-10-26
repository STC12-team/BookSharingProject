package ru.innopolis.stc12.booksharing.entitys;

public class BookWithOwner {
    private Integer id;
    private Book book;
    private User user;

    public BookWithOwner(Integer id, Book book, User user) {
        this.id = id;
        this.book = book;
        this.user = user;
    }

    public BookWithOwner(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BookWithOwner{" +
                "id=" + id +
                ", book=" + book +
                ", user=" + user +
                '}';
    }
}
