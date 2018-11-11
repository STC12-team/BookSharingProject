package ru.innopolis.stc12.booksharing.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegexMatcherTest {

    @Test
    void getStringByRegexp() {
        String someString = "123ччч456";
        String pattern = "[0-9][0-9][0-9]";
        assertEquals("123", RegexMatcher.getStringByRegexp(pattern, someString));
    }
}