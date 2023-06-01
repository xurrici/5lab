package lab5.collection.managers.xml;

import java.io.IOException;
import java.util.LinkedHashMap;
public interface BaseReader {
    LinkedHashMap<String[], String> readFromFile(String path) throws IOException;
}
