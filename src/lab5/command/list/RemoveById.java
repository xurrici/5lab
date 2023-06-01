package lab5.command.list;

import java.util.LinkedList;
import lab5.collection.SpaceMarine.SpaceMarine;
import lab5.collection.managers.SpaceMarineManager;
import lab5.collection.managers.CollectionManager;
import lab5.collection.managers.IdManager;
import lab5.command.Command;

public class RemoveById extends Command {

    public RemoveById() {
        super(true);
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescr() {
        return "удалить элемент из коллекции по его id";
    }
    @Override
    public void execute() {
        if (checkArgument(this.getArgument())) {
            CollectionManager<LinkedList<SpaceMarine>, SpaceMarine> manager = SpaceMarineManager.getInstance();
            if (manager.getCollection() == null) {
                System.out.println("Коллекция пуста");
                return;
            }

            Long finalId = IdManager.validateUserInput((String) this.getArgument());
            if (finalId == null) return;

            SpaceMarine spaceMarine = IdManager.checkById(finalId);
            if (spaceMarine != null) {
                manager.getCollection().remove(spaceMarine);
                System.out.println("Элемент удален!");
            } else System.out.println("Элемента с таким id-номером нет в текущей коллекции!");
        }
    }
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null) {
            System.out.println("Требуется один аргумент числового типа.");
            return false;
        }
        return true;
    }

}
