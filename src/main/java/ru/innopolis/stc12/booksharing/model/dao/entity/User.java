package ru.innopolis.stc12.booksharing.model.dao.entity;

import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {
    public User() {
    }

    public User(long id, String login, String password, Role role, int enabled) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    private long id;
    private String login;
    private String password;
    private Role role;
    private int enabled;
    private String email;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "usersIdSeq", sequenceName = "users_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersIdSeq")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JoinColumn(name = "role_id", nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "enabled")
    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BookCopy> getBookCopies() {
        return new ArrayList<BookCopy>();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + role.getId() + '\'' +
                ", enabled='" + enabled + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(enabled, user.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role, enabled);
    }
}