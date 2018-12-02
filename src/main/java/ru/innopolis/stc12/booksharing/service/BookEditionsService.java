package ru.innopolis.stc12.booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.stc12.booksharing.model.dao.BookEditionsDao;
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
    private BookEditionsDao bookEditionsDao;
    private static final String REGEXP_SENTENCE_BY_ONE_WORD = "\\S+";

    @Autowired
    public void setBookEditionsDao(BookEditionsDao bookEditionsDao) {
        this.bookEditionsDao = bookEditionsDao;
    }

    public BookEdition getByIsbn(String isbn) {
        return bookEditionsDao.getBookEditionByIsbn(isbn);
    }

    public List<BookEdition> getByName(String name) {
        return bookEditionsDao.getBookEditionByTitle(name);
    }

    public List<BookEdition> getAllBookEditions() {
        return bookEditionsDao.getAllBookEditions();
    }

    public boolean addBookEdition(BookEdition bookEdition) {
        return bookEditionsDao.addBookEdition(bookEdition);
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
        return bookEditionsDao.getBookEditionById(id);
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
            put("all", 10);
            put("free", 4);
            put("queue", 6);
            put("place", 2);
        }}; // sonar recommendations ignored for temporary solution.
    }
}
