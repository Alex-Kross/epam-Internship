package com.epam.jwd.task2.model.entity.leaf;

import com.epam.jwd.task2.model.entity.TextElement;

import java.util.Objects;

/**
 * This Word contains one string word.
 *
 * @author Karpuk A.U.
 * @version 1.1 20.2.2022
 * */
public class Word extends TextElement implements Cloneable{
    private String word;

    public Word(String word) {
        this.word = word;
    }

    @Override
    public String getElementText() {
        return word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(word);
        return stringBuilder.toString();
    }

    @Override
    public Word clone() throws CloneNotSupportedException {
        return (Word) super.clone();
    }
}
