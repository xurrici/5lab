package lab5.command.list;

import lab5.collection.managers.SpaceMarineManager;
import lab5.collection.managers.CollectionManager;
import lab5.command.Command;
import lab5.command.CommandExecutor;
import lab5.command.CommandMode;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.regex.Pattern;
import lab5.collection.SpaceMarine.SpaceMarine;
public class ExecuteScript extends Command {
    public ExecuteScript() {
        super(true);
    }
    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescr() {
        return "считать и исполнить скрипт из указанного файла";
    }
    @Override
    public void execute() throws IllegalArgumentException {
        try {
            CollectionManager<LinkedList<SpaceMarine>, SpaceMarine> manager = SpaceMarineManager.getInstance();
            if (manager.getCollection() == null) {
                System.out.println("Команда недоступна");
                return;
            }

            CommandExecutor executor = new CommandExecutor();
            if (checkRecursion(Path.of((String) this.getArgument()), new ArrayDeque<>())) {
                System.out.println("При анализе скрипта обнаружена рекурсия. Устраните ее перед исполнением.");
                return;
            }
            System.out.println("Запуск скрипта");
            executor.startExecuting(new FileInputStream((String) this.getArgument()), CommandMode.NonUserMode);
        } catch (InvalidPathException e) {
            System.out.println("Неверный путь к файлу.");
            throw new IllegalArgumentException(e);
        } catch (FileNotFoundException | NoSuchFileException e) {
            System.out.println("Файл не найден.");
        } catch (SecurityException e) {
            System.out.println("Доступ к файлу запрешен.");
        } catch (IOException e) {
            System.out.println("Непредвиденная ошибка при попытке прочитать файл (" + e.getMessage() + ")");
        }
    }
    private boolean checkRecursion(Path path, ArrayDeque<Path> stack) throws IOException {
        if (stack.contains(path)) return true;
        stack.addLast(path);
        String str = Files.readString(path);
        Pattern pattern = Pattern.compile("execute_script .*");
        var patternMatcher = pattern.matcher(str);
        while (patternMatcher.find())
        {
            Path newPath = Path.of(patternMatcher.group().split(" ")[1]);
            if(checkRecursion(newPath, stack)) return true;
        }
        stack.removeLast();
        return false;
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null) {
            System.out.println("Аргумент - путь к файлу");
            return false;
        } else if (inputArgument instanceof String) {
            return true;
        }
        return false;
    }
}