package ru.javarush.gortopan.cipher.actions;

public class Exit implements Action {
    @Override
    public void execute() {
        System.out.println("Exiting application... Bye!");
        System.exit(0);
    }
}
