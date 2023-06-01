package lab5.command.list;

import java.util.LinkedList;
import lab5.collection.SpaceMarine.SpaceMarine;
import lab5.collection.managers.SpaceMarineManager;
import lab5.collection.managers.CollectionManager;
import lab5.collection.managers.mode.ModeManager;
import lab5.command.Command;
import lab5.exceptions.BuildObjectException;

public class Add extends Command {
    ModeManager<SpaceMarine> handler;
    public Add() {
        super(false);
    }
    public Add(ModeManager<SpaceMarine> handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescr() {
        return "добавить новый элемент в коллекцию";
    }

    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            CollectionManager<LinkedList<SpaceMarine>, SpaceMarine> manager = SpaceMarineManager.getInstance();
            manager.addElementToCollection(handler.buildObject());
            System.out.println("Объект добавлен!");
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
