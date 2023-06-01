package lab5.collection.managers.xml;

import lab5.collection.managers.xml.editors.DateEditor;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.CharacterCodingException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import lab5.collection.SpaceMarine.Weapon;
import lab5.collection.managers.xml.editors.LocalDateTimeEditor;
import lab5.collection.managers.xml.editors.WeaponEditor;
public class Loader<T extends Collection<E>, E> {
    private static final Logger myLogger = Logger.getLogger("lab5");
    private T resultCollection;
    private final Class<E> fullClassOfElement;
    public Loader(Class<T> tClass, Class<E> eClass)
    {
        setupConverter();
        fullClassOfElement = eClass;
        try {
            resultCollection = tClass.newInstance();
            buildingObject = buildElement();
        }
        catch (Exception e)
        {
            myLogger.log(Level.SEVERE, "В программе возникла проблема: " + e);
        }
    }
    private static void setupConverter() {
        PropertyEditorManager.registerEditor(Date.class, DateEditor.class);
        PropertyEditorManager.registerEditor(LocalDateTime.class, LocalDateTimeEditor.class);
        PropertyEditorManager.registerEditor(Weapon.class, WeaponEditor.class);
    }
    public static void setupConverter(Class<?> typeToEdit, Class<? extends PropertyEditor> editor)
    {
        PropertyEditorManager.registerEditor(typeToEdit, editor);
    }
    public T loadFromXMLbyEnvKey(String envKey) {
        String xmlPath = System.getenv(envKey);
        if (xmlPath == null) {
            myLogger.log(Level.SEVERE, "Отсутствует переменная окружения с путем к загрузочному файлу!");
            myLogger.log(Level.INFO, "Задайте переменную окружения с именем \"lab5\", поместив туда полный путь к XML файлу.");
            myLogger.log(Level.WARNING, "Выполнение программы не может быть продолжено.");
            System.exit(-1);
        }

        BaseReader reader = new XMLReader();

        resultCollection = loadFromFile(xmlPath, reader);

        return resultCollection;
    }
    public T loadFromFile(String path, BaseReader reader)
    {
        try {
            LinkedHashMap<String[], String> parsedValues = reader.readFromFile(path);

            if (!parsedValues.isEmpty())
                fillCollection(parsedValues);

        } catch (AccessDeniedException ex) {
            System.out.println("/ ! \\ Не удалось прочитать данные из файла в связи с отстуствием прав доступа.");
        } catch (CharacterCodingException ex) {
            System.out.println("/ ! \\ Не удалось прочитать данные из файла в связи с кодировкой данного файла.");
        } catch (IOException e) {
            System.out.println("/ ! \\ Не удалось прочитать файл.");
            myLogger.log(Level.SEVERE, "Во время работы с вводом-выводом произошла непредвиденная ошибка! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("/ ! \\ Файл XML неправильный и потому не был загружен");
        }
        return resultCollection;
    }

    private void fillCollection(LinkedHashMap<String[], String> parsedValues) {
        parsedValues.forEach(this::addFieldToElement);

        myLogger.log(Level.FINE, "Добавление...");
        addObjectToCollection(buildingObject);
    }

    private int latestKnownIndex = 0;
    private E buildingObject;

    private void addFieldToElement(String[] pathToField, String s) {
        var matcher = Pattern.compile("[0-9]+?").matcher(pathToField[1]);
        int currentIndex;
        if (matcher.find())
        {
            currentIndex = Integer.parseInt(matcher.group());
        }
        else {
            myLogger.log (Level.WARNING, "Поле имеет некорректный адрес и будет пропущено. " + Arrays.toString(pathToField));
            return;
        }
        if (latestKnownIndex != currentIndex)
        {
            latestKnownIndex = currentIndex;
            if (resultCollection.size() > currentIndex)
            {
                Iterator<E> iterator = resultCollection.iterator();
                for (int i = 0; i <= currentIndex; i++)
                {
                    buildingObject = iterator.next();
                }
            }
            else
            {
                myLogger.log(Level.FINE, "adding object...");
                addObjectToCollection(buildingObject);
                buildingObject = buildElement();
            }
        }
        for (int i = 2; i < pathToField.length; i++)
        {
            try {
                setField(fullClassOfElement, pathToField, 2, s, buildingObject);
            } catch (NoSuchFieldException | InstantiationException | IllegalAccessException e) {
                myLogger.log(Level.SEVERE, "Поле не было обнаружено: " + e);
            }
        }
    }
    private void addObjectToCollection(E buildingObject) {
        resultCollection.add(buildingObject);
    }
    private <U> void setField (Class<?> currentType, String[] fullPath, int currentIndex, String value, U object) throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        Field fieldToSet = currentType.getDeclaredField(fullPath[currentIndex++]);
        fieldToSet.setAccessible(true);
        Class<U> nextType = (Class<U>) fieldToSet.getType();
        if (currentIndex != fullPath.length)
        {
            U nextObject = (U) fieldToSet.get(object);
            if (nextObject == null)
            {
                nextObject = nextType.newInstance();
                fieldToSet.set(object, nextObject);
            }
            setField(nextType, fullPath, currentIndex, value, nextObject);
        }
        else
        {
            try
            {
                fieldToSet.set(object, convert(nextType, value));
            }
            catch (NullPointerException | NumberFormatException | DateTimeParseException e)
            {
                myLogger.log(Level.WARNING, "XML файл поврежден. Строка с данными " + value + " (Адрес: " + Arrays.toString(fullPath) + ") была пропущена.");
            }
        }
    }
    private Object convert(Class<?> targetType, String text) {
        PropertyEditor editor = PropertyEditorManager.findEditor(targetType);
        editor.setAsText(text);
        return editor.getValue();
    }

    private E buildElement() {

        E result = null;
        try
        {
            result = fullClassOfElement.newInstance();
        } catch (InstantiationException e) {
            myLogger.log(Level.SEVERE, "При инициализации элемента возникла проблема! " + e.getMessage());
        } catch (IllegalAccessException e) {
            myLogger.log(Level.SEVERE, "Ошибка доступа! " + e.getMessage());
        }
        return result;
    }
}
