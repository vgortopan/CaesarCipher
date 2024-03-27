package ru.javarush.gortopan.cipher.command;

import ru.javarush.gortopan.cipher.actions.Action;
import ru.javarush.gortopan.cipher.actions.ActionFactory;
import ru.javarush.gortopan.cipher.enums.Operation;

import java.util.Scanner;

public class Dialogue {

    private final Scanner scanner = new Scanner(System.in);

    boolean keepRunning = true;
    public void init() {
        Operation operation;
        do {
            showMainMenu();
            try {
                String input = scanner.nextLine();
                int operationNumber = Integer.parseInt(input);
                operation = Operation.getOperation(operationNumber);
                Action action = ActionFactory.create(operation);
                action.execute();
                System.out.println(operation.getAction() + " is finished");
                if (operation == Operation.EXIT) {
                    keepRunning = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please select one of the options below!");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        } while (keepRunning);
    }

    private void showMainMenu() {
        for (Operation operation : Operation.values()) {
            System.out.printf((Operation.COMMAND_PATTERN) + "%n", operation.getCommand(), operation.getDescription());
        }
    }
}
