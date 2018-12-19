package ru.innopolis.stc12.booksharing.model.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_details")
public class UserDetails implements Serializable {
    private int id;
    private User user;
    private String firstName;
    private String lastName;
    private String surname;
    private String userPicUrl;

    public UserDetails() {
    }

    public UserDetails(User user, String firstName, String lastName, String surname, String userPicUrl) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.surname = surname;
        this.userPicUrl = userPicUrl;
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "userDetailsSeq", sequenceName = "user_details_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userDetailsSeq")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "firstname")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastname")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "user_pic_url")
    public String getUserPicUrl() {
        return userPicUrl;
    }

    public void setUserPicUrl(String userPicUrl) {
        this.userPicUrl = userPicUrl;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", user=" + user +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", surname='" + surname + '\'' +
                ", userPicUrl='" + userPicUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return id == that.id &&
                Objects.equals(user, that.user) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(userPicUrl, that.userPicUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, firstName, lastName, surname, userPicUrl);
    }
}
