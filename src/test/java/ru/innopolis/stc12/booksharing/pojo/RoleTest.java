package ru.innopolis.stc12.booksharing.pojo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.innopolis.stc12.booksharing.model.pojo.Role;

class RoleTest {
    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role();
    }

    @Test
    void onStartFieldsAreEmpty() {
        Assertions.assertEquals(0, role.getId());
        Assertions.assertNull(null, role.getName());
    }

    @Test
    void valuesCanBeSet() {
        role.setId(1);
        role.setName("Test");

        Assertions.assertEquals(1, role.getId());
        Assertions.assertEquals("Test", role.getName());
    }
}
