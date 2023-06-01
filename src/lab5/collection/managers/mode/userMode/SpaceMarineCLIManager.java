package lab5.collection.managers.mode.userMode;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import lab5.collection.SpaceMarine.SpaceMarine;
import lab5.collection.managers.IdManager;
import lab5.collection.managers.mode.ModeManager;
import lab5.collection.managers.validators.HealthValidator;
import lab5.collection.managers.validators.HeartCountValidator;
import lab5.collection.managers.validators.InputValidator;
import lab5.collection.managers.validators.NameValidator;
import lab5.collection.managers.validators.Validator;
import lab5.exceptions.BuildObjectException;

public class SpaceMarineCLIManager implements ModeManager<SpaceMarine> {
    @Override
    public SpaceMarine buildObject() throws BuildObjectException {
        try {
            System.out.println("Создание нового SpaceMarine...");
            SpaceMarine spaceMarine = new SpaceMarine();
            Scanner scanner = new Scanner(System.in);
            InputValidator inputValidator = new InputValidator();
            String nextLine;
            System.out.println();
            spaceMarine.setId(IdManager.generateId());
            String name;
            Validator<String> nameValidator = new NameValidator();
            inputValidator.canBeNull(false);
            while (true) {
                System.out.print("Введите название: ");
                nextLine = scanner.nextLine();

                if (inputValidator.validate(nextLine)) {
                    name = nextLine;
                    if (nameValidator.validate(name)) {
                        spaceMarine.setName(name);
                        break;
                    } else {
                        System.out.println("Значение нарушает ограничения! Попробуйте снова.");
                        System.out.println("Ограничения: " + nameValidator.getDescr());
                    }
                } else System.out.println("Ввод не должен быть пустым!");
            }
            CoordinatesCLIManager coordinatesCLIHandler = new CoordinatesCLIManager();
            spaceMarine.setCoordinates(coordinatesCLIHandler.buildObject());
            spaceMarine.setCreationDate(Date.from(Instant.now()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            Integer health;
            Validator<Integer> healthValidator = new HealthValidator();
            while (true) {
                try {
                    System.out.print("Введите здоровье: ");
                    nextLine = scanner.nextLine();

                    if (inputValidator.validate(nextLine)) {
                        health = Integer.valueOf(nextLine);
                        if (healthValidator.validate(health)) {
                            spaceMarine.setHealth(health);
                            break;
                        } else {
                            System.out.println("Введено неверное значение. Попробуйте снова.");
                            System.out.println("Ограничения: " + healthValidator.getDescr());
                        }
                    } else System.out.println("Ввод не должен быть пустым!");
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Неверный тип! Попробуйте снова.");
                }
            }
            Integer heartCount;
            Validator<Integer> heartCountValidator = new HeartCountValidator();
            while (true) {
                try {
                    System.out.print("Введите количество сердец: ");
                    nextLine = scanner.nextLine();

                    if (inputValidator.validate(nextLine)) {
                        heartCount = Integer.valueOf(nextLine);
                        if (heartCountValidator.validate(heartCount)) {
                            spaceMarine.setHeartCount(heartCount);
                            break;
                        } else {
                            System.out.println("Введено неверное значение. Попробуйте снова.");
                            System.out.println("Ограничения: " + heartCountValidator.getDescr());
                        }
                    } else System.out.println("Ввод не должен быть пустым!");
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Неверный тип! Попробуйте снова.");
                }
            }
            String achievments;
            Validator<String> achievmentsValidator = new NameValidator();
            inputValidator.canBeNull(false);
            while (true) {
                System.out.print("Введите достижения: ");
                nextLine = scanner.nextLine();

                if (inputValidator.validate(nextLine)) {
                    achievments = nextLine;
                    if (achievmentsValidator.validate(achievments)) {
                        spaceMarine.setAchievements(achievments);
                        break;
                    } else {
                        System.out.println("Значение нарушает ограничения! Попробуйте снова.");
                        System.out.println("Ограничения: " + achievmentsValidator.getDescr());
                    }
                } else System.out.println("Ввод не должен быть пустым!");
            }
            WeaponCLIManager weaponCLIManager = new WeaponCLIManager();
            spaceMarine.setWeaponType(weaponCLIManager.buildObject());
            ChapterCLIManager chapterCLIManager = new ChapterCLIManager();
            spaceMarine.setChapter(chapterCLIManager.buildObject());
            return spaceMarine;

        } catch (NoSuchElementException e) {
            throw new BuildObjectException("Во время конструирования объекта произошла ошибка: " + e.getMessage());
        }
    }
}
