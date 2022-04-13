package com.epam.jwd.task2.util;

import com.epam.jwd.task2.model.entity.RegEx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    public String readText(String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        String str;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((str = reader.readLine()) != null) {
                stringBuilder.append(str);
                stringBuilder.append(RegEx.LINE_BRAKE.getRegEx());
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return stringBuilder.toString();
    }
}
