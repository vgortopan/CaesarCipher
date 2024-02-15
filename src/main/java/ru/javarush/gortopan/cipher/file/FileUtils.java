package ru.javarush.gortopan.cipher.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    public static BufferedReader getReadBuffer(String fileName) {
        try {
            return Files.newBufferedReader(Path.of(FilePathConstants.WORKING_DIR + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedWriter getWriteBuffer(String fileName) {
        try {
            return Files.newBufferedWriter(Path.of(FilePathConstants.WORKING_DIR + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
