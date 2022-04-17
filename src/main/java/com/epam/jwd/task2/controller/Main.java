package com.epam.jwd.task2.controller;

import com.epam.jwd.task2.model.entity.CompositeTextElement;
import com.epam.jwd.task2.model.entity.TextElement;
import com.epam.jwd.task2.model.entity.composite.Text;
import com.epam.jwd.task2.model.logic.validator.UserEntryValidator;
import com.epam.jwd.task2.model.logic.parser.TextParser;
import com.epam.jwd.task2.model.logic.task10.SorterSpecialWord;
import com.epam.jwd.task2.model.logic.task10.SpecialWordCounter;
import com.epam.jwd.task2.model.logic.task2.SentenceSorter;
import com.epam.jwd.task2.model.logic.task5.WordSwapper;
import com.epam.jwd.task2.util.ConverterToString;
import com.epam.jwd.task2.util.GetterSensFromText;
import com.epam.jwd.task2.util.Reader;
import com.epam.jwd.task2.util.SpecialWordParser;
import com.epam.jwd.task2.util.Writer;
import com.epam.jwd.task2.view.Printer;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String INPUT_TEXT_FILE = "src/main/resources/inputFile.txt";
    private static final String OUTPUT_TEXT_FILE = "src/main/resources/outputFile.txt";
    private static final String SPECIAL_WORDS_FILE = "src/main/resources/listSpecialWords.txt";
    private static final String LIST_OPERATION = "\n1 - Parsing text\n2 - Run task1\n3 - Run task2\n4 - Run task5\n0 - Exit:\nEnter: ";

    public static void main(String[] args){
        List<String> specialWords;
        Reader textReader = new Reader();
        Writer textWriter = new Writer();
        List<TextElement> sentences = null;
        int userChoice;
        boolean loop = true;

        textWriter.writeText(OUTPUT_TEXT_FILE, "");     // clear outputFile

        while(loop){
            Printer.printInConsoleMessage(LIST_OPERATION);

            try {
                Scanner scanner = new Scanner(System.in);
                UserEntryValidator entryValidator = new UserEntryValidator();
                userChoice = entryValidator.validate(scanner);

                switch (userChoice) {
                    case 1:     //parsing text
                        CompositeTextElement text = new Text();
                        String sourceText = textReader.readText(INPUT_TEXT_FILE);       // read from file
                        TextParser.parseText(text, sourceText);                         // parsing
                        textWriter.writeText(OUTPUT_TEXT_FILE, text.getElementText());  // write in file
                        sentences = GetterSensFromText.getSentences(((Text) text));      // get list sentence
                        Printer.printInConsoleMessage("Text has parsed");
                        break;
                    case 2:     //task2
                        // Sort sentence
                        SentenceSorter task2 = new SentenceSorter(sentences);
                        task2.sortAscendingByNumberWords();

                        // convert result to string
                        String strSentences = ConverterToString.convertSentences(task2.getSentenceList());
                        Printer.printInConsoleMessage(strSentences);
                        break;
                    case 3:     //task5
                        // swap last and first word in each sentence
                        WordSwapper task5 = new WordSwapper(sentences);
                        task5.reversPlaceLastAndFirstWord();

                        // convert result to string
                        String strSentences2 = ConverterToString.convertSentences(task5.getSentenceList());
                        Printer.printInConsoleMessage(strSentences2);
                        break;
                    case 4:     //task10
                        //read special words from file
                        String specialWordsText = textReader.readText(SPECIAL_WORDS_FILE);
                        specialWords = SpecialWordParser.parse(specialWordsText);

                        // calculate number special words in each sentences
                        SpecialWordCounter task10Part1 = new SpecialWordCounter(sentences, specialWords);
                        task10Part1.CalcNumberSpecialWordsInSen();

                        // convert result to string
                        String strNumberSpecialWord = ConverterToString.convertNumberSpecialWord(task10Part1.getNumberSpecialWordsInSentences(), specialWords);
                        Printer.printInConsoleMessage(strNumberSpecialWord);

                        // sort total number special words in text
                        SorterSpecialWord task10Part2 = new SorterSpecialWord(task10Part1.getNumberSpecialWordsInSentences());
                        task10Part2.sortInDescendingSpecialWords();

                        // convert result to string
                        String strSorterSpecialWord = ConverterToString.convertSorterSpecialWord(task10Part2.getSortedTotalNumberSpecial(), specialWords);
                        Printer.printInConsoleMessage(strSorterSpecialWord);
                        break;
                    case 0:
                        loop = false;
                        break;
                    default:
                        Printer.printInConsoleMessage("Incorrect input!");
                }
            }
            catch (RuntimeException |  CloneNotSupportedException e){
                Printer.printInConsoleMessage(e.getMessage());
            }
        }
    }
}
