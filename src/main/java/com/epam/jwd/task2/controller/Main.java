package com.epam.jwd.task2.controller;

import com.epam.jwd.task2.model.entity.CompositeTextElement;
import com.epam.jwd.task2.model.entity.TextElement;
import com.epam.jwd.task2.model.entity.composite.Text;
import com.epam.jwd.task2.model.logic.exception.FileException;
import com.epam.jwd.task2.model.logic.exception.UserInputException;
import com.epam.jwd.task2.model.logic.exception.ValueEmptyException;
import com.epam.jwd.task2.model.logic.exception.implFileException.FileErrorException;
import com.epam.jwd.task2.model.logic.exception.implFileException.FileNotLocateException;
import com.epam.jwd.task2.model.logic.parser.TextParser;
import com.epam.jwd.task2.model.logic.task10.SpecialWordSorter;
import com.epam.jwd.task2.model.logic.task10.SpecialWordCounter;
import com.epam.jwd.task2.model.logic.task2.SentenceSorter;
import com.epam.jwd.task2.model.logic.task5.WordSwapper;
import com.epam.jwd.task2.model.logic.validator.UserEntryValidator;
import com.epam.jwd.task2.util.ToStringConverter;
import com.epam.jwd.task2.util.SensFromTextGetter;
import com.epam.jwd.task2.util.Reader;
import com.epam.jwd.task2.util.SpecialWordParser;
import com.epam.jwd.task2.util.Writer;
import com.epam.jwd.task2.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String INPUT_TEXT_FILE = "src/main/resources/inputFile.txt";
    private static final String OUTPUT_TEXT_FILE = "src/main/resources/outputFile.txt";
    private static final String SPECIAL_WORDS_FILE = "src/main/resources/listSpecialWords.txt";
    private static final String LIST_OPERATION = "\n1 - Parsing text\n2 - Run task1\n3 - Run task2\n4 - Run task5\n0 - Exit:\nEnter: ";
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args){
        List<String> specialWords;
        Reader textReader = new Reader();
        Writer textWriter = new Writer();
        List<TextElement> sentences = null;
        int userChoice;
        boolean loop = true;

        // clear outputFile
        try {
            textWriter.writeText(OUTPUT_TEXT_FILE, "");
        } catch (FileNotLocateException | FileErrorException e) {
            LOGGER.warn(e.getMessage(), e);
            Printer.printInConsoleMessage(e.getMessage());
        }

        while(loop){
            Printer.printInConsoleMessage(LIST_OPERATION);
            try {
                Scanner scanner = new Scanner(System.in);
                UserEntryValidator entryValidator = new UserEntryValidator();
                userChoice = entryValidator.validate(scanner);
                switch (userChoice) {
                    case 1:     //parsing textElement
                        CompositeTextElement textElement = new Text();
                        String textFromSourceFile = textReader.readText(INPUT_TEXT_FILE);       // read from file
                        TextParser.parseText(textElement, textFromSourceFile);                  // parsing
                        textWriter.writeText(OUTPUT_TEXT_FILE, textElement.getElementText());   // write in file
                        sentences = SensFromTextGetter.getSentences(((Text) textElement));      // get list sentence
                        Printer.printInConsoleMessage("Text has parsed");
                        break;
                    case 2:     //task2
                        // Sort sentence
                        SentenceSorter task2 = new SentenceSorter(sentences);
                        task2.sortAscendingByNumberWords();

                        // convert result to string
                        String strSentences = ToStringConverter.convertSentences(task2.getSentenceList());
                        Printer.printInConsoleMessage(strSentences);
                        break;
                    case 3:     //task5
                        // swap last and first word in each sentence
                        WordSwapper task5 = new WordSwapper(sentences);
                        task5.reversPlaceLastAndFirstWord();

                        // convert result to string
                        String strSentences2 = ToStringConverter.convertSentences(task5.getSentenceList());
                        Printer.printInConsoleMessage(strSentences2);
                        break;
                    case 4:     //task10
                        //read special words from file
                        String strSpecialWords = textReader.readText(SPECIAL_WORDS_FILE);
                        specialWords = SpecialWordParser.parse(strSpecialWords);

                        // calculate number special words in each sentences
                        SpecialWordCounter task10Part1 = new SpecialWordCounter(sentences, specialWords);
                        task10Part1.CalcNumberSpecialWords();

                        // convert result to string
                        String strNumberSpecialWord = ToStringConverter.convertNumberSpecialWord(task10Part1.getNumberSpecialWords(), specialWords);
                        Printer.printInConsoleMessage(strNumberSpecialWord);

                        // sort total number special words in textElement
                        SpecialWordSorter task10Part2 = new SpecialWordSorter(task10Part1.getNumberSpecialWords());
                        task10Part2.sortInDescendingSpecialWords();

                        // convert result to string
                        String strSorterSpecialWord = ToStringConverter.convertSortedSpecialWord(task10Part2.getSortedSpecialWordIndexes(), specialWords);
                        Printer.printInConsoleMessage(strSorterSpecialWord);
                        break;
                    case 0:
                        loop = false;
                        break;
                    default:
                        throw new UserInputException("Incorrect input!");
                }
            } catch (UserInputException | ValueEmptyException | FileException e) {
                LOGGER.warn(e.getMessage(), e);
                Printer.printInConsoleMessage(e.getMessage());
            } catch (CloneNotSupportedException | RuntimeException e ) {
                LOGGER.warn(e.getMessage(), e);
                Printer.printInConsoleMessage(e.getMessage());
            } catch (Exception e ) {
                LOGGER.warn(e.getMessage(), e);
                Printer.printInConsoleMessage(e.getMessage());
            }
        }
    }
}
