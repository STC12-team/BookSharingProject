package ru.innopolis.stc12.booksharing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.PublisherDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class PublisherServiceTest {
    @InjectMocks
    private PublisherService publisherService;

    @Mock
    private PublisherDao publisherDao;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void getAllPublishers() {
        List<Publisher> list = new ArrayList<>();
        list.add(new Publisher("Test publisher"));
//        when(publisherDao.getAllPublishers()).thenReturn(list);
        assertEquals(list, publisherService.getAllPublishers());
    }

    @Test
    void addPublisher() {
        Publisher publisher = new Publisher("Test name");
        ArgumentCaptor<Publisher> valueCapture = ArgumentCaptor.forClass(Publisher.class);
//        when(publisherDao.addPublisher(valueCapture.capture())).thenReturn(true);
        publisherService.addPublisher(publisher);
        assertEquals(publisher, valueCapture.getValue());
    }

    @Test
    void getByName() {
        String name = "Test name";
        Publisher publisher = new Publisher(name);
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        when(publisherDao.getPublisherByName(nameCaptor.capture())).thenReturn(publisher);
        assertEquals(publisher, publisherService.getByName(name));
    }

    @Test
    void getByNameOrCreate() {
        String name = "Test name";
    }
}