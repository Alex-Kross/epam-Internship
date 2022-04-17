package com.epam.jwd.task2.model.logic.parser;

import com.epam.jwd.task2.model.entity.CompositeTextElement;
import com.epam.jwd.task2.model.entity.RegEx;
import com.epam.jwd.task2.model.entity.composite.Sentence;
import com.epam.jwd.task2.model.entity.leaf.PunctMark;
import com.epam.jwd.task2.model.entity.leaf.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends TextElementParser {
    /** Regex for search text string */
    private final String TEXT_STRING = "^\s*[a-zA-Z0-9\"(\\[].+(([a-zA-Z\")\\[];)$|.+[a-zA-Z0-9.!?,:;()/\\-\"\\[\\]\\\\]$)";

    /** Regex for search sentence */
    private final String SENTENCE = "[.?!]+[^a-zA-Z0-9]|[.?!]+\\s+|[.?!:]+$";

    /** Regex for search punctuation marks */
    private final String PUNCT_MARK = "[[\"][\\[][\\]][(][)]]\s*|[.,':!?;/[\"][\\[][\\]][(][)]]\s*";

    /** Regex for search word  */
    private final String WORD = "([^.,':!?;/[\"][\\[][\\]][(][)] ])+\\s*";

    private final Pattern TEXT_STRING_PATTERN = Pattern.compile(TEXT_STRING);
    private final Pattern SENTENCE_PATTERN = Pattern.compile(SENTENCE);
    private final Pattern PUNCT_MARK_PATTERN = Pattern.compile(PUNCT_MARK);
    private final Pattern WORD_PATTERN = Pattern.compile(WORD);

    /** contain sentence */
    private Sentence sentence = new Sentence();

    public SentenceParser(TextElementParser nextTextElementParser) {super(nextTextElementParser);}

    @Override
    public void parse(String textString, CompositeTextElement paragraph) {
        Matcher textStringMatcher = TEXT_STRING_PATTERN.matcher(textString);

        // if string is text
        if(textStringMatcher.find()){
            // list of index punctuation mark in the end of sentence
            List<Integer> listIndexDots = new ArrayList();

            // current index
            int currentIndex = 0;

            // index the end of string
            int lastIndex = textStringMatcher.end();

            Matcher sentenceMatcher = SENTENCE_PATTERN.matcher(textStringMatcher.group());
            Matcher punctMarkMatcher = PUNCT_MARK_PATTERN.matcher(textStringMatcher.group());

            while (sentenceMatcher.find()){
                listIndexDots.add(sentenceMatcher.end());
            }

            while (punctMarkMatcher.find()) {   // find punctuation mark
                // str before punctuation mark
                String strBeforePunctMark = textString.substring(currentIndex, punctMarkMatcher.start());
                Matcher wordMatcher = WORD_PATTERN.matcher(strBeforePunctMark);
                currentIndex = punctMarkMatcher.end();  // move current index

                while (wordMatcher.find()) {            // add all words in sentence
                    String word = wordMatcher.group();
                    sentence.add(new Word(word));
                }
                sentence.add(new PunctMark(punctMarkMatcher.group()));  // add punctuation mark

                if (lastIndex == currentIndex) {                // if reach to the end of string
                    sentence.add(new PunctMark(RegEx.LINE_BRAKE.getRegEx()));
                }
                if (listIndexDots.contains(currentIndex)){              // if this punctuation mark is the end of sentence
                    paragraph.add(sentence);
                    sentence = new Sentence();
                }
            }

            // if string without punctuation marks or string finish not punctuation mark
            if (lastIndex > currentIndex) {
                String strWithoutPunctMark = textStringMatcher.group().substring(currentIndex);
                Matcher wordMatcher = WORD_PATTERN.matcher(strWithoutPunctMark);
                while (wordMatcher.find()) {        // add all words in sentence
                    String word = wordMatcher.group();
                    sentence.add(new Word(word));
                }
                sentence.add(new PunctMark(RegEx.LINE_BRAKE.getRegEx()));
            }
        }
        else{
            super.parse(textString, paragraph);
        }
    }
}
