package com.epam.jwd.task2.model.logic.task2;

import com.epam.jwd.task2.model.entity.TextElement;
import com.epam.jwd.task2.model.entity.composite.Sentence;
import com.epam.jwd.task2.model.entity.leaf.Word;

import java.util.Comparator;

/**
 * Sort list of sentences in ascending order of the number of words in each of them.
 *
 * @author Karpuk A.U.
 * @version 1.1 20.2.2022
 * */
public class NumberWordComparator implements Comparator<TextElement> {
    /**
     * Sort list of sentences in ascending
     * Calc number of words in sentence1 and sentence2.
     * After compare them number.
     *
     * @param sentence1 part of list of sentence
     * @param sentence2 part of list of sentence
     * @return result of comparing
     */
    @Override
    public int compare(TextElement sentence1, TextElement sentence2) {
        int numberWordInSentence1 = 0;
        int numberWordInSentence2 = 0;

        for (TextElement WordOrPunctMark : ((Sentence) sentence1).getWordsAndPunctMarks()) {
            if (WordOrPunctMark.getClass() == Word.class) {
                numberWordInSentence1++;
            }
        }
        for (TextElement WordOrPunctMark: ((Sentence) sentence2).getWordsAndPunctMarks()) {
            if (WordOrPunctMark.getClass() == Word.class) {
                numberWordInSentence2++;
            }
        }
        return numberWordInSentence1 - numberWordInSentence2;
    }
}
