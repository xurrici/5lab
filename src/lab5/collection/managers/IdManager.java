package lab5.collection.managers;

import java.util.LinkedList;
import lab5.collection.SpaceMarine.SpaceMarine;

public class IdManager {
    public static SpaceMarine checkById(Long id) {
        CollectionManager<LinkedList<SpaceMarine>, SpaceMarine> collectionHandler = SpaceMarineManager.getInstance();
        for (SpaceMarine obj : collectionHandler.getCollection()) {
            if (obj.getId() == id)
                return obj;
        }
        return null;
    }
    public static Long generateId() {
        CollectionManager<LinkedList<SpaceMarine>, SpaceMarine> collectionHandler = SpaceMarineManager.getInstance();
        if(collectionHandler.getCollection().isEmpty()) return 1l;
        return collectionHandler.getCollection().stream().mapToLong(SpaceMarine::getId).max().orElse(0l) + 1l;
    }
    public static boolean isNotNumeric(String str) {
        return !str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
    public static Long validateUserInput(String input) {
        if (IdManager.isNotNumeric(input)) {
            System.out.println("Введенный аргумент: \"" + input + "\" не является числом! Попробуйте снова.");
            return null;
        } else if (input.contains(".")) {
            System.out.println("Данное поле не принимает дробные значения. Попробуйте снова.");
            return null;
        }
        Long id = null;
        try {
            id = Long.valueOf(input);
        } catch (NumberFormatException e) {
            System.out.println("Полученый аргумент: \"" + input + "\" не соответствует формату (слишком большое). Попробуйте снова.");
        }
        return id;
    }
}
