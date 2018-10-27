package ru.innopolis.stc12.booksharing.model.pojo;

import java.util.Objects;

public class BookEdition {
    private int id;
    private String title;
    private String description;
    private String isbn;

    public BookEdition(String title, String description, String isbn) {
        this.title = title;
        this.description = description;
        this.isbn = isbn;
    }

    public BookEdition(int id, String title, String description, String isbn) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "BookEdition{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEdition that = (BookEdition) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, isbn);
    }
}
