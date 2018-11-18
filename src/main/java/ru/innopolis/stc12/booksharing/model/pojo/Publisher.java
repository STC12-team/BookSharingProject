package ru.innopolis.stc12.booksharing.model.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "publishers")
public class Publisher {
    private Integer id;
    private String name;

    public Publisher() {
    }

    public Publisher(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Publisher(String name) {
        this.name = name;
    }

    @Id
    @SequenceGenerator(name = "publisherIdGenerator", sequenceName = "publishers_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisherIdGenerator")
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

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(id, publisher.id) &&
                Objects.equals(name, publisher.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
