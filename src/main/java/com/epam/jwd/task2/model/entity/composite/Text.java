package com.epam.jwd.task2.model.entity.composite;

import com.epam.jwd.task2.model.entity.CompositeTextElement;
import com.epam.jwd.task2.model.entity.TextElement;
import com.epam.jwd.task2.model.entity.leaf.PunctMark;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This Text contains list of paragraphs
 *
 * @author Karpuk A.U.
 * @version 1.1 20.2.2022
 * */
public class Text extends CompositeTextElement implements Cloneable{
    private List<TextElement> paragraphs;

    public Text() {
        this.paragraphs = new ArrayList<>();
    }

    @Override
    public String getElementText() {
        StringBuilder string = new StringBuilder();

        for (TextElement par : paragraphs) {
            string.append(par.getElementText());
        }
        return string.toString();
    }

    @Override
    public void add(TextElement textElement) {
        paragraphs.add(textElement);
    }

    @Override
    public void remove(TextElement textElement) {
        paragraphs.remove(textElement);
    }

    public List<TextElement> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<TextElement> paragraphs) {
        this.paragraphs = paragraphs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return Objects.equals(paragraphs, text.paragraphs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paragraphs);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (TextElement par : paragraphs) {
            string.append(par);
        }
        return string.toString();
    }

    @Override
    public Text clone() throws CloneNotSupportedException {
        Text newText = (Text) super.clone();
        List<TextElement> newParagraphs = new ArrayList<>(paragraphs.size());

        for (TextElement  paragraph: paragraphs) {
            if (paragraph.getClass() == Paragraph.class){
                newParagraphs.add(((Paragraph) paragraph).clone());
            } else if (paragraph.getClass() == PunctMark.class){
                newParagraphs.add(((PunctMark) paragraph).clone());
            } else{
                throw new RuntimeException("Text doesn't contain type this object");
            }
        }
        newText.setParagraphs(newParagraphs);
        return newText;
    }
}
