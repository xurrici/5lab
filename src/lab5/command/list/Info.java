package lab5.command.list;

import java.util.LinkedList;
import lab5.collection.managers.SpaceMarineManager;
import lab5.collection.managers.CollectionManager;
import lab5.command.Command;
import lab5.collection.SpaceMarine.SpaceMarine;

public class Info extends Command {

    public Info() {
        super(false);
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescr() {
        return "вывести в стандартный поток вывода информацию о коллекции";
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CollectionManager<LinkedList<SpaceMarine>, SpaceMarine> manager = SpaceMarineManager.getInstance();
            if (manager.getCollection() == null)
                System.out.println("Информация отсутствует.");
            else {
                System.out.println("Тип колекции: " + manager.getCollection().getClass().toString());
                System.out.println("Дата инициализации: " + manager.getFirstOrNew().getCreationDate().toString());
                System.out.println("Количество элементов: " + manager.getCollection().size());
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
