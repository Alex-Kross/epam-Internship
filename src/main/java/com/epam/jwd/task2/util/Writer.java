package com.epam.jwd.task2.util;

import com.epam.jwd.task2.model.logic.exception.implFileException.FileErrorException;
import com.epam.jwd.task2.model.logic.exception.implFileException.FileNotLocateException;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    public void writeText(String fileName, String text) throws FileErrorException, FileNotLocateException{
        if (fileName == null || text == null) {
            throw new NullPointerException("Name output file is null or text is null");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(text);
        } catch (FileNotFoundException e) {
            throw new FileNotLocateException("Output file not found");
        } catch (IOException e) {
            throw new FileErrorException("Output file error");
        }
    }
}
