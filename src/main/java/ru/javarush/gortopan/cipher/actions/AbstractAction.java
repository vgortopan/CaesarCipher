package ru.javarush.gortopan.cipher.actions;

import ru.javarush.gortopan.cipher.components.Alphabet;

import java.util.Scanner;

abstract public class AbstractAction implements Action {

    protected Scanner scanner = new Scanner(System.in);

    protected String getFile(String message) {
        System.out.println(message);
        return readString();
    }

    protected int getKey(String message) {
        System.out.println(message);
        return readInteger();
    }

    private int readInteger() {
        String value = readString();
        int i = 0;
        try {
            i = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number:");
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
        String decrypted = "";

        for (char symbol : chars) {
            index = alphabet.getIndexFromChar(Character.toLowerCase(symbol));
            newIndex = (index - key) % alphabet.size();
            if (newIndex < 0) {
                newIndex = alphabet.size() + newIndex;
            }
            decrypted += index == -1 ? symbol : alphabet.getCharFromIndex(newIndex);
        }
        return decrypted;
    }
}
