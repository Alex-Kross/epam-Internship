package com.epam.jwd.task2.util;

import com.epam.jwd.task2.model.entity.TextElement;
import com.epam.jwd.task2.model.entity.composite.Paragraph;
import com.epam.jwd.task2.model.entity.composite.Sentence;
import com.epam.jwd.task2.model.entity.composite.Text;
import com.epam.jwd.task2.model.entity.leaf.CodeBlock;

import java.util.List;

/**
 * Class remove last line brake in text
 *
 * @author Karpuk A.U.
 * @version 1.1 20.2.2022
 * */
public class LastLineBrakeRemover {
    /**
     * Remove last line brake in the end of textElement
     * Retrieve last paragraph from Text then retrieve last sentences or
     * code block from paragraph.
     * If we get a sentence then from list words and
     * punctuation marks delete last punct mark that is a
     * line brake.
     * If we get a codeBlock then trim string of
     * code block on 1 character from the end
     *
     * @param textElement object that contain text
     */
    public static void remove(Text textElement){
        if (textElement == null) {
            throw new NullPointerException("Text element is null");
        }
        // get last paragraph
        int indexLastParagraph = textElement.getParagraphs().size() - 1;
        TextElement lastParagraph = textElement.getParagraphs().get(indexLastParagraph);

        // get list sentence or block code from last paragraph
        List<TextElement> listSentenceOrCode = ((Paragraph) lastParagraph).getSentencesAndCode();

        // get last sentence or code block
        int indexLastSentenceOrCode = listSentenceOrCode.size() - 1;
        TextElement sentenceOrCode = listSentenceOrCode.get(indexLastSentenceOrCode);

        if (sentenceOrCode.getClass() == Sentence.class) {
            List<TextElement> listWordsOrPunctMarks = ((Sentence) sentenceOrCode).getWordsAndPunctMarks();
            listWordsOrPunctMarks.remove(listWordsOrPunctMarks.size()-1);
        }
        else if(sentenceOrCode.getClass() == CodeBlock.class){
            StringBuilder codeBlock = ((CodeBlock) sentenceOrCode).getCode();
            codeBlock.deleteCharAt(codeBlock.length()-1);
        }
    }
}
