package lab5.collection.managers.mode.userMode;

import lab5.collection.managers.validators.InputValidator;
import lab5.exceptions.BuildObjectException;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class EnumRequester<T extends Enum<T>> {
    public T requestEnum(T[] values, String enumName, Scanner scanner, InputValidator inputValidator) throws BuildObjectException {
        try {
            System.out.println("Выберите " + enumName + " тип:");

            int i = 0;
            for (T value : values) {
                System.out.println("\t" + ++i + " - " + value.toString());
            }

            String nextLine;
            Integer userAnswer;
            while (true) {
                System.out.print("Введите число от 1 до " + values.length + ": ");
                nextLine = scanner.nextLine();

                if (inputValidator.validate(nextLine)) {
                    userAnswer = Integer.valueOf(nextLine);
                    if (userAnswer >= 1 && userAnswer <= i)
                        return values[userAnswer - 1];
                    else System.out.println("Введите число от 1 до " + values.length + "!");
                } else System.out.println("Ввод не должен быть пустым!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Требуется ввести целое число от 1 до " + values.length + "!");
            return null;
        } catch (NoSuchElementException e) {
            throw new BuildObjectException("Во время конструирования объекта произошла ошибка: " + e.getMessage());
        }
    }
}