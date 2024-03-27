package ru.javarush.gortopan.cipher.file;

import java.io.File;

public class FilePathConstants {

    private static final String FILES_DIR = "text";
    public static final String WORKING_DIR = System.getProperty("user.dir") + File.separator + FILES_DIR + File.separator;
    public static final String DICTIONARY = "dictionary.txt";
}
