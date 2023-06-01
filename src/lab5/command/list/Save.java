package lab5.command.list;

import lab5.collection.managers.SpaceMarineManager;
import lab5.command.Command;

public class Save extends Command {

    public Save() {
        super(false);
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescr() {
        return "Saves collection to file.";
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            SpaceMarineManager collectionManager = new SpaceMarineManager();
            collectionManager.writeCollection();
            System.out.println("Коллекция сохранена");
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
