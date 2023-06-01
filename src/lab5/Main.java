package lab5;

import lab5.collection.managers.SpaceMarineManager;
import lab5.command.CommandExecutor;
import lab5.command.CommandMode;

public class Main {
    public static void main(String[] args) {
        SpaceMarineManager loader = new SpaceMarineManager();
        loader.loadCollection();
        System.out.println("Введите help для получения списка команд.");
        System.out.println();
        CommandExecutor executor = new CommandExecutor();
        executor.startExecuting(System.in, CommandMode.CLI_UserMode);
    }
}