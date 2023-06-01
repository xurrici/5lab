package lab5.collection.managers;


import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import lab5.collection.SpaceMarine.SpaceMarine;

public class SpaceMarineManager implements CollectionManager<LinkedList<SpaceMarine>, SpaceMarine> {
    private static SpaceMarineManager singletonPattern;
    private static final Date initializationDate = new Date();
    private LinkedList<SpaceMarine> spaceMarinesList;
    public static SpaceMarineManager getInstance() {
        if (singletonPattern == null)
            singletonPattern = new SpaceMarineManager();
        return singletonPattern;
    }
    public void loadCollection() {
        LinkedList<SpaceMarine> data = new XMLManager().read();
        SpaceMarineManager.getInstance().setCollection(data);
    }
    public void writeCollection() {
        try {
            new XMLManager().write(SpaceMarineManager.getInstance().getCollection());
        } catch (IOException ex) {
            throw new IllegalArgumentException("Не удалось сохранить файл");
        }
    }
    @Override
    public void addElementToCollection(SpaceMarine value) {
        if (spaceMarinesList != null)
            spaceMarinesList.add(value);
        else {
            LinkedList<SpaceMarine> objects = new LinkedList<>();
            objects.add(value);
            SpaceMarineManager.getInstance().setCollection(objects);
        }
    }
    @Override
    public SpaceMarine getFirstOrNew() {
        if (spaceMarinesList.iterator().hasNext())
            return spaceMarinesList.iterator().next();
        else
            return new SpaceMarine();
    }
    @Override
    public void clearCollection() {
        spaceMarinesList.clear();
    }
    @Override
    public void setCollection(LinkedList<SpaceMarine> cityLinkedList) {
        this.spaceMarinesList = cityLinkedList;
    }
    @Override
    public LinkedList<SpaceMarine> getCollection() {
        return spaceMarinesList;
    }
    public static Date getInitializationDate() {
        return initializationDate;
    }
}
