package com.epam.jwd.task2.model.logic.task5;

import com.epam.jwd.task2.model.entity.TextElement;
import com.epam.jwd.task2.model.entity.composite.Sentence;
import com.epam.jwd.task2.model.entity.leaf.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * It implements task5.
 * In each sentence of the text, swap the first word
 * with the last one without changing the length of the sentence.
 *
 * @author Karpuk A.U.
 * @version 1.1 20.2.2022
 * */
public class WordSwapper {
    /** Contain list of sentences */
    private List<TextElement> sentenceList;

    /** Object first word in sentence */
    private TextElement firstWord;

    /** Object last word in sentence */
    private TextElement lastWord;

    /** Index first word in sentence */
    private int indexFirstWord;

    /** Index last word in sentence */
    private int indexLastWord;

    /**
     * Init list of sentences
     *
     * @param sentenceList list sentences of text
     */
    public WordSwapper(List<TextElement> sentenceList) throws CloneNotSupportedException {
        if (sentenceList == null) {
            throw new RuntimeException("List of sentences is null");
        }
        this.sentenceList = new ArrayList<>();
        for (TextElement sentence : sentenceList) {
            this.sentenceList.add(((Sentence)sentence).clone());
        }
    }

    /**
     * Swap the first word with the last in every sentence
     * This method according number white space after first word
     * in the swapping
     */
    public void reversPlaceLastAndFirstWord(){
        if (sentenceList == null) {
            throw new RuntimeException("List of sentences is null");
        }
        for (TextElement sentence : sentenceList) {
            List<TextElement> wordsOrPunctMarks = ((Sentence)sentence).getWordsAndPunctMarks();

            if (wordsOrPunctMarks.size() > 2) {
                findFirstAndLastWord(wordsOrPunctMarks);

                // get values objects last and first words
                String valueFirstWord = firstWord.getElementText().trim();
                String valueLastWord = lastWord.getElementText().trim();

                // Calculate number white space after first word
                int numberWhiteSpace = firstWord.getElementText().length() - valueFirstWord.length();

                // create new first word and append need number white space
                StringBuilder newValueFirstWord = new StringBuilder();
                newValueFirstWord.append(valueLastWord);
                for (int i = 0; i < numberWhiteSpace; i++) {
                    newValueFirstWord.append(" ");
                }

                // swap place first and last words
                wordsOrPunctMarks.set(indexFirstWord, new Word(newValueFirstWord.toString()));
                wordsOrPunctMarks.set(indexLastWord, new Word(valueFirstWord));
            }
        }
    }

    /**
     * Find first and last word and them indexes
     *
     * @param wordsOrPunctMarks parts of sentence
     */
    private void findFirstAndLastWord(List<TextElement> wordsOrPunctMarks){
        boolean isFirstWord = false;
        int index = 0;

        for (TextElement wordOrPunctMark : wordsOrPunctMarks) {
            if (wordOrPunctMark.getClass() == Word.class) {
                if (!isFirstWord) {
                    indexFirstWord = index;
                    firstWord = wordOrPunctMark;
                    isFirstWord = true;
                }
                lastWord = wordOrPunctMark;
                indexLastWord = index;
            }
            index++;
        }
    }

    /**
     * It is getter for list of sentence
     *
     * @return list of sentences
     */
    public List<TextElement> getSentenceList() {
        return sentenceList;
    }

    /**
     * It is setter for list of sentence
     *
     * @return list of sentences
     */
    public void setSentenceList(List<TextElement> sentenceList) {
        this.sentenceList = sentenceList;
    }
}
