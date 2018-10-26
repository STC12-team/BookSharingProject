package ru.innopolis.stc12.booksharing.entitys;

public class User {
    private Integer id;
    private String login;
    private String name;
    private String phone;
    private String email;

    public User(Integer id, String login, String name, String phone, String email) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public User(String login, String name, String phone, String email) {
        this.login = login;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
