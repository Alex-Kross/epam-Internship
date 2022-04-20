package com.epam.jwd.task2.view;

/**
 * Class for output information on console
 *
 * @author Karpuk A.U.
 * @version 1.1 20.2.2022
 * */
public class Printer {
    /**
     * Print string message
     *
     * @param message string of text
     */
    public static void printInConsoleMessage(String message){
        if (message == null) {
            throw new NullPointerException("Message is null");
        }
        System.out.println(message);
    }
}
