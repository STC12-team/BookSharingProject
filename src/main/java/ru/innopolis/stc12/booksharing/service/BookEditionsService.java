package ru.innopolis.stc12.booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.model.dao.BookEditionsDao;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;
import ru.innopolis.stc12.booksharing.utils.RegexMatcher;

import java.util.ArrayList;
import java.util.List;

@Service
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
}
