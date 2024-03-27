package ru.javarush.gortopan.cipher.exceptions;

import java.io.IOException;

public class CreateNewFileException extends IOException {

    public CreateNewFileException() {
        super("There was a problem creating the destination file. Please try again.");
    }
}
