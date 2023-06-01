package lab5.command.list;

import java.util.LinkedList;
import lab5.collection.managers.SpaceMarineManager;
import lab5.collection.managers.CollectionManager;
import lab5.command.Command;
import lab5.collection.SpaceMarine.SpaceMarine;

public class PrintUniqueHealth extends Command {

    public PrintUniqueHealth() {
        super(false);
    }

    @Override
    public String getName() {
        return "print_unique_health";
    }

    @Override
    public String getDescr() {
        return "вывести уникальные значения поля health всех элементов в коллекции";
    }
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CollectionManager<LinkedList<SpaceMarine>, SpaceMarine> manager = SpaceMarineManager.getInstance();
            if (manager.getCollection() == null) {
                System.out.println("Команда недоступна");
                return;
            }
            manager.getCollection().stream()
                .mapToDouble(SpaceMarine::getHealth)
                .distinct()
                .forEach(System.out::println);
        }
    }
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Аргументы отсутствуют.");
            return false;
        }
    }

}
