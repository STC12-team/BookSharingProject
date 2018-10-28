package ru.innopolis.stc12.booksharing.model.pojo;

import java.util.Objects;

public class User {
    private Integer id;
    private String login;
    private String password;
    private Role role;
    private String enabled;

    public User(Integer id, String login, String password, Role role, String enabled) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public User(String login, String password, Role role, String enabled) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", enabled='" + enabled + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
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
