package lab5.command.list;

import java.util.LinkedList;
import lab5.collection.managers.SpaceMarineManager;
import lab5.collection.managers.CollectionManager;
import lab5.collection.managers.IdManager;
import lab5.collection.managers.mode.ModeManager;
import lab5.command.Command;
import lab5.exceptions.BuildObjectException;
import lab5.collection.SpaceMarine.SpaceMarine;

public class UpdateId extends Command {

    ModeManager<SpaceMarine> modeManager;
    public UpdateId() {
        super(true);
    }
    public UpdateId(ModeManager<SpaceMarine> modeManager) {
        super(true);
        this.modeManager = modeManager;
    }

    @Override
    public String getName() {
        return "update_id {element}";
    }

    @Override
    public String getDescr() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }
    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            CollectionManager<LinkedList<SpaceMarine>, SpaceMarine> manager = SpaceMarineManager.getInstance();
            if (manager.getCollection() == null) {
                System.out.println("Команда недоступна");
                return;
            }

            Long finalId = IdManager.validateUserInput((String) this.getArgument());
            if (finalId == null) return;

            Object obj = IdManager.checkById(finalId);
            if (obj == null) {
                System.out.println("Элемента с таким id-номером нет в текущей коллекции!");
                return;
            } else manager.getCollection().remove(obj);

            SpaceMarine newCity = modeManager.buildObject();
            newCity.setId(finalId);

            manager.addElementToCollection(newCity);

            System.out.println("Обновлен объект с id: " + finalId);
        }
    }
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null) {
            System.out.println("Команда update_id имеет аргумент типа данных int!");
            return false;
        } else if (inputArgument instanceof String) {
            try {
                Integer.parseInt((String) inputArgument);
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Команда update_id имеет аргумент типа данных int!");
                return false;
            }
        }
        return false;
    }
}
