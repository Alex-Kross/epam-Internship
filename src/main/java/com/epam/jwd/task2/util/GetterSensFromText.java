package com.epam.jwd.task2.util;

import com.epam.jwd.task2.model.entity.RegEx;
import com.epam.jwd.task2.model.entity.TextElement;
import com.epam.jwd.task2.model.entity.composite.Paragraph;
import com.epam.jwd.task2.model.entity.composite.Sentence;
import com.epam.jwd.task2.model.entity.composite.Text;
import com.epam.jwd.task2.model.entity.leaf.PunctMark;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class retrieves only sentences from text
 *
 * @author Karpuk A.U.
 * @version 1.1 20.2.2022
 * */
public class GetterSensFromText {
    /**
     * Takes only sentences from text and return list of sentences
     *
     * @param text text
     */
    public static List<TextElement> getSentences(Text text) throws CloneNotSupportedException {
        if (text == null) {
            throw new RuntimeException("text is null");
        }

        Text text1 = null;
        text1 = text.clone();

        List<TextElement> sentenceList = new ArrayList<>();

        for (TextElement paragraph : text1.getParagraphs()) {
            List<TextElement> sentenceOrCode = ((Paragraph) paragraph).getSentencesAndCode();
            for (TextElement sentence : sentenceOrCode) {
                if (sentence.getClass() == Sentence.class) {
                    removeExcessLineBrake((Sentence) sentence);
                    sentenceList.add(sentence);
                }
            }
        }
        return sentenceList;
    }

    /**
     * Remove excess line brake.
     * Remove all line brakes and add one line brake in the end of sentence
     *
     * @param sentence one sentence from the list of sentence
     */
    private static void removeExcessLineBrake(Sentence sentence){
        List<TextElement> listWordsOrPunctMarks = sentence.getWordsAndPunctMarks();
        Iterator iterator = listWordsOrPunctMarks.iterator();

        while (iterator.hasNext()) {
            TextElement wordOrPunctMark = (TextElement) iterator.next();
            if (wordOrPunctMark.getClass() == PunctMark.class) {
                if (wordOrPunctMark.getElementText().equals(RegEx.LINE_BRAKE.getRegEx())) {
                    iterator.remove();
                }
            }

        }
        listWordsOrPunctMarks.add(new PunctMark(RegEx.LINE_BRAKE.getRegEx()));
        sentence.setWordsAndPunctMarks(listWordsOrPunctMarks);
    }
}
