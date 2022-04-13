package com.epam.jwd.task2.util;

import com.epam.jwd.task2.model.entity.RegEx;

import java.util.ArrayList;
import java.util.List;

public class SpecialWordParser {
    public static List parse(String specialWordsText){
        List<String> specialWords = new ArrayList<>();
        String[] listSpecialWords = specialWordsText.split(RegEx.LINE_BRAKE.getRegEx());
        for (String specialWord : listSpecialWords) {
            specialWords.add(specialWord);
        }
        return specialWords;
    }
}
