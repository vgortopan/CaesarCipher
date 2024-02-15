package ru.javarush.gortopan.cipher.actions;

import ru.javarush.gortopan.cipher.file.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BruteForce extends AbstractAction {
    private static final String GET_SOURCE_FILE_MESSAGE = "Enter the source file to decrypt:";
    private static final String GET_DESTINATION_FILE_MESSAGE = "Enter the destination file to save decrypted text:";
    private static final String GET_REPRESENTATION_FILE_MESSAGE = "Enter the representation file to compare:";
    @Override
    public void execute() {
        String sourceFile = getFile(GET_SOURCE_FILE_MESSAGE);
        String destinationFile = getFile(GET_DESTINATION_FILE_MESSAGE);
        String representationFile = getFile(GET_REPRESENTATION_FILE_MESSAGE);

        BufferedReader sourceReader = FileUtils.getReadBuffer(sourceFile);
        BufferedReader representationReader = FileUtils.getReadBuffer(representationFile);
        BufferedWriter writer = FileUtils.getWriteBuffer(destinationFile);

        Map<String, Integer> words = makeDictionary(representationReader);

        process(sourceReader, writer, words);
    }

    private void process(BufferedReader sourceReader, BufferedWriter writer, Map<String, Integer> dict) {
        try {
            String line;
            int key = 1;
            String decryptedLine = "";

            StringTokenizer tokenizer;

            boolean matchedDict = false;
            while (sourceReader.ready()) {
                line = sourceReader.readLine();

                if (line == null) {
                    line = "";
                }

                do {
                    decryptedLine = decrypt(line, key);
                    if (!matchedDict) {
                        tokenizer = new StringTokenizer(decryptedLine);
                        while (tokenizer.hasMoreTokens()) {
                            String word = tokenizer.nextToken();
                            if (word.length() > 2 && dict.containsKey(word)) {
                                matchedDict = true;
                                break;
                            }
                        }
                        if (!matchedDict) {
                            key++;
                        }

                    }

                } while (!matchedDict);
                writer.write(decryptedLine + System.lineSeparator());
                writer.flush();
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Integer> makeDictionary(BufferedReader representationReader) {
        try {
            String line;
            Map<String, Integer> words = new HashMap<>();
            StringTokenizer tokenizer;

            while (representationReader.ready()) {
                line = representationReader.readLine();

                if (line == null) {
                    continue;
                }
                tokenizer = new StringTokenizer(line);

                while (tokenizer.hasMoreTokens()) {
                    words.put(tokenizer.nextToken().toLowerCase(), 1);
                }
            }

            return words;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
