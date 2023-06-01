package lab5.collection.managers.xml.editors;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import lab5.collection.SpaceMarine.Weapon;

public class WeaponEditor implements PropertyEditor {

    private Weapon result;

    public WeaponEditor()
    {
        result = Weapon.BOLT_RIFLE;
    }
    @Override
    public void setValue(Object value) {
        result = (Weapon)value;
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
        return result.toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            result = Weapon.valueOf(text);
        }
        catch (NumberFormatException e)
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