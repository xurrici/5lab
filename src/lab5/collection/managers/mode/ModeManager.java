package lab5.collection.managers.mode;

import lab5.exceptions.BuildObjectException;

public interface ModeManager<T> {
    T buildObject() throws BuildObjectException;
}
