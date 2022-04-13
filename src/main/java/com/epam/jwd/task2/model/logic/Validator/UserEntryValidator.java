package com.epam.jwd.task2.model.logic.Validator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserEntryValidator {
    private final String USER_INPUT = "^\\d$";
    private final Pattern userInputPattern = Pattern.compile(USER_INPUT);

    public int validate(Scanner scanner){
        if (scanner == null) {
            throw new RuntimeException("Scanner is null");
        }
        int number = 0;
        String userInput = scanner.next();
        Matcher userInputMatcher = userInputPattern.matcher(userInput);

        while (!userInputMatcher.find()){
            throw new RuntimeException("Input only number");
        }
        number = Integer.parseInt(userInput);
        return number;
    }
}
