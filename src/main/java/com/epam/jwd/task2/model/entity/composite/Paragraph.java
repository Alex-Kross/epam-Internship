package com.epam.jwd.task2.model.entity.composite;

import com.epam.jwd.task2.model.entity.CompositeTextElement;
import com.epam.jwd.task2.model.entity.TextElement;
import com.epam.jwd.task2.model.entity.leaf.CodeBlock;
import com.epam.jwd.task2.model.entity.leaf.PunctMark;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This Paragraph contains list sentences or list empty string or block of code
 *
 * @author Karpuk A.U.
 * @version 1.1 20.2.2022
 * */
public class Paragraph extends CompositeTextElement implements Cloneable{
    private List<TextElement> sentencesAndCode;

    public Paragraph() {
        sentencesAndCode = new ArrayList<>();
    }

    @Override
    public String getElementText() {
        StringBuilder string = new StringBuilder();

        for (TextElement sen : sentencesAndCode) {
            string.append(sen.getElementText());
        }
        return string.toString();
    }

    @Override
    public void add(TextElement textElement) {
        sentencesAndCode.add(textElement);
    }

    @Override
    public void remove(TextElement textElement) {
        sentencesAndCode.remove(textElement);
    }

    public List<TextElement> getSentencesAndCode() {
        return sentencesAndCode;
    }

    public void setSentencesAndCode(List<TextElement> sentencesAndCode) {
        this.sentencesAndCode = sentencesAndCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paragraph paragraph = (Paragraph) o;
        return Objects.equals(sentencesAndCode, paragraph.sentencesAndCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sentencesAndCode);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (TextElement sen : sentencesAndCode) {
            string.append(sen);
        }
        return string.toString();
    }

    @Override
    public Paragraph clone() throws CloneNotSupportedException {
        Paragraph newParagraph = (Paragraph) super.clone();
        List<TextElement> newSentencesAndCode = new ArrayList<>(sentencesAndCode.size());
        for (TextElement sentenceOrCode : sentencesAndCode) {
            if (sentenceOrCode.getClass() == Sentence.class){
                newSentencesAndCode.add(((Sentence) sentenceOrCode).clone());
            } else if(sentenceOrCode.getClass() == CodeBlock.class){
                newSentencesAndCode.add(((CodeBlock) sentenceOrCode).clone());
            } else if (sentenceOrCode.getClass() == PunctMark.class){
                newSentencesAndCode.add(((PunctMark) sentenceOrCode).clone());
            } else{
                throw new RuntimeException("Paragraph doesn't contain type this object");
            }
        }
        newParagraph.setSentencesAndCode(newSentencesAndCode);
        return newParagraph;
    }
}
