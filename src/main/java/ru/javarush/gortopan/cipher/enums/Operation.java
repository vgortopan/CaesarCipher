package ru.javarush.gortopan.cipher.enums;

public enum Operation {

    EXIT(0, "exit application", "Exit"),
    ENCRYPT(1, "encrypt text with key", "Encryption"),
    DECRYPT(2, "decrypt text with key", "Decryption"),
    BRUTEFORCE(3, "bruteforce decrypt without key", "Bruteforce"),
    STATIC_ANALYSIS(4, "static analysis without key", "Static analysis");

    public static final String COMMAND_PATTERN= "%d - %s";

    private final int command;

    private final String description;

    private final String action;

    Operation(int command, String description, String action) {
        this.command = command;
        this.description = description;
        this.action = action;
    }

    public int getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public String getAction() {
        return action;
    }

    public static Operation getOperation(int command) {
        for (Operation operation : Operation.values()) {
            if (operation.getCommand() == command) {
                return operation;
            }
        }
        throw new IllegalArgumentException("Please enter an existing option!");
    }
}
