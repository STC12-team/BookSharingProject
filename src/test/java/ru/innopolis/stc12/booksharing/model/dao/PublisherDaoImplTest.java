package ru.innopolis.stc12.booksharing.model.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.stc12.booksharing.model.pojo.Publisher;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class PublisherDaoImplTest {

    @Mock
    PublisherDaoImpl publisherDao;

    @Mock
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        initMocks(this);

        publisherDao = new PublisherDaoImpl();
        jdbcTemplate = mock(JdbcTemplate.class);
        publisherDao.setJdbcTemplate(jdbcTemplate);
    }


    @Test
    void getPublisherById() {
        Publisher publisher = new Publisher();
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(publisher);
        assertEquals(publisher, publisherDao.getPublisherById(5));
    }

    @Test
    void getAllPublishers() {
        List<Publisher> list = new ArrayList<>();
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class),
                any(RowMapper.class))).thenReturn(list);
        assertEquals(list, publisherDao.getAllPublishers());
    }

    @Test
    void getPublisherByName() {
        String name = "Test name";
        Publisher publisher = new Publisher(name);
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(publisher);
        assertEquals(publisher, publisherDao.getPublisherByName(name));
    }

    @Test
    void addPublisher() {
        Publisher publisher = new Publisher(1, "Test name");
        ArgumentCaptor<String> nameCapture = ArgumentCaptor.forClass(String.class);
        when(jdbcTemplate.update(anyString(), nameCapture.capture())).thenReturn(1);
        publisherDao.addPublisher(publisher);
        assertEquals(nameCapture.getValue(), publisher.getName());
    }
}