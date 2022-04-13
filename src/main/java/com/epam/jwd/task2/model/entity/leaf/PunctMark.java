package com.epam.jwd.task2.model.entity.leaf;

import com.epam.jwd.task2.model.entity.TextElement;

import java.util.Objects;

/**
 * This PunctMark contains one punctuation mark.
 *
 * @author Karpuk A.U.
 * @version 1.1 20.2.2022
 * */
public class PunctMark extends TextElement implements Cloneable{
    private String punctuation;

    public PunctMark(String punctuation) {
        this.punctuation = punctuation;
    }

    @Override
    public String getElementText() {
        return punctuation;
    }

    public String getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(String punctuation) {
        this.punctuation = punctuation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PunctMark punctMark1 = (PunctMark) o;
        return Objects.equals(punctuation, punctMark1.punctuation);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(punctuation);
        return stringBuilder.toString();
    }

    @Override
    public PunctMark clone() throws CloneNotSupportedException {
        return (PunctMark) super.clone();
    }
}
