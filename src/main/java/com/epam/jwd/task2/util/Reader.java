package com.epam.jwd.task2.util;

import com.epam.jwd.task2.model.entity.RegEx;
import com.epam.jwd.task2.model.logic.exception.implFileException.FileErrorException;
import com.epam.jwd.task2.model.logic.exception.implFileException.FileNotLocateException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    public String readText(String fileName) throws FileErrorException, FileNotLocateException {
        if (fileName == null) {
            throw new NullPointerException("Name source file is null");
        }

        StringBuilder stringBuilder = new StringBuilder();
        String str;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((str = reader.readLine()) != null) {
                stringBuilder.append(str);
                stringBuilder.append(RegEx.LINE_BRAKE.getRegEx());
            }
        } catch (FileNotFoundException e) {
            throw new FileNotLocateException("Source file not found");
        } catch (IOException e) {
            throw new FileErrorException("Source file error");
        }
        return stringBuilder.toString();
    }
}
