package ru.innopolis.stc12.booksharing.model.dao.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.pojo.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookCopyMapperTest {
    private BookCopyMapper bookCopyMapper;
    @Mock
    private ResultSet resultSet;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookCopyMapper = new BookCopyMapper();
    }

    @Test
    void mapRow() throws SQLException {
        when(resultSet.getInt("be_id")).thenReturn(1);
        when(resultSet.getString("be_isbn")).thenReturn("be_isbn");
        when(resultSet.getInt("p_id")).thenReturn(1);
        when(resultSet.getString("p_name")).thenReturn("p_name");
        when(resultSet.getString("be_title")).thenReturn("be_title");
        when(resultSet.getString("be_description")).thenReturn("be_description");
        when(resultSet.getInt("be_year")).thenReturn(2018);
        when(resultSet.getInt("u_id")).thenReturn(1);
        when(resultSet.getString("u_login")).thenReturn("u_login");
        when(resultSet.getString("u_password")).thenReturn("u_password");
        when(resultSet.getString("r_name")).thenReturn("r_name");
        when(resultSet.getInt("bc_id")).thenReturn(1);
        BookEdition bookEdition = new BookEdition(1,
                "be_isbn",
                new Publisher(1, "p_name"),
                "be_title",
                "be_description",
                2018);
        User user = new User(1, "u_login", "u_password", "r_name");
        BookCopy bookCopy = new BookCopy(1, bookEdition, user, BookCopiesStatus.FREE);
        assertEquals(bookCopy, bookCopyMapper.mapRow(resultSet, 0));
    }
}