package ru.innopolis.stc12.booksharing.model.pojo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    void constructorWithAllParamsExist() {
        Assertions.assertEquals(new Role(1, "mock role").getClass(), Role.class);
    }

    @Test
    void constructorWithoutIdParamExist() {
        Assertions.assertEquals(new Role("mock role").getClass(), Role.class);
    }

    @Test
    void hashCodeOfEqualsRolesIsEquals() {
        Role role1 = new Role();
        Role role2 = new Role();
        Assertions.assertEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    void hashCodeOfDifferentRolesAreNotEquals() {
        Role role1 = new Role(0, "ROLE_ADMIN");
        Role role2 = new Role(1, "ROLE_USER");
        Assertions.assertNotEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    void sameRoleObjectsAreEquals() {
        Role role1 = new Role(1, "ROLE_ADMIN");
        Role role2 = new Role(1, "ROLE_ADMIN");
        Assertions.assertTrue(role1.equals(role2));
    }

    @Test
    void differentRoleObjectsAreNotEquals() {
        Role role1 = new Role(0, "ROLE_ADMIN");
        Role role2 = new Role(1, "ROLE_USER");
        Assertions.assertFalse(role1.equals(role2));
    }

    @Test
    void toStringIsCorrect() {
        String mock = "Role{" +
                "id=" + "0" +
                ", name='" + "ROLE_ADMIN" + '\'' +
                '}';
        Assertions.assertEquals(mock, new Role(0, "ROLE_ADMIN").toString());
    }
}
