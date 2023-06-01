package lab5.collection.managers.mode.nonUserMode;

import lab5.collection.managers.IdManager;
import lab5.collection.managers.mode.ModeManager;
import lab5.collection.managers.validators.Validator;
import lab5.exceptions.BuildObjectException;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import lab5.collection.SpaceMarine.Chapter;
import lab5.collection.SpaceMarine.Coordinates;
import lab5.collection.SpaceMarine.SpaceMarine;
import lab5.collection.SpaceMarine.Weapon;
import lab5.collection.managers.validators.SpaceMarineValidator;

public class SpaceMarineNonUserManager implements ModeManager<SpaceMarine> {
    Scanner scanner;
    public SpaceMarineNonUserManager(Scanner scanner) {
        this.scanner = scanner;
    }
    @Override
    public SpaceMarine buildObject() throws BuildObjectException {
        System.out.println("Создание объекта...");
        SpaceMarine result = new SpaceMarine();
        int valuesToRead = 10;
        ArrayList<String> values = new ArrayList<>(valuesToRead);
        for (int i = 0; i < valuesToRead && scanner.hasNextLine(); i++) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty())
                values.add(line);
            else
                values.add(null);
        }

        try {
            result.setId(IdManager.generateId());

            result.setName(values.get(0));
            System.out.println("Название: " + result.getName());
            System.out.println();

            System.out.println("Создание координат...");
            Coordinates coordinates = new Coordinates();
            coordinates.setX(Double.valueOf(values.get(1)));
            System.out.println("Координата X: " + coordinates.getX());
            coordinates.setY(Float.parseFloat(values.get(2)));
            System.out.println("Координата Y: " + coordinates.getY());
            result.setCoordinates(coordinates);
            System.out.println("Координаты: " + result.getCoordinates());
            System.out.println();

            result.setCreationDate(Date.from(Instant.now()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            //result.setCreationDate(new Date());
            System.out.println("Создан: " + result.getCreationDate());
            System.out.println();
            
            result.setHealth(Integer.valueOf(values.get(3)));
            System.out.println("Здоровье: " + result.getName());
            System.out.println();
            
            result.setHeartCount(Integer.valueOf(values.get(4)));
            System.out.println("Сердец: " + result.getName());
            System.out.println();
            
            result.setAchievements(values.get(5));
            System.out.println("Достижения: " + result.getName());
            System.out.println();
            
            int i = Weapon.values().length;
            int userAnswer = Integer.parseInt(values.get(6));
            if (userAnswer >= 1 && userAnswer <= i)
                result.setWeaponType(Weapon.values()[userAnswer - 1]);
            else {
                System.out.println("Введен неверный объект.");
                throw new BuildObjectException("Созданный элемент нарушает ограничения и не может быть добавлен в коллекцию!");
            }
            System.out.println("Оружие: " + Weapon.values()[userAnswer - 1]);
            
            Chapter chapter = new Chapter();
            chapter.setName(values.get(7));
            System.out.println("Название главы: " + coordinates.getX());
            chapter.setMarinesCount(Long.parseLong(values.get(7)));
            System.out.println("Пехотинцев: " + coordinates.getY());
            result.setChapter(chapter);
            System.out.println("Глава: " + result.getChapter());
            
            Validator<SpaceMarine> validator = new SpaceMarineValidator();
            if (!validator.validate(result)) {
                System.out.println("Введен неверный объект.");
                throw new BuildObjectException("Созданный элемент нарушает ограничения и не может быть добавлен в коллекцию!");
            }
            System.out.println("Валидация прошла успешно.");

            return result;

        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
            throw new BuildObjectException("Предоставленные данные для построения объекта неверны. Воспользуйтесь ручным вводом или исправьте команду в скрипте.");
        }
    }
}
