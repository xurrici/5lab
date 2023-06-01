package lab5.command.list;

import java.util.LinkedList;
import lab5.collection.managers.SpaceMarineManager;
import lab5.collection.managers.CollectionManager;
import lab5.command.Command;
import lab5.collection.SpaceMarine.SpaceMarine;

public class Show extends Command {

    public Show() {
        super(false);
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescr() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CollectionManager<LinkedList<SpaceMarine>, SpaceMarine> manager = SpaceMarineManager.getInstance();

            if (manager.getCollection() == null)
                System.out.println("Коллекция пуста");
            else {
                manager.getCollection().forEach(System.out::println);
            }
        }
    }
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Аргументы отсутствуют");
            return false;
        }
    }

}
