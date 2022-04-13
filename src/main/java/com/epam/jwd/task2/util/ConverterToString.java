package com.epam.jwd.task2.util;

import com.epam.jwd.task2.model.entity.RegEx;
import com.epam.jwd.task2.model.entity.TextElement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ConverterToString {
    /**
     * Convert list sentences in string
     *
     * @param sentences list of sentences
     * @return string
     */
    public static String convertSentences(List<TextElement> sentences){
        StringBuilder string = new StringBuilder();

        for (TextElement sentence : sentences) {
            string.append(sentence.getElementText());
            string.append(RegEx.LINE_BRAKE.getRegEx());
        }
        return string.toString();
    }

    /**
     * Convert list of special words and how many times it occurs in each sentence in string.
     *
     * @param indexesSortedSpecialWords map for sorted total number special words in text
     * @param specialWords list of special words
     * @return string
     */
    public static String convertSorterSpecialWord(Map<Integer, Integer> indexesSortedSpecialWords, List<String> specialWords){
        Iterator<Map.Entry<Integer, Integer>> iterator = indexesSortedSpecialWords.entrySet().iterator();
        StringBuilder sortedSpecialWords = new StringBuilder();

        sortedSpecialWords.append("Total number special words in text:\n");

        while(iterator.hasNext()){
            Map.Entry<Integer, Integer> map =iterator.next();
            int indexSpecialWord = map.getKey();
            int numberSpecialWord = map.getValue();

            sortedSpecialWords.append(specialWords.get(indexSpecialWord));
            sortedSpecialWords.append(" - ");
            sortedSpecialWords.append(numberSpecialWord);
            sortedSpecialWords.append(RegEx.LINE_BRAKE.getRegEx());
        }
        return sortedSpecialWords.toString();
    }

    /**
     * Convert list of special words and how many times it occurs in text in string.
     *
     * @param numberSpecialWords map for number special words in each sentence
     * @param specialWords list of special words
     * @return string
     */
    public static String convertNumberSpecialWord(Map<Integer, List<Integer>> numberSpecialWords, List<String> specialWords){
        Iterator<Map.Entry<Integer, List<Integer>>> iterator = numberSpecialWords.entrySet().iterator();
        StringBuilder stringSpecialWord = new StringBuilder();

        stringSpecialWord.append("Total number special words in every sentence:\n");

        while(iterator.hasNext()){
            Map.Entry<Integer, List<Integer>> map =iterator.next();
            int indexSentence = map.getKey();
            List<Integer> listSpecialWords = map.getValue();

            stringSpecialWord.append("Sentence ");
            stringSpecialWord.append(indexSentence+1);
            stringSpecialWord.append(": ");

            for (int i = 0; i < listSpecialWords.size(); i++) {
                stringSpecialWord.append(specialWords.get(i));
                stringSpecialWord.append(" - ");
                stringSpecialWord.append(listSpecialWords.get(i));
                stringSpecialWord.append(", ");
            }
            stringSpecialWord.append(RegEx.LINE_BRAKE.getRegEx());
            stringSpecialWord.append(RegEx.LINE_BRAKE.getRegEx());
        }
        return stringSpecialWord.toString();
    }
}
