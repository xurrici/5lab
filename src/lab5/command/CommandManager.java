package lab5.command;

import lab5.command.list.Help;
import lab5.command.list.ExecuteScript;
import lab5.command.list.Save;
import lab5.command.list.RemoveGreater;
import lab5.command.list.Add;
import lab5.command.list.RemoveById;
import lab5.command.list.Info;
import lab5.command.list.SumOfHealth;
import lab5.command.list.AddIfMax;
import lab5.command.list.UpdateId;
import lab5.command.list.Show;
import lab5.command.list.Exit;
import lab5.command.list.Clear;
import lab5.collection.managers.mode.ModeManager;
import lab5.collection.managers.mode.nonUserMode.SpaceMarineNonUserManager;
import lab5.collection.managers.mode.userMode.SpaceMarineCLIManager;
import lab5.exceptions.BuildObjectException;
import lab5.exceptions.CommandInterruptedException;
import lab5.exceptions.UnknownCommandException;

import java.util.*;
import lab5.collection.SpaceMarine.SpaceMarine;
import lab5.command.list.FilterLessThanWeaponType;
import lab5.command.list.PrintUniqueHealth;
import lab5.command.list.RemoveLower;

public class CommandManager {
    private LinkedHashMap<String, Command> commandMap;
    public CommandManager(){
        commandMap = new LinkedHashMap<>();

        commandMap.put("help", new Help());
        commandMap.put("info", new Info());
        commandMap.put("show", new Show());
        commandMap.put("add", new Add());
        commandMap.put("update_id", new UpdateId());
        commandMap.put("remove_by_id", new RemoveById());
        commandMap.put("clear", new Clear());
        commandMap.put("save", new Save());
        commandMap.put("execute_script", new ExecuteScript());
        commandMap.put("exit", new Exit());
        commandMap.put("add_if_max", new AddIfMax());
        commandMap.put("remove_greater", new RemoveGreater());
        commandMap.put("remove_lower", new RemoveLower());
        commandMap.put("sum_of_health", new SumOfHealth());
        commandMap.put("filter_less_than_weapon_type", new FilterLessThanWeaponType());
        commandMap.put("print_unique_health", new PrintUniqueHealth());
    };
    public CommandManager(CommandMode mode, Scanner scanner) {
        commandMap = new LinkedHashMap<>();

        commandMap.put("help", new Help());
        commandMap.put("info", new Info());
        commandMap.put("show", new Show());
        commandMap.put("remove_by_id", new RemoveById());
        commandMap.put("clear", new Clear());
        commandMap.put("save", new Save());
        commandMap.put("execute_script", new ExecuteScript());
        commandMap.put("exit", new Exit());
        commandMap.put("sum_of_health", new SumOfHealth());
        commandMap.put("filter_less_than_weapon_type", new FilterLessThanWeaponType());
        commandMap.put("print_unique_health", new PrintUniqueHealth());

        ModeManager<SpaceMarine> handler = null;
        switch (mode)
        {
            case CLI_UserMode -> handler = new SpaceMarineCLIManager();
            case NonUserMode -> handler = new SpaceMarineNonUserManager(scanner);
        }
        commandMap.put("add", new Add(handler));
        commandMap.put("update_id", new UpdateId(handler));
        commandMap.put("add_if_max", new AddIfMax(handler));
        commandMap.put("remove_greater", new RemoveGreater(handler));
        commandMap.put("remove_lower", new RemoveLower(handler));
    }
    public HashMap<String, Command> getCommandMap() {
        return commandMap;
    }
    public void executeCommand(String[] args) {
        try {
            if (args.length > 1)
                Optional.ofNullable(commandMap.get(args[0])).orElseThrow(() -> new UnknownCommandException("\nКоманда " + args[0] + " не найдена")).setArgument(args[1]);
            Optional.ofNullable(commandMap.get(args[0])).orElseThrow(() -> new UnknownCommandException("\nКоманда " + args[0] + " не найдена")).execute();
        } catch (IllegalArgumentException | NullPointerException | NoSuchElementException e) {
            System.err.println("Выполнение команды пропущено из-за неправильных предоставленных аргументов! (" + e.getMessage() + ")");
            throw new CommandInterruptedException(e);
        } catch (BuildObjectException | UnknownCommandException e) {
            System.err.println(e.getMessage());
            throw new CommandInterruptedException(e);
        } catch (Exception e) {
            System.err.println("В командном менеджере произошла непредвиденная ошибка! ");
            throw new CommandInterruptedException(e);
        }
    }
}
