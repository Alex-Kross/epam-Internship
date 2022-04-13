package com.epam.jwd.task2.model.logic.parser;

import com.epam.jwd.task2.model.entity.CompositeTextElement;
import com.epam.jwd.task2.model.entity.RegEx;

public class TextParser {
    public static void parseText(CompositeTextElement text, String sourceText){
        TextElementParser parser = getParserChain();
        String[] strings = sourceText.split(RegEx.LINE_BRAKE.getRegEx());
        for (String string : strings) {
            parser.parse(string, text);
        }
    }

    private static TextElementParser getParserChain(){
        SentenceParser sentenceParser = new SentenceParser(null);
        CodeParser codeParser = new CodeParser(sentenceParser);
        return new ParagraphParser(codeParser);
    }
}
