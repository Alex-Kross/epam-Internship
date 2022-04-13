package com.epam.jwd.task2.model.entity.composite;

import com.epam.jwd.task2.model.entity.CompositeTextElement;
import com.epam.jwd.task2.model.entity.TextElement;
import com.epam.jwd.task2.model.entity.leaf.PunctMark;
import com.epam.jwd.task2.model.entity.leaf.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This Sentence contains list words and punctuation marks
 *
 * @author Karpuk A.U.
 * @version 1.1 20.2.2022
 * */
public class Sentence extends CompositeTextElement implements Cloneable{
    private List<TextElement> wordsAndPunctMarks;

    public Sentence() {
        wordsAndPunctMarks = new ArrayList<>();
    }

    @Override
    public String getElementText() {
        StringBuilder string = new StringBuilder();

        for (TextElement wordAndPunctMark : wordsAndPunctMarks) {
            string.append(wordAndPunctMark.getElementText());
        }
        return string.toString();
    }

    public void add(TextElement textElement) {
        wordsAndPunctMarks.add(textElement);
    }

    public void remove(TextElement textElement) {
        wordsAndPunctMarks.remove(textElement);
    }

    public List<TextElement> getWordsAndPunctMarks() {
        return wordsAndPunctMarks;
    }

    public void setWordsAndPunctMarks(List<TextElement> wordsAndPunctMarks) {
        this.wordsAndPunctMarks = wordsAndPunctMarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(wordsAndPunctMarks, sentence.wordsAndPunctMarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordsAndPunctMarks);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (TextElement wordAndPunctMark : wordsAndPunctMarks) {
            string.append(wordAndPunctMark);
        }
        return string.toString();
    }

    @Override
    public Sentence clone() throws CloneNotSupportedException {
        Sentence newSentence = (Sentence) super.clone();
        List<TextElement> newWordsAndPunctMarks = new ArrayList<>(wordsAndPunctMarks.size());

        for (TextElement wordsOrPunctMark : wordsAndPunctMarks) {
            if (wordsOrPunctMark.getClass() == PunctMark.class){
                newWordsAndPunctMarks.add(((PunctMark) wordsOrPunctMark).clone());
            } else if(wordsOrPunctMark.getClass() == Word.class){
                newWordsAndPunctMarks.add(((Word) wordsOrPunctMark).clone());
            } else{
                throw new RuntimeException("Sentence doesn't contain type this object");
            }
        }
        newSentence.setWordsAndPunctMarks(newWordsAndPunctMarks);
        return newSentence;
    }
}
