package ru.javarush.gortopan.cipher.actions;

import ru.javarush.gortopan.cipher.enums.Operation;

public class ActionFactory {

    public static Action create(Operation operation) {
        return switch (operation) {
            case EXIT -> new Exit();
            case ENCRYPT -> new Encrypt();
            case DECRYPT -> new Decrypt();
            case BRUTEFORCE -> new BruteForce();
            case STATIC_ANALYSIS -> new StaticAnalysis();
        };
    }
}
