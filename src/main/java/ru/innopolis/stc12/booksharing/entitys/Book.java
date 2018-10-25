package ru.innopolis.stc12.booksharing.entitys;

public class Book {
    private Integer id;
    private String name;
    private String author;
    private Integer isbn;

    public Book(Integer id, String name, String author, Integer isbn) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isbn = isbn;
    }

    public Book(String name, String author, Integer isbn) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", isbn=" + isbn +
                '}';
    }
}
