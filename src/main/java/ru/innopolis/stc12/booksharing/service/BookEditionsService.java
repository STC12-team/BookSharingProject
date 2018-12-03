package ru.innopolis.stc12.booksharing.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookEditionsDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.utils.RegexMatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@EnableTransactionManagement
public class BookEditionsService {
    private Logger logger = Logger.getLogger(BookEditionsService.class);
    private BookEditionsDao<BookEdition> bookEditionsDao;
    private static final String REGEXP_SENTENCE_BY_ONE_WORD = "\\S+";

    @Autowired
    public void setBookEditionsDao(BookEditionsDao bookEditionsDao) {
        this.bookEditionsDao = bookEditionsDao;
        this.bookEditionsDao.setClazz(BookEdition.class);
    }

    public BookEdition getByIsbn(String isbn) {
        return bookEditionsDao.getBookEditionByIsbn(isbn);
    }

    public List<BookEdition> getByName(String name) {
        return bookEditionsDao.getBookEditionByTitle(name);
    }

    public List<BookEdition> getAllBookEditions() {
        return bookEditionsDao.findAll();
    }

    public void addBookEdition(BookEdition bookEdition) {
        bookEditionsDao.save(bookEdition);
    }

    public List<BookEdition> getBookEditionsBySearchValue(String searchValue) {
        searchValue = searchValue.replace("+", " ").toLowerCase();
        List<String> list = RegexMatcher.getListOfStringsByRegexp(REGEXP_SENTENCE_BY_ONE_WORD, searchValue);
        List<BookEdition> resultList = new ArrayList<>();
        for (String str : list) {
            resultList.addAll(bookEditionsDao.getBookEditionsBySearchValue(str));
        }
        return resultList;
    }

    public BookEdition getById(int id) {
        return bookEditionsDao.findOne(id);
    }

    public List<BookEdition> getBookEditionsByPublisher(String publisher) {
        return bookEditionsDao.getBookEditionsByPublisher(publisher);
    }

    public BookEdition getBookEditionByIsbn(String isbn) {
        return bookEditionsDao.getBookEditionByIsbn(isbn);
    }

    public List<BookEdition> getBookEditionByTitle(String title) {
        return bookEditionsDao.getBookEditionByTitle(title);
    }

    /*
    TODO Replace mock with real object logic
     */
    public Map<String, Integer> getMockedAttributes() {
        return new HashMap<String, Integer>() {{
            put("all", 10); put("free", 4); put("queue", 6);}}; // sonar recommendations ignored for temporary solution.
    }

    public int getUserPlaceInQueue() { // TODO: replace with real life value
        return (int)(Math.random()*2); // return random value from sequence [0,1]
    }
}
