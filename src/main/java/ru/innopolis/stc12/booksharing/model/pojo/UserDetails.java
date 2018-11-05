package ru.innopolis.stc12.booksharing.model.pojo;

import java.util.Objects;

public class UserDetails {
    private long id;
    private long userId;
    private String firstName;
    private String lastName;
    private String surname;

    public UserDetails() {
    }

    public UserDetails(long id, long userId, String firstName, String lastName, String surname) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.surname = surname;
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

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", surname='" + surname + '\'' +
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
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, firstName, lastName, surname);
    }
}
