package ru.javarush.gortopan.cipher.actions;

import ru.javarush.gortopan.cipher.components.Alphabet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Encrypt extends AbstractAction {

    private static final String GET_SOURCE_FILE_MESSAGE = "Enter the source file to encrypt:";
    private static final String GET_DESTINATION_FILE_MESSAGE = "Enter the destination file to save encrypted text:";
    private static final String GET_KEY_MESSAGE = "Enter the encryption key:";
    @Override
    public void execute() {
        BufferedReader source = getFileReader(GET_SOURCE_FILE_MESSAGE);
        BufferedWriter destination = getFileWriter(GET_DESTINATION_FILE_MESSAGE);
        int key = getKey(GET_KEY_MESSAGE);
        process(source, destination, key);

    }

    private void process(BufferedReader source, BufferedWriter destination, int key) {
        try {
            String line;
            String encryptedLine;
            while (source.ready()) {
                line = source.readLine();

                if (line == null) {
                    line = "";
                }

                encryptedLine = encrypt(line, key);
                destination.write(encryptedLine + System.lineSeparator());
                destination.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String encrypt(String source, int key) {

        Alphabet alphabet = new Alphabet();

        char[] chars = source.toCharArray();
        int index;
        StringBuilder encrypted = new StringBuilder();

        for (char symbol : chars) {
            index = alphabet.getIndexFromChar(Character.toLowerCase(symbol));
            encrypted.append(index == -1 ? symbol : alphabet.getCharFromIndex((index + key) % alphabet.size()));
        }
        return encrypted.toString();
    }


}
