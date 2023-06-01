package lab5.collection.managers;

import java.io.*;
import java.util.LinkedList;
import lab5.collection.SpaceMarine.SpaceMarine;
import lab5.collection.managers.xml.Loader;
import lab5.collection.managers.xml.Saver;

public class XMLManager {
    public LinkedList<SpaceMarine> read() {
        Loader<LinkedList<SpaceMarine>, SpaceMarine> loader = new Loader<>(LinkedList.class, SpaceMarine.class);
        return loader.loadFromXMLbyEnvKey("lab5");
    }
    public void write(LinkedList<SpaceMarine> entries) throws IOException {
        Saver<LinkedList<SpaceMarine>, SpaceMarine> saver = new Saver<>(SpaceMarine.class);
        saver.saveCollection(entries, "lab5");
    }
}

