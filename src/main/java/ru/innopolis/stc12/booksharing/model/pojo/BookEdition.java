package ru.innopolis.stc12.booksharing.model.pojo;

import java.util.Objects;

public class BookEdition {
    private Integer id;
    private String isbn;
    private Publisher publisher;
    private String title;
    private String description;
    private Integer yearOfPublication;

    public BookEdition() {
    }

    public BookEdition(Integer id, String isbn, Publisher publisher, String title, String description, Integer yearOfPublication) {
        this.id = id;
        this.isbn = isbn;
        this.publisher = publisher;
        this.title = title;
        this.description = description;
        this.yearOfPublication = yearOfPublication;
    }

    public BookEdition(String isbn, Publisher publisher, String title, String description, Integer yearOfPublication) {
        this.isbn = isbn;
        this.publisher = publisher;
        this.title = title;
        this.description = description;
        this.yearOfPublication = yearOfPublication;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
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

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    @Override
    public String toString() {
        return "BookEdition{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", publisher=" + publisher +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEdition that = (BookEdition) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(isbn, that.isbn) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(yearOfPublication, that.yearOfPublication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, publisher, title, description, yearOfPublication);
    }
}
