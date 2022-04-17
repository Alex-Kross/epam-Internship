package com.epam.jwd.task2.model.logic.parser;

import com.epam.jwd.task2.model.entity.CompositeTextElement;
import com.epam.jwd.task2.model.entity.RegEx;
import com.epam.jwd.task2.model.entity.composite.Text;
import com.epam.jwd.task2.util.RemoverLastLineBrake;

public class TextParser {
    public static void parseText(CompositeTextElement textElement, String textFromSourceFile){
        TextElementParser parser = getParserChain();
        String[] listStringOfText = textFromSourceFile.split(RegEx.LINE_BRAKE.getRegEx());

        for (int i = 0; i < listStringOfText.length; i++) {
            parser.parse(listStringOfText[i], textElement);
            if (i+1 >= listStringOfText.length){
                // remove last line brake of the end int the text
                RemoverLastLineBrake.remove((Text) textElement);
            }
        }
    }

    private static TextElementParser getParserChain(){
        SentenceParser sentenceParser = new SentenceParser(null);
        CodeParser codeParser = new CodeParser(sentenceParser);
        return new ParagraphParser(codeParser);
    }
}
