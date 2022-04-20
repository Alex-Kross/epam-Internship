package com.epam.jwd.task2.model.logic.validator;

import com.epam.jwd.task2.model.logic.exception.UserInputException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserEntryValidator {
    private final String USER_INPUT = "^\\d$";
    private final Pattern USER_INPUT_PATTERN = Pattern.compile(USER_INPUT);

    public int validate(Scanner scanner){
        if (scanner == null) {
            throw new NullPointerException("Scanner is null");
        }
        int number = 0;
        String userInput = scanner.next();
        Matcher userInputMatcher = USER_INPUT_PATTERN.matcher(userInput);

        while (!userInputMatcher.find()){
            throw new UserInputException("Input only number");
        }
        number = Integer.parseInt(userInput);
        return number;
    }
}
