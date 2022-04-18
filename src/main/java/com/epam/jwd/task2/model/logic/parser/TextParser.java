package com.epam.jwd.task2.model.logic.parser;

import com.epam.jwd.task2.model.entity.CompositeTextElement;
import com.epam.jwd.task2.model.entity.RegEx;
import com.epam.jwd.task2.model.entity.composite.Text;
import com.epam.jwd.task2.model.logic.exception.implEmptyException.FileEmptyException;
import com.epam.jwd.task2.util.RemoverLastLineBrake;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    private static final String NOT_EMPTY_STRING = "[a-zA-Z0-9]";
    private static final Pattern NOT_EMPTY_STRING_PATTERN = Pattern.compile(NOT_EMPTY_STRING);
    private static final String EMPTY_STRING = "^s*\\n$";
    private static final Pattern EMPTY_STRING_PATTERN = Pattern.compile(EMPTY_STRING);

    public static void parseText(CompositeTextElement textElement, String textFromSourceFile){
        if (textFromSourceFile == null || textElement == null) {
            throw new NullPointerException("Text from source file is null or text element is null ");
        }

        Matcher notEmptyStringMatcher = NOT_EMPTY_STRING_PATTERN.matcher(textFromSourceFile);
        Matcher emptyStringMatcher = EMPTY_STRING_PATTERN.matcher(textFromSourceFile);
        while (!notEmptyStringMatcher.find() || emptyStringMatcher.find()) {
            throw new FileEmptyException("File with source text is empty");
        }

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
