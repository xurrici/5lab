package lab5.command.list;

import java.util.LinkedList;
import lab5.collection.SpaceMarine.SpaceMarine;
import lab5.collection.managers.CollectionManager;
import lab5.collection.managers.mode.ModeManager;
import lab5.command.Command;

import lab5.collection.managers.SpaceMarineManager;
import lab5.exceptions.BuildObjectException;

public class AddIfMax extends Command {
    ModeManager<SpaceMarine> handler;
    public AddIfMax() {
        super(false);
    }
    public AddIfMax(ModeManager<SpaceMarine> handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getDescr() {
        return "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            CollectionManager<LinkedList<SpaceMarine>, SpaceMarine> manager = SpaceMarineManager.getInstance();
            if (manager.getCollection() == null) {
                System.out.println("Команда недоступна");
                return;
            }
            SpaceMarine newCity = handler.buildObject();
            if (newCity.getHealth() > manager.getCollection().stream().mapToDouble(SpaceMarine::getHealth).max().orElse(0)) {
                manager.addElementToCollection(newCity);
                System.out.println("Элемент добавлен!");
            } else {
                System.out.println("Элемент не был добавлен. Его значение меньше максимального");
            }

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
