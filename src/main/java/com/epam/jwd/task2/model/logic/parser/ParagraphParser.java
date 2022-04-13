package com.epam.jwd.task2.model.logic.parser;

import com.epam.jwd.task2.model.entity.CompositeTextElement;
import com.epam.jwd.task2.model.entity.RegEx;
import com.epam.jwd.task2.model.entity.composite.Paragraph;
import com.epam.jwd.task2.model.entity.leaf.PunctMark;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends TextElementParser {
    /** Regex for search empty string*/
    private final String EMPTY_STRING = "^\s*$";

    /** Regex for search block of code  */
    private final String FIRST_STRING_OF_CODE = "^[a-zA-Z].+(([{]\s*/{2}.*)$|.+([{]\s*)$|.+([{]\s*/\\*.*\\*/\s*)$)";

    private final Pattern blockCodePattern = Pattern.compile(FIRST_STRING_OF_CODE);
    private final Pattern paragraphPattern = Pattern.compile(EMPTY_STRING);

    private boolean isStartCodeBlock =false;
    private Paragraph paragraph;

    public ParagraphParser(TextElementParser nextTextElementParser) {
        super(nextTextElementParser);
    }

    @Override
    public void parse(String textString, CompositeTextElement text) {
        if (text == null) {
            throw new RuntimeException("text is null");
        }
        Matcher paragraphMatcher = paragraphPattern.matcher(textString);
        Matcher blockCodeMatcher = blockCodePattern.matcher(textString);

        if (blockCodeMatcher.find() && !isStartCodeBlock) {     //if the beginning of the code block is found
            paragraph = new Paragraph();
            text.add(paragraph);
            isStartCodeBlock = true;
        }
        else if (isEndCodeBlock) {      //if the ending of the code block is found
                isEndCodeBlock = false;
                isStartCodeBlock = false;
                paragraph = new Paragraph();
                text.add(paragraph);
        }

        if (paragraphMatcher.find() && !isStartCodeBlock){      // if empty string found
            paragraph.add(new PunctMark(RegEx.LINE_BRAKE.getRegEx()));
        }
        else{
            super.parse(textString, paragraph);
        }
    }
}
