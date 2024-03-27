package ru.javarush.gortopan.cipher.actions;

import ru.javarush.gortopan.cipher.components.Alphabet;
import ru.javarush.gortopan.cipher.exceptions.AlphabetSizeException;
import ru.javarush.gortopan.cipher.exceptions.CreateNewFileException;
import ru.javarush.gortopan.cipher.exceptions.MissingSourceFileException;
import ru.javarush.gortopan.cipher.file.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;

abstract public class AbstractAction implements Action {

    protected Scanner scanner = new Scanner(System.in);



    private String getFile(String message) {
        System.out.println(message);
        return readString();
    }

    protected BufferedReader getFileReader(String message) {
        BufferedReader reader = null;
        try {
            String fileName = getFile(message);
            reader = FileUtils.getReadBuffer(fileName);
        } catch (MissingSourceFileException exception) {
            System.out.println(exception.getMessage());
            getFileReader(message);
        }
        return reader;
    }

    protected BufferedReader[] getFileReaders(String message) {
        BufferedReader[] reader = new BufferedReader[2];
        try {
            String fileName = getFile(message);
            reader[0] = FileUtils.getReadBuffer(fileName);
            reader[1] = FileUtils.getReadBuffer(fileName);
        } catch (MissingSourceFileException exception) {
            System.out.println(exception.getMessage());
            getFileReaders(message);
        }
        return reader;
    }

    protected BufferedWriter getFileWriter(String message) {
        BufferedWriter writer = null;
        try {
            String fileName = getFile(message);
            writer = FileUtils.getWriteBuffer(fileName);
        } catch (CreateNewFileException e) {
            System.out.println(e.getMessage());
            getFileWriter(message);
        }
        return writer;
    }

    protected int getKey(String message) {
        System.out.println(message);
        return readInteger();
    }

    private int readInteger() {
        String value = readString();
        int i = 0;
        Alphabet alphabet = new Alphabet();
        try {
            i = Integer.parseInt(value);
            if (i < 0 || i > alphabet.size()) {
                throw new AlphabetSizeException("Please enter a number between 0 and " + alphabet.size());
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number:");
            readInteger();
        } catch (AlphabetSizeException e) {
            System.out.println(e.getMessage());
            readInteger();
        }
        return i;
    }

    private String readString() {
        return scanner.nextLine();
    }

    protected String decrypt(String source, int key) {

        Alphabet alphabet = new Alphabet();

        char[] chars = source.toCharArray();
        int index;
        int newIndex;
        StringBuilder decrypted = new StringBuilder();

        for (char symbol : chars) {
            index = alphabet.getIndexFromChar(Character.toLowerCase(symbol));
            newIndex = (index - key) % alphabet.size();
            if (newIndex < 0) {
                newIndex = alphabet.size() + newIndex;
            }
            decrypted.append(index == -1 ? symbol : alphabet.getCharFromIndex(newIndex));
        }
        return decrypted.toString();
    }
}
