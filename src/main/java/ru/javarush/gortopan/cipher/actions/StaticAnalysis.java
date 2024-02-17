package ru.javarush.gortopan.cipher.actions;

import ru.javarush.gortopan.cipher.components.Alphabet;
import ru.javarush.gortopan.cipher.exceptions.MissingSourceFileException;
import ru.javarush.gortopan.cipher.file.FilePathConstants;
import ru.javarush.gortopan.cipher.file.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class StaticAnalysis extends AbstractAction {
    private static final String GET_SOURCE_FILE_MESSAGE = "Enter the source file to decrypt:";
    private static final String GET_DESTINATION_FILE_MESSAGE = "Enter the destination file to save decrypted text:";
    @Override
    public void execute() {
        BufferedReader[] sourceReaders = getFileReaders(GET_SOURCE_FILE_MESSAGE);
        BufferedReader representationReader;
        try {
            representationReader = FileUtils.getReadBuffer(FilePathConstants.DICTIONARY);
        } catch (MissingSourceFileException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter writer = getFileWriter(GET_DESTINATION_FILE_MESSAGE);

        int key = getProbabilityShift(representationReader, sourceReaders[0]);
        process(sourceReaders[1], writer, key);

    }

    private int getProbabilityShift(BufferedReader representationReader, BufferedReader sourceReaderAnalytics) {

        char representationMostFrequentChar = getMostFrequentChar(representationReader);
        char sourceMostFrequentChar = getMostFrequentChar(sourceReaderAnalytics);

        Alphabet alphabet = new Alphabet();
        int representationKey = alphabet.getIndexFromChar(representationMostFrequentChar);
        int sourceKey = alphabet.getIndexFromChar(sourceMostFrequentChar);

        int key = sourceKey - representationKey;
        if (key < 0) {
            key += alphabet.size();
        }
        return key % alphabet.size();

    }

    private char getMostFrequentChar(BufferedReader reader) {
        TreeMap<Character, Integer> map = new TreeMap<>();
        Alphabet alphabet = new Alphabet();
        try {
            String line;
            while (reader.ready()) {
                line = reader.readLine();

                if (line == null || line.isEmpty() || " ".equals(line) || "\n".equals(line)) {
                    continue;
                }

                for (char c : line.toCharArray()) {
                    map.merge(Character.toLowerCase(c), 1, Integer::sum);
                }
            }

            int max = Integer.MIN_VALUE;
            char symbol = '-';
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue() > max && alphabet.getIndexFromChar(entry.getKey()) >= 0) {
                    max = entry.getValue();
                    symbol = entry.getKey();
                }
            }
            return symbol;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void process(BufferedReader sourceReader, BufferedWriter writer, int key) {
        try {
            String line;
            String decryptedLine;
            while (sourceReader.ready()) {
                line = sourceReader.readLine();

                if (line == null) {
                    line = "";
                }

                decryptedLine = decrypt(line, key);
                writer.write(decryptedLine + System.lineSeparator());
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
