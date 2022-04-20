package com.epam.jwd.task2.model.logic.parser;

import com.epam.jwd.task2.model.entity.CompositeTextElement;

public abstract class TextElementParser {
    protected static boolean isEndCodeBlock = true;
    private TextElementParser nextTextElementParser;

    public TextElementParser(TextElementParser nextTextElementParser) {
        this.nextTextElementParser = nextTextElementParser;
    }

    public void parse(String text, CompositeTextElement textElement){
        if (nextTextElementParser != null) {
            nextTextElementParser.parse(text, textElement);
        }
    }
}
