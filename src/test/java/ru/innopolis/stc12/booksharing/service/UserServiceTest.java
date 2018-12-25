package ru.innopolis.stc12.booksharing.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.innopolis.stc12.booksharing.model.dao.entity.*;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.RoleDao;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.UserDao;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserDao<User> userDao;
    @Mock
    private RoleDao<Role> roleDao;
    @Mock
    private UserDao<UserDetails> userDetailsDao;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private UserDetails userDetails;
    @Mock
    private User user;
    @Mock
    private Role role;
    @Mock
    private SecurityContext securityContext;
    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void getUsers() {
        List<User> users = new ArrayList<>();
        when(userDao.findAll()).thenReturn(users);
        assertEquals(users, userService.getUsers());
    }

    @Test
    void getUserByLogin() {
        User user = new User();
        when(userDao.getUserByLogin("TestUser")).thenReturn(user);
        assertEquals(user, userService.getUserByLogin("TestUser"));
        Assertions.assertNull(userService.getUserByLogin(null));
        Assertions.assertNull(userService.getUserByLogin(""));
    }

    @Test
    void getAuthenticatedUserLogin() {
        org.springframework.security.core.userdetails.User userSecurity =
                new org.springframework.security.core.userdetails.User("TestUser", "", new ArrayList());

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getPrincipal()).thenReturn(userSecurity);
        when(userDao.getUserByLogin("TestUser")).thenReturn(user);
        assertEquals(user, userService.getAuthenticatedUserDetails());
    }

    @Test
    void getAuthenticatedUserLoginAsNull() {
        when(securityContext.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(securityContext);
        Assertions.assertNull(userService.getAuthenticatedUserDetails());
    }

    @Test
    void addNewUser() {
        when(roleDao.findOne(2)).thenReturn(role);
        when(bCryptPasswordEncoder.encode("TestPassword")).thenReturn("Hash");
        User newUser = new User("TestLogin", "Hash", role, 1, "TestEMail");
        when(userDao.getUserByLogin("TestLogin")).thenReturn(newUser);

        assertEquals(newUser, userService.addNewUser("TestLogin", "TestPassword", "TestEMail"));
        verify(userDao).save(newUser);
    }

    @Test
    void confirmCorrectPassword() {
//        UserDetails userDetails = new UserDetails(1, 1, "firstname", "lastname", "surname", "admin@example.com", "sa");
//        when(userDao.getUserDetails()).thenReturn(userDetails);
//        when(userDao.checkUserPasswordMatches(anyString(), anyString())).thenReturn(true);
//        assertTrue(userService.confirmPassword("sa"));
    }

    @Test
    void confirmWrongPassword() {
//        UserDetails userDetails = new UserDetails(1, 1, "firstname", "lastname", "surname", "admin@example.com", "secret");
//        when(userDao.getUserDetails()).thenReturn(userDetails);
//        when(userDao.checkUserPasswordMatches(anyString(), anyString())).thenReturn(false);
//        assertFalse(userService.confirmPassword("sa"));
    }

    @Test
    void updateUserDetails() {
        org.springframework.security.core.userdetails.User userSecurity =
                new org.springframework.security.core.userdetails.User("TestUser", "", new ArrayList());
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getPrincipal()).thenReturn(userSecurity);
        when(userDao.getUserByLogin("TestUser")).thenReturn(user);
        when(user.getUserDetails()).thenReturn(userDetails);
        when(userDetailsDao.update(userDetails)).thenReturn(userDetails);

        assertEquals(userDetails, userService.updateUserDetails("firstName", "lastName", "surname", ""));
    }

    @Test
    void getBookHoldersByUserLogin() {
        List<BookHolder> bookHolders = new ArrayList<>();
        BookHolder bookHolder = new BookHolder();
        BookCopy bookCopy = new BookCopy();
        BookEdition bookEdition = new BookEdition();
        bookHolder.setBookCopy(bookCopy);
        bookCopy.setBookEdition(bookEdition);
        bookHolders.add(bookHolder);
        when(userDao.getBookHoldersByUserLogin("user")).thenReturn(bookHolders);
        assertEquals(bookHolders, userService.getBookHoldersByUserLogin("user"));
    }

    @Test
    void getBookCopiesByUserLogin() {
        List<BookCopy> bookCopies = new ArrayList<>();
        BookCopy bookCopy = new BookCopy();
        BookEdition bookEdition = new BookEdition();
        bookCopy.setBookEdition(bookEdition);
        bookCopies.add(bookCopy);
        when(userDao.getBookCopiesByUserLogin("user")).thenReturn(bookCopies);
        assertEquals(bookCopies, userService.getBookCopiesByUserLogin("user"));
    }

    @Test
    void getBookQueueByUserLogin() {
        List<BookQueue> bookQueues = new ArrayList<>();
        BookQueue bookQueue = new BookQueue();
        BookEdition bookEdition = new BookEdition();
        bookQueue.setBookEdition(bookEdition);
        bookQueues.add(bookQueue);
        when(userDao.getBookQueueByUserLogin("user")).thenReturn(bookQueues);
        assertEquals(bookQueues, userService.getBookQueueByUserLogin("user"));
    }
}
