package com.epam.jwd.task2.util;

import com.epam.jwd.task2.model.entity.RegEx;
import com.epam.jwd.task2.model.entity.TextElement;
import com.epam.jwd.task2.model.logic.exception.implEmptyException.implCollectionException.ListSentenceException;
import com.epam.jwd.task2.model.logic.exception.implEmptyException.implCollectionException.ListSpecialWordException;
import com.epam.jwd.task2.model.logic.exception.implEmptyException.implCollectionException.NumberSpecialWordException;
import com.epam.jwd.task2.model.logic.exception.implEmptyException.implCollectionException.SortedSpecialWordIndexesException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ToStringConverter {
    /**
     * Convert list sentences in string
     *
     * @param sentences list of sentences
     * @return string
     */
    public static String convertSentences(List<TextElement> sentences){
        if (sentences == null) {
            throw new NullPointerException("List of sentences is null");
        }
        if (sentences.size() == 0) {
            throw new ListSentenceException("List of sentences is empty");
        }

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
     * @param sortedSpecialWordIndexes map for sorted total number special words in text
     * @param specialWords list of special words
     * @return string
     */
    public static String convertSortedSpecialWord(Map<Integer, Integer> sortedSpecialWordIndexes, List<String> specialWords){
        if (specialWords == null || sortedSpecialWordIndexes == null) {
            throw new NullPointerException("List of special words is null or map of sorted special word indexes is null");
        }
        if (specialWords.size() == 0) {
            throw new ListSpecialWordException("List of special words is empty");
        }
        if (sortedSpecialWordIndexes.size() == 0) {
            throw new SortedSpecialWordIndexesException("Map of sorted special word indexes is empty");
        }
        StringBuilder sortedSpecialWords = new StringBuilder();
        sortedSpecialWords.append("Total number special words in text:\n");

        Iterator<Map.Entry<Integer, Integer>> iterator = sortedSpecialWordIndexes.entrySet().iterator();
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
        if (specialWords == null || numberSpecialWords == null) {
            throw new NullPointerException("List of special words is null or Map of number special words is null");
        }
        if (specialWords.size() == 0) {
            throw new ListSpecialWordException("List of special words is empty");
        }
        if (numberSpecialWords.size() == 0) {
            throw new NumberSpecialWordException("Map of number special words is empty");
        }
        StringBuilder stringSpecialWord = new StringBuilder();
        stringSpecialWord.append("Total number special words in every sentence:\n");

        Iterator<Map.Entry<Integer, List<Integer>>> iterator = numberSpecialWords.entrySet().iterator();
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
