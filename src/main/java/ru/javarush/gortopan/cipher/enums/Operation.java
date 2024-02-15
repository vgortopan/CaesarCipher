package ru.javarush.gortopan.cipher.enums;

public enum Operation {

    EXIT(0, "exit application"),
    ENCRYPT(1, "encrypt text with key"),
    DECRYPT(2, "decrypt text with key"),
    BRUTEFORCE(3, "bruteforce decrypt without key"),
    STATIC_ANALYSIS(4, "static analysis without key");

    public static final String COMMAND_PATTERN= "%d - %s";

    private final int command;

    private final String description;

    Operation(int command, String description) {
        this.command = command;
        this.description = description;
    }

    public int getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
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
