package lab5.collection.managers.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;
import java.util.logging.Logger;

public class XMLWriter implements BaseWriter {

    private static final Logger myLogger = Logger.getLogger("lab5");

    @Override
    public void writeToFile(String path, LinkedHashMap<String[], String> values) {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(path))) {
            while (values.values().remove(null));
            writer.write("<?xml version=\"1.0\"?>");
            writer.write(System.lineSeparator());
            writer.write("<routes>");
            writer.write(System.lineSeparator());
            values.forEach((address, value) -> {
                try {
                    writeElement(writer, address, getNextAddress(values, address), value);
                } catch (IOException ex) {}
            });
            writer.write("</routes>");
            writer.write(System.lineSeparator());
        } catch (Exception e) {
            File xml = new File(path);
            try {
                boolean isCreated = xml.createNewFile();
                if (isCreated) writeToFile(path, values);
                else throw new IOException("Не удалось создать файл.");
            } catch (IOException ex) {
                ex.initCause(e);
                System.out.println("Создание файла вызвало непредвиденную ошибку.");
            }
        }
    }

    int lastKnownI = 0;
    private void writeElement(Writer writer, String[] address, String[] nextAddress, String value) throws IOException
    {
        if (value == null) return;

        for (int i = lastKnownI; i < address.length; i++)
        {
            for (int j = 0; j < i + 1; j++)
            {
                writer.write("\t");
            }
            myLogger.fine("Открываю: " + address[i] + "Адреса // далее: " + Arrays.toString(nextAddress) + ", текущее: " + Arrays.toString(address));
            writer.write("<" + address[i] + ">");
            writer.write(System.lineSeparator());
        }
        for (int j = 0; j < address.length + 1; j++)
        {
            writer.write("\t");
        }
        writer.write(value);
        writer.write(System.lineSeparator());
        for (lastKnownI = address.length; lastKnownI > 0; lastKnownI--) {
            myLogger.fine("Адреса // далее: " + Arrays.toString(nextAddress) + ", текущее: " + Arrays.toString(address) + " / индекс: " + lastKnownI);
            if (nextAddress.length < lastKnownI || !Objects.equals(nextAddress[lastKnownI - 1], address[lastKnownI - 1])) {
                for (int j = 0; j < lastKnownI; j++) {
                    writer.write("\t");
                }
                writer.write("</" + address[lastKnownI - 1] + ">");
                writer.write(System.lineSeparator());
                myLogger.fine("Закрываю: " + address[lastKnownI - 1]);
            }
            else break;
        }
    }

    private String[] getNextAddress(LinkedHashMap<String[], String> map, String[] key) {
        List<String[]> keys = new ArrayList<>(map.keySet());
        int index = keys.indexOf(key);

        if (index < 0 || index >= keys.size() - 1)
            return new String[0];

        String[] k = keys.get(index + 1);
        return Objects.requireNonNullElseGet(k, () -> new String[0]);
    }
}
