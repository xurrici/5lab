package lab5.collection.managers.xml.editors;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class LocalDateTimeEditor implements PropertyEditor {

    private LocalDateTime result;

    public LocalDateTimeEditor()
    {
        result = Date.from(Instant.now()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    @Override
    public void setValue(Object value) {
        result = (LocalDateTime)value;
    }

    @Override
    public Object getValue() {
        return result;
    }

    @Override
    public boolean isPaintable() {
        return false;
    }

    @Override
    public void paintValue(Graphics gfx, Rectangle box) {

    }

    @Override
    public String getJavaInitializationString() {
        return null;
    }

    @Override
    public String getAsText() {
        return Date.from(result.atZone(ZoneId.systemDefault()).toInstant()).toInstant().toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            result = LocalDateTime.ofInstant(Date.from(Instant.parse(text)).toInstant(), ZoneId.systemDefault());
        }
        catch (DateTimeParseException e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String[] getTags() {
        return new String[0];
    }

    @Override
    public Component getCustomEditor() {
        return null;
    }

    @Override
    public boolean supportsCustomEditor() {
        return false;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {}

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }
}