package ru.javarush.gortopan.cipher.exceptions;

import java.io.IOException;

public class MissingSourceFileException extends IOException {

    public MissingSourceFileException(String message) {
        super(message);
    }
}
