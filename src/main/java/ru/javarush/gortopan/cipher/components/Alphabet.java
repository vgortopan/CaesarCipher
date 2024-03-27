package ru.javarush.gortopan.cipher.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Alphabet {

    private static final Character[] ALPHABET_RU = {
            'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р',
            'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш',
            'щ', 'ъ', 'ы', 'ь', 'э', 'я'
    };

    private static final Character[] ALPHABET_EN = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    private static final Character[] CHARACTERS = {
            '.', ',', '«', '»', '"', '\'', ':', ';', '!', '?', '-', ' '
    };

    private static final Character[] NUMBERS = {
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
    };

    private final List<Character> alphabet;

    public Alphabet() {
        List<Character> temp = new ArrayList<>();
        temp.addAll(Arrays.asList(ALPHABET_RU));
//        temp.addAll(Arrays.asList(ALPHABET_EN));
        temp.addAll(Arrays.asList(CHARACTERS));
        temp.addAll(Arrays.asList(NUMBERS));
        this.alphabet = List.copyOf(temp);
    }

    public List<Character> getAlphabet() {
        return this.alphabet;
    }

    public int getIndexFromChar(Character character) {
        if (!alphabet.contains(character)) {
            return -1;
        }
        return alphabet.indexOf(character);
    }

    public Character getCharFromIndex(int index) {
        if (index < 0 || index >= alphabet.size()) {
            throw new RuntimeException("Out of bound index");
        }
        return alphabet.get(index);
    }

    public int size() {
        return alphabet.size();
    }

}
