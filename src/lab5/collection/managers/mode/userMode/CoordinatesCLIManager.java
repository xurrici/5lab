package lab5.collection.managers.mode.userMode;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import lab5.collection.SpaceMarine.Coordinates;
import lab5.collection.managers.mode.ModeManager;
import lab5.collection.managers.validators.CoordinateXValidator;
import lab5.collection.managers.validators.InputValidator;
import lab5.collection.managers.validators.Validator;
import lab5.exceptions.BuildObjectException;

public class CoordinatesCLIManager implements ModeManager<Coordinates> {
    @Override
    public Coordinates buildObject() throws BuildObjectException {
        try {
            System.out.println("Создание координат...");
            Coordinates coordinates = new Coordinates();
            Scanner scanner = new Scanner(System.in);
            InputValidator inputValidator = new InputValidator();
            String nextLine;
            System.out.println();
            Double x;
            Validator<Double> coordXValidator = new CoordinateXValidator();
            while(true) {
                try {
                    System.out.print("Введите X: ");
                    nextLine = scanner.nextLine();
                    if (inputValidator.validate(nextLine)) {
                        x = Double.parseDouble(nextLine);
                        if (coordXValidator.validate(x)) {
                            coordinates.setX(x);
                            break;
                        } else {
                            System.out.println("Введено неверное значение. Попробуйте снова.");
                            System.out.println("Ограничения: " + coordXValidator.getDescr());
                        }
                    } else System.out.println("Ввод не должен быть пустым!");
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Неверный тип! Попробуйте снова.");
                }
            }
            try {
                System.out.print("Введите Y: ");
                nextLine = scanner.nextLine();
                Float y = Float.parseFloat(nextLine);
                coordinates.setY(y);
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Неверный тип! Попробуйте снова.");
            }
            return coordinates;
        } catch (NoSuchElementException | NumberFormatException e) {
            throw new BuildObjectException("Во время конструирования объекта произошла ошибка: " + e.getMessage());
        }
    }
}
