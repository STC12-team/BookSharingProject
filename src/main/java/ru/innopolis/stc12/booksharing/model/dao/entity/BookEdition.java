package ru.innopolis.stc12.booksharing.model.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "book_editions")
public class BookEdition implements Serializable {
    private Integer id;
    private String title;
    private String description;
    private String isbn;
    private Publisher publisher;
    private Integer yearOfPublication;
    private List<BookCopy> bookCopies = new ArrayList<>();
    private List<BookQueue> bookQueues = new ArrayList<>();

    public BookEdition() {
    }

    public BookEdition(Integer id, String title, String description, String isbn, Publisher publisher, Integer yearOfPublication) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.publisher = publisher;
        this.yearOfPublication = yearOfPublication;
    }

    public BookEdition(String title, String description, String isbn, Publisher publisher, Integer yearOfPublication) {
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.publisher = publisher;
        this.yearOfPublication = yearOfPublication;
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "bookEditionsIdGenerator", sequenceName = "book_editions_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookEditionsIdGenerator")
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

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book_editions")
    public List<BookCopy> getBookCopies() {
        return this.bookCopies;
    }

    public void setBookCopies(List<BookCopy> bookCopies) {
        this.bookCopies = bookCopies;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book_editions")
    public List<BookQueue> getBookQueue() {
        return this.bookQueues;
    }
    public void setBookQueue(List<BookQueue> bookQueue) {
        this.bookQueues = bookQueue;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "year_of_publication")
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
