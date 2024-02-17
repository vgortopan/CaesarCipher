package ru.javarush.gortopan.cipher.file;

import ru.javarush.gortopan.cipher.exceptions.CreateNewFileException;
import ru.javarush.gortopan.cipher.exceptions.MissingSourceFileException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    public static BufferedReader getReadBuffer(String fileName) throws MissingSourceFileException {
        try {
            Path path = Path.of(FilePathConstants.WORKING_DIR + fileName);
            return Files.newBufferedReader(path);
        } catch (IOException e) {
            throw new MissingSourceFileException("File '" + fileName + "'" + " doesn`t exist..\n" + "Please specify existing source file.");
        }
    }

    public static BufferedWriter getWriteBuffer(String fileName) throws CreateNewFileException {
        try {
            Path path = Path.of(FilePathConstants.WORKING_DIR + fileName);
            if (!Files.exists(path)) {
                System.out.println("File " + fileName + " doesn`t exist, creating new file.");
                Files.createFile(path);
            }
            return Files.newBufferedWriter(path);
        } catch (IOException e) {
            throw new CreateNewFileException();
        }
    }
}
