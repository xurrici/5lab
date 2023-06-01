package lab5.command.list;

import java.util.LinkedList;
import lab5.collection.managers.SpaceMarineManager;
import lab5.collection.managers.CollectionManager;
import lab5.command.Command;
import lab5.collection.SpaceMarine.SpaceMarine;
import lab5.collection.managers.IdManager;

public class FilterLessThanWeaponType extends Command {

    public FilterLessThanWeaponType() {
        super(true);
    }

    @Override
    public String getName() {
        return "filter_less_than_weapon_type";
    }

    @Override
    public String getDescr() {
        return "вывести элементы, значение поля weaponType которых меньше заданного";
    }
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            Long finalId = IdManager.validateUserInput((String) this.getArgument());
            if (finalId == null) return;
            
            CollectionManager<LinkedList<SpaceMarine>, SpaceMarine> manager = SpaceMarineManager.getInstance();
            
            if (manager.getCollection() == null)
                System.out.println("Коллекция пуста");
            else {
                manager.getCollection().stream().filter(obj -> obj.getWeaponType().ordinal() < Integer.parseInt(finalId.toString())).forEach(System.out::println);
            }
        }
    }
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null) {
            System.out.println("Требуется 1 аргумент числового типа");
            return false;
        }
        return true;
    }

}
