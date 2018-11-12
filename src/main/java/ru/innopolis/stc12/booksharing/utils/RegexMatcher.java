package ru.innopolis.stc12.booksharing.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {

    private RegexMatcher() {
    }

    /**
     * Return's first match from search by regexp
     *
     * @param regexp
     * @param inputString
     * @return first match
     */
    public static String getStringByRegexp(String regexp, String inputString) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(inputString);
        return matcher.find() ? matcher.group() : "";
    }

    public static List<String> getListOfStringsByRegexp(String regexp, String inputString) {
        List<String> listMatches = new ArrayList<>();
        if (!"".equals(inputString)) {
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(inputString);

            while (matcher.find()) {
                listMatches.add(matcher.group());
            }
        }
        return listMatches;
    }


}
