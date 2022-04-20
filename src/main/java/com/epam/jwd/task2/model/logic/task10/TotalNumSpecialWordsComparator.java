package com.epam.jwd.task2.model.logic.task10;

import java.util.Comparator;
import java.util.Map;

/**
 * Sort the list by the total number of special words in descending order.
 *
 * @author Karpuk A.U.
 * @version 1.1 20.2.2022
 * */
public class TotalNumSpecialWordsComparator implements Comparator<Map.Entry<Integer, Integer>> {
    /**
     * Sort list in descending order.
     *
     * @param o1 map for sorted total number special words in text.
     * @param o2 map for sorted total number special words in text.
     * @return result of comparing
     */
    @Override
    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
        return o2.getValue().compareTo(o1.getValue());
    }
}
