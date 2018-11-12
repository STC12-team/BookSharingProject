package ru.innopolis.stc12.booksharing.model.pojo;

import java.util.Objects;

public class UserDetails {
    private long id;
    private long userId;
    private String firstName;
    private String lastName;
    private String surname;
    private String email; // TODO remove field from here after User pojo refactoring
    private String password;

    public UserDetails() {
    }

    public UserDetails(long id, long userId, String firstName, String lastName, String surname, String email, String password) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, firstName, lastName, surname, email, password);
    }
}
