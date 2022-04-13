package com.epam.jwd.task2.model.logic.task2;

import com.epam.jwd.task2.model.entity.TextElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * It implements task2.
 * Retrieve all sentences of a given text in ascending
 * order of the number of words in each of them.
 *
 * @author Karpuk A.U.
 * @version 1.1 20.2.2022
 * */
public class SentenceSorter {
    /** Contain list of sentences */
    private List<TextElement> sentenceList;

    /**
     * Init list of sentences
     *
     * @param sentenceList list sentences of text
     */
    public SentenceSorter(List<TextElement> sentenceList){
        if (sentenceList == null) {
            throw new RuntimeException("List of sentences is null");
        }
        this.sentenceList = new ArrayList<>();
        this.sentenceList.addAll(sentenceList);
    }

    /**
     * Sort list sentence in ascending
     * order of the number of words in each of them.
     * */
    public void sortAscendingByNumberWords(){
        if (sentenceList == null) {
            throw new RuntimeException("List of sentences is null");
        }
        Collections.sort(sentenceList, new NumberWordComparator());
    }

    /** It is getter for list of sentence
     *
     * @return list of sentences
     */
    public List<TextElement> getSentenceList() {
        return sentenceList;
    }

    /** It is setter for list of sentence
     *
     * @return list of sentences
     */
    public void setSentenceList(List<TextElement> sentenceList) {
        this.sentenceList = sentenceList;
    }
}
