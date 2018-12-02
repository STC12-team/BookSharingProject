package ru.innopolis.stc12.booksharing.model.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role implements Serializable {
    public Role() {
    }

    private long id;
    private String name;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "rolesIdSeq", sequenceName = "roles_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rolesIdSeq")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
