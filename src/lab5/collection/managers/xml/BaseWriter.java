package lab5.collection.managers.xml;

import java.util.LinkedHashMap;
public interface BaseWriter {
    void writeToFile(String path, LinkedHashMap<String[], String> values);
}
