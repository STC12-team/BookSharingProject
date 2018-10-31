package ru.innopolis.stc12.booksharing.model.pojo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void onStartFieldsAreEmpty() {
        Assertions.assertEquals(0, user.getId());
        Assertions.assertNull(null, user.getLogin());
        Assertions.assertNull(null, user.getPassword());
        Assertions.assertNull(null, user.getRole());
        Assertions.assertEquals(0, user.getRoleId());
    }

    @Test
    void valuesCanBeSet() {
        user.setId(1);
        user.setLogin("Test");
        user.setPassword("secret");
        user.setRoleId(1);
        user.setRole("admin");

        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("Test", user.getLogin());
        Assertions.assertEquals("secret", user.getPassword());
        Assertions.assertEquals("admin", user.getRole());
        Assertions.assertEquals(1, user.getRoleId());
    }

    @Test
    void valuesCanBeSetWithRoleAsInt() {
        User user = new User(1, "Test", "secret", 1, "", 1);

        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("Test", user.getLogin());
        Assertions.assertEquals("secret", user.getPassword());
        Assertions.assertEquals(1, user.getRoleId());
    }

    @Test
    void valuesCanBeSetWithRoleAsString() {
        User user = new User(1, "Test", "secret", 1, "ROLE_ADMIN", 1);

        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("Test", user.getLogin());
        Assertions.assertEquals("secret", user.getPassword());
        Assertions.assertEquals("ROLE_ADMIN", user.getRole());
    }

    @Test
    void valuesCanBeSetWithAllParameters() {
        User user = new User(1, "Test", "secret", 1, "ROLE_ADMIN", 1);

        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("Test", user.getLogin());
        Assertions.assertEquals("secret", user.getPassword());
        Assertions.assertEquals(1, user.getRoleId());
        Assertions.assertEquals("ROLE_ADMIN", user.getRole());
    }

    @Test
    void hashCodeOfEqualsUsersIsEquals() {
        User user1 = new User();
        User user2 = new User();
        Assertions.assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void hashCodeOfDifferentUsersAreNotEquals() {
        User user1 = new User(1, "", "", 0, "ROLE_ADMIN",1);
        User user2 = new User(1, "", "", 1, "ROLE_USER",1);
        Assertions.assertNotEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void sameUserObjectsAreEquals() {
        User user1 = new User(1, "", "", 0, "ROLE_ADMIN",1);
        User user2 = new User(1, "", "", 0, "ROLE_ADMIN",1);
        Assertions.assertTrue(user1.equals(user2));
    }

    @Test
    void differentUserObjectsAreNotEquals() {
        User user1 = new User(1, "", "", 0, "ROLE_ADMIN",1);
        User user2 = new User(1, "", "", 1, "ROLE_USER",1);
        Assertions.assertFalse(user1.equals(user2));
    }

    @Test
    void toStringIsCorrect() {
        String mock = "User{" +
                "id=" + "1" +
                ", login='" + "login" + '\'' +
                ", password='" + "password" + '\'' +
                ", roleId=" + "1" +
                ", role='" + "role" + '\'' +
                ", enabled='" + "1" + '\'' +
                '}';
        Assertions.assertEquals(mock, new User(1, "login", "password", 1, "role", 1).toString());
    }
}