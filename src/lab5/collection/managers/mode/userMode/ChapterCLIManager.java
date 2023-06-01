package lab5.collection.managers.mode.userMode;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import lab5.collection.managers.mode.ModeManager;
import lab5.collection.managers.validators.InputValidator;
import lab5.exceptions.BuildObjectException;

import java.util.Scanner;
import lab5.collection.SpaceMarine.Chapter;
import lab5.collection.managers.validators.MarineCountValidator;
import lab5.collection.managers.validators.Validator;

public class ChapterCLIManager implements ModeManager<Chapter> {
    @Override
    public Chapter buildObject() throws BuildObjectException {
        try {
            System.out.println("Создание главы...");
            Chapter chapter = new Chapter();
            Scanner scanner = new Scanner(System.in);
            InputValidator inputValidator = new InputValidator();
            String nextLine;
            System.out.println();
            
            while(true) {
                try {
                    System.out.print("Введите название: ");
                    nextLine = scanner.nextLine();
                    if(nextLine != null && nextLine.length() > 0) {
                        chapter.setName(nextLine);
                        break;
                    } else {
                        System.out.println("Введено неверное значение. Попробуйте снова.");
                        System.out.println("Ограничения: length > 0");
                    }
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Неверный тип! Попробуйте снова.");
                }
            }
            Long marinesCount;
            Validator<Long> validator = new MarineCountValidator();
            while(true) {
                try {
                    System.out.print("Введите количество пехотинцев: ");
                    nextLine = scanner.nextLine();
                    if (inputValidator.validate(nextLine)) {
                        marinesCount = Long.parseLong(nextLine);
                        if (validator.validate(marinesCount)) {
                            chapter.setMarinesCount(marinesCount);
                            break;
                        } else {
                            System.out.println("Введено неверное значение. Попробуйте снова.");
                            System.out.println("Ограничения: " + validator.getDescr());
                        }
                    } else System.out.println("Ввод не должен быть пустым!");
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Неверный тип! Попробуйте снова.");
                }
            }

            return chapter;
        } catch (NoSuchElementException | NumberFormatException e) {
            throw new BuildObjectException("Во время конструирования объекта произошла ошибка: " + e.getMessage());
        }
    }
}
