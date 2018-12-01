package ru.innopolis.stc12.booksharing.model.dao.entity;

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

    public User(String login, String password, Role role, int enabled, String email) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
        this.email = email;
    }

    private int id;
    private String login;
    private String password;
    private Role role;
    private int enabled;
    private String email;

    private UserDetails userDetails;
    private List<BookCopy> bookCopies = new ArrayList<>();
    private List<BookHolder> bookHolders = new ArrayList<>();

    @Id
    @Column(name = "id", nullable=false)
    @SequenceGenerator(name = "usersIdSeq", sequenceName = "users_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersIdSeq")
    public int getId() {
        return id;
    }
    public void setId(int id) {
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

    @OneToOne
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

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "user_id")
    public UserDetails getUserDetails() {
        return this.userDetails;
    }
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public List<BookHolder> getBookHolders() {
        return this.bookHolders;
    }
    public void setBookHolders(List<BookHolder> bookHolders) {
        this.bookHolders = bookHolders;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    public List<BookCopy> getBookCopies() {
        return this.bookCopies;
    }
    public void setBookCopies(List<BookCopy> bookCopies) {
        this.bookCopies = bookCopies;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", enabled=" + enabled +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                enabled == user.enabled &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role, enabled, email);
    }
}