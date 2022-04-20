package com.epam.jwd.task2.model.logic.parser;

import com.epam.jwd.task2.model.entity.CompositeTextElement;
import com.epam.jwd.task2.model.entity.RegEx;
import com.epam.jwd.task2.model.entity.leaf.CodeBlock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeParser extends TextElementParser{
    /** Regex for search opening curly brace in class, interface and etc */
    private final String FIRST_STRING_OF_CODE = "^[a-zA-Z].+(([{]\s*/{2}.*)$|.+([{]\s*)$|.+([{]\s*/\\*.*\\*/\s*)$)";

    /** Regex for search opening curly brace */
    private final String FIRST_CURLY_BRACE = "[{]+";

    /** Regex for search closing curly brace */
    private final String LAST_CURLY_BRACE = "[}]+";

    /** Regex for search init package and import package */
    private final String PACKAGE_STRING = "(^(package\s+)|^(import\s+))([a-zA-Z]+\\.)+[a-zA-Z]+\s*([;]\s*)$";

    private final Pattern MAJOR_PATTERN = Pattern.compile(FIRST_STRING_OF_CODE);
    private final Pattern FIRST_BRACE_PATTERN = Pattern.compile(FIRST_CURLY_BRACE);
    private final Pattern LAST_BRACE_PATTERN = Pattern.compile(LAST_CURLY_BRACE);
    private final Pattern PACKAGE_PATTERN = Pattern.compile(PACKAGE_STRING);

    /** contain code block */
    private final StringBuilder CODE_BLOCK_STRING = new StringBuilder();

    private int numberFirstCurlyBrace = 0;
    private int numberLastCurlyBrace = 0;


    public CodeParser(TextElementParser nextTextElementParser) {
        super(nextTextElementParser);
    }

    @Override
    public void parse(String textString, CompositeTextElement paragraph) {
        Matcher majorMatcher = MAJOR_PATTERN.matcher(textString);
        Matcher firstBraceMatcher = FIRST_BRACE_PATTERN.matcher(textString);
        Matcher lastBraceMatcher = LAST_BRACE_PATTERN.matcher(textString);
        Matcher packageMatcher = PACKAGE_PATTERN.matcher(textString);

        // find string 'import namePack.namePack...namePack;' or 'package namePack.namePack...namePack';
        if (packageMatcher.find() ) {
            CODE_BLOCK_STRING.append(textString).append(RegEx.LINE_BRAKE.getRegEx());
            paragraph.add(new CodeBlock(CODE_BLOCK_STRING.toString()));
            CODE_BLOCK_STRING.setLength(0);//clear value
            return;
        }
        else if (majorMatcher.find()) {                             // find first string declaration any class
            numberFirstCurlyBrace++;
            CODE_BLOCK_STRING.append(textString).append(RegEx.LINE_BRAKE.getRegEx());
        }
        else if(numberFirstCurlyBrace != numberLastCurlyBrace){     // calculate number first curly brace and last
            while(firstBraceMatcher.find()){
                numberFirstCurlyBrace++;
            }
            while(lastBraceMatcher.find()){
                numberLastCurlyBrace++;
            }
            CODE_BLOCK_STRING.append(textString).append(RegEx.LINE_BRAKE.getRegEx());
        }
        if(numberFirstCurlyBrace == numberLastCurlyBrace){          // if number curly braces are equal
            if (numberFirstCurlyBrace == 0 && numberLastCurlyBrace == 0) {  // if curly braces stay unchanged
                super.parse(textString, paragraph);
                isEndCodeBlock = false;
            }else{
                // block code found
                isEndCodeBlock = true;
                paragraph.add(new CodeBlock(CODE_BLOCK_STRING.toString()));
                CODE_BLOCK_STRING.setLength(0);//clear value
                numberLastCurlyBrace = 0;
                numberFirstCurlyBrace = 0;
            }
        }
        else{
            // continue record code string in codeBlockString
            isEndCodeBlock = false;
        }
    }
}
