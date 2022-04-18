package com.epam.jwd.task2.model.logic.task10;

import com.epam.jwd.task2.model.entity.TextElement;
import com.epam.jwd.task2.model.logic.exception.implEmptyException.implCollectionException.ListSentenceException;
import com.epam.jwd.task2.model.logic.exception.implEmptyException.implCollectionException.ListSpecialWordException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * It implements task10 part 1.
 * For each word from the file, find
 * how many times it occurs in each sentence.
 *
 * @author Karpuk A.U.
 * @version 1.1 20.2.2022
 */
public class SpecialWordCounter {
    /** Contain list of sentences */
    private List<TextElement> sentenceList;

    /**
     * Contain map for number special words in each sentence.
     * Key is sentence index. Value is list consist of number
     * each special word in sentence.
     * */
    private Map<Integer, List<Integer>> numberSpecialWords = new HashMap();

    /** Contain list of special words for search in each sentence */
    private List<String> specialWords;

    /**
     * Init list of sentences and special words
     *
     * @param sentenceList list of sentences
     * @param specialWords list of special words
     */
    public SpecialWordCounter(List<TextElement> sentenceList, List<String> specialWords) throws CloneNotSupportedException {
        if (sentenceList == null || specialWords == null) {
            throw new NullPointerException("List of sentences is null or list of special words is null");
        }
        if (sentenceList.size() == 0) {
            throw new ListSentenceException("List of sentences is empty");
        }
        if (specialWords.size() == 0) {
            throw new ListSpecialWordException("List of special words is empty");
        }
        this.sentenceList = new ArrayList<>();
        this.sentenceList.addAll(sentenceList);
        this.specialWords = specialWords;
    }

    /**
     * It is getter for map of sentence
     *
     * @return map for number special words in each sentence.
     */
    public Map<Integer, List<Integer>> getNumberSpecialWords() {
        return numberSpecialWords;
    }

    /**
     * Calculate how many special words in each sentence.
     * In each sentence search list of special word with
     * help pattern. When pattern matches to record in list
     * number of matches. After search list of special words
     * we have list numberSpecialWords that contain the
     * number of words encountered in the same order as
     * the list of special words.
     * In the end fill hashMap key consist of index of sentence
     * and value consist of list numberSpecialWords
     */
    public void CalcNumberSpecialWords(){
        if (sentenceList == null || specialWords == null) {
            throw new NullPointerException("List of sentences is null or list of special words is null");
        }
        if (sentenceList.size() == 0) {
            throw new ListSentenceException("List of sentences is empty");
        }
        if (specialWords.size() == 0) {
            throw new ListSpecialWordException("List of special words is empty");
        }
        int indexSentence = 0;

        for (TextElement sentence : sentenceList) {
            String s = sentence.getElementText().toLowerCase();
            Pattern specialWordPattern;
            Matcher specialWordMatcher;
            List<Integer> numberSpecialWords = new ArrayList<>(specialWords.size());

            for (String specialWord : specialWords) {
                int numberSpecialWord = 0;
                specialWordPattern = Pattern.compile(getPatternString(specialWord));
                specialWordMatcher = specialWordPattern.matcher(s);

                while (specialWordMatcher.find()){
                    numberSpecialWord++;
                }
                numberSpecialWords.add(numberSpecialWord);
            }
            this.numberSpecialWords.put(indexSentence, numberSpecialWords);
            indexSentence++;
        }
    }

    /**
     * Compose regular expression for special word.
     * A regular expression in one line looks like this
     * ("^" + specialWord + "(\s+|\\W)" + "|" + "\s+" + specialWord + "\s+" + "|" + "\s+" + specialWord + "\\W")
     * @param specialWord list of special words
     * @return pattern for Matcher
     */
    private String getPatternString(String specialWord){
        StringBuilder patternString = new StringBuilder();
        patternString.append("^");
        patternString.append(specialWord);
        patternString.append("(\s+|\\W)");
        patternString.append("|");
        patternString.append("\s+");
        patternString.append(specialWord);
        patternString.append("\s+");
        patternString.append("|");
        patternString.append("\s+");
        patternString.append(specialWord);
        patternString.append("\\W");
        return patternString.toString();
    }
}
