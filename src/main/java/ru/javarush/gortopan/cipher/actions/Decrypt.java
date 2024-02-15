package ru.javarush.gortopan.cipher.actions;

import ru.javarush.gortopan.cipher.file.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Decrypt extends AbstractAction {

    private static final String GET_SOURCE_FILE_MESSAGE = "Enter the source file to decrypt:";
    private static final String GET_DESTINATION_FILE_MESSAGE = "Enter the destination file to save decrypted text:";
    private static final String GET_KEY_MESSAGE = "Enter the decryption key:";
    @Override
    public void execute() {
        String sourceFile = getFile(GET_SOURCE_FILE_MESSAGE);
        String destinationFile = getFile(GET_DESTINATION_FILE_MESSAGE);
        int key = getKey(GET_KEY_MESSAGE);

        BufferedReader source = FileUtils.getReadBuffer(sourceFile);
        BufferedWriter destination = FileUtils.getWriteBuffer(destinationFile);
        process(source, destination, key);

    }

    private void process(BufferedReader source, BufferedWriter destination, int key) {
        try {
            String line;
            String decryptedLine;
            while (source.ready()) {
                line = source.readLine();

                if (line == null) {
                    line = "";
                }

                decryptedLine = decrypt(line, key);
                destination.write(decryptedLine + System.lineSeparator());
                destination.flush();
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
