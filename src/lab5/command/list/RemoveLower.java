package lab5.command.list;

import lab5.collection.managers.SpaceMarineManager;
import lab5.collection.managers.CollectionManager;
import lab5.collection.managers.mode.ModeManager;
import lab5.command.Command;
import lab5.exceptions.BuildObjectException;
import java.util.Iterator;
import java.util.LinkedList;
import lab5.collection.SpaceMarine.SpaceMarine;

public class RemoveLower extends Command {
    ModeManager<SpaceMarine> handler;
    public RemoveLower() {
        super(false);
    }
    public RemoveLower(ModeManager<SpaceMarine> handler) {
        super(false);
        this.handler = handler;
    }
    @Override
    public String getName() {
        return "remove_lower";
    }
    @Override
    public String getDescr() {
        return "удалить из коллекции все элементы, меньшие, чем заданный";
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
            Iterator<SpaceMarine> iter = manager.getCollection().iterator();

            int i = 0;
            while(iter.hasNext()) {
                if (newCity.getHealth() > iter.next().getHealth()) {
                    iter.remove();
                    i ++;
                }
            }
            if (i == 1)
                System.out.println(i + " объект удален");
            else System.out.println(i + " объектов удалено");
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
