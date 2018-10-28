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
}
