package lab5.command;

import lab5.exceptions.CommandInterruptedException;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandExecutor {
    public void startExecuting(InputStream input, CommandMode mode) {
        Scanner cmdScanner = new Scanner(input);
        CommandManager commandManager = new CommandManager(mode, cmdScanner);

        while (cmdScanner.hasNext()) {
            String line = cmdScanner.nextLine().trim();
            if (line.isEmpty()) continue;
            try {
                commandManager.executeCommand(line.split(" "));
                System.out.println();
            } catch (CommandInterruptedException | NoSuchElementException ex) {
                if (!mode.equals(CommandMode.CLI_UserMode))
                    System.err.println("Команда была пропущена.");
            }
        }
    }
}
