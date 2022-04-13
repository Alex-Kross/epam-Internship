package com.epam.jwd.task2.model.logic.task10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * It implements task10 part 2.
 * Sorting special words in
 * descending order of total occurrence.
 *
 * @author Karpuk A.U.
 * @version 1.1 20.2.2022
 */
public class SorterSpecialWord {
    /**
     * Contain map for total number special words in text.
     * Key is index of sentence.
     * Value is total number special word
     * */
    private Map<Integer, Integer> totalNumberSpecial = new HashMap<>();

    /**
     * Contain map for sorted total number special words in text.
     * Key is sorted index of sentence.
     * Value is total number special word
     * */
    private Map<Integer, Integer> sortedTotalNumberSpecial = new LinkedHashMap<>();

    /**
     * Contain map for number special words in each sentence.
     * Key is sentence index. Value is list consist of number
     * each special word in sentence.
     * */
    private Map<Integer, List<Integer>> numberSpecialWordsInSentences;

    /**
     * Init numberSpecialWordsInSentences
     *
     * @param numberSpecialWordsInSentences map for number special words in each sentence.
     */
    public SorterSpecialWord(Map<Integer, List<Integer>> numberSpecialWordsInSentences) {
        this.numberSpecialWordsInSentences = numberSpecialWordsInSentences;
    }

    /**
     * It is getter for sortedTotalNumberSpecial
     *
     * @return map for sorted total number special words in text.
     */
    public Map<Integer, Integer> getSortedTotalNumberSpecial() {
        return sortedTotalNumberSpecial;
    }

    /**
     * Sort in descending order total number special words
     */
    public void sortInDescendingSpecialWords() {
        if (numberSpecialWordsInSentences == null) {
            throw new RuntimeException("map for number special words in each sentence is null");
        }
        calcTotalNumberSpecial();
        List<Map.Entry<Integer, Integer>> listTotalNumberSpecial = new ArrayList<>(totalNumberSpecial.entrySet());

        Collections.sort(listTotalNumberSpecial, new TotalNumSpecialWordsComparator());

        for (Map.Entry<Integer, Integer> entry : listTotalNumberSpecial) {
            sortedTotalNumberSpecial.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Calculate total number each special word in text
     */
    private void calcTotalNumberSpecial(){
        int numberSentences = numberSpecialWordsInSentences.size();
        int numberSpecialWords = numberSpecialWordsInSentences.get(0).size();
        int totalNumber = 0;

        for (int i = 0; i < numberSpecialWords; i++) {
            totalNumber = 0;

            for (int j = 0; j < numberSentences; j++) {
                totalNumber += numberSpecialWordsInSentences.get(j).get(i);
            }
            totalNumberSpecial.put(i, totalNumber);
        }
    }
}
