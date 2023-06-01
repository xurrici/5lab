package lab5.command.list;

import java.util.LinkedList;
import lab5.collection.managers.SpaceMarineManager;
import lab5.collection.managers.CollectionManager;
import lab5.command.Command;
import lab5.collection.SpaceMarine.SpaceMarine;

public class SumOfHealth extends Command {

    public SumOfHealth() {
        super(false);
    }

    @Override
    public String getName() {
        return "sum_of_health";
    }

    @Override
    public String getDescr() {
        return "вывести сумму значений поля health для всех элементов коллекции";
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CollectionManager<LinkedList<SpaceMarine>, SpaceMarine> manager = SpaceMarineManager.getInstance();
            if (manager.getCollection() == null) {
                System.out.println("Команда недоступна");
                return;
            }

            Double sum = 0.0;

            for (SpaceMarine city: manager.getCollection()) {
                sum += city.getHealth();
            }
            System.out.println(sum);
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
