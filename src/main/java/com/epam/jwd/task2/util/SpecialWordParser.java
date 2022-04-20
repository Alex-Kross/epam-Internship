package com.epam.jwd.task2.util;

import com.epam.jwd.task2.model.entity.RegEx;
import com.epam.jwd.task2.model.logic.exception.implEmptyException.FileEmptyException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialWordParser {
    private static final String LIST_SPECIAL_WORD = "([A-Za-z0-9]+\n\\s*)+";
    private static final String SPECIAL_WORD = "[A-Za-z0-9]+\\s*";
    private static final Pattern LIST_SPECIAL_WORD_PATTERN = Pattern.compile(LIST_SPECIAL_WORD);
    private static final Pattern SPECIAL_WORD_PATTERN = Pattern.compile(SPECIAL_WORD);

    public static List parse(String specialWordsText){
        if (specialWordsText == null) {
            throw new NullPointerException("Special words is null");
        }

        Matcher listSpecialWordMatcher = LIST_SPECIAL_WORD_PATTERN.matcher(specialWordsText);
        if (!listSpecialWordMatcher.find()) {
            throw new FileEmptyException("File with special words is empty");
        }

        List<String> specialWords = new ArrayList<>();
        String[] listSpecialWords = specialWordsText.split(RegEx.LINE_BRAKE.getRegEx());
        for (String specialWord : listSpecialWords) {
            Matcher specialWordMatcher = SPECIAL_WORD_PATTERN.matcher(specialWord);
            while (specialWordMatcher.find()) {
                specialWords.add(specialWord);
            }
        }
        return specialWords;
    }
}
