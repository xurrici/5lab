package lab5.command.list;

import java.util.LinkedList;
import lab5.collection.SpaceMarine.SpaceMarine;
import lab5.collection.managers.SpaceMarineManager;
import lab5.collection.managers.CollectionManager;
import lab5.command.Command;

public class Clear extends Command {

    public Clear() {
        super(false);
    }
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescr() {
        return "очистить коллекциюn";
    }
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CollectionManager<LinkedList<SpaceMarine>, SpaceMarine> manager = SpaceMarineManager.getInstance();
            if (manager.getCollection() == null) {
                System.out.println("Коллекция недоступна");
                return;
            }
            manager.clearCollection();
            System.out.println("Коллекция очищена");
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
