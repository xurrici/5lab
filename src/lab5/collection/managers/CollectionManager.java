package lab5.collection.managers;

import java.util.AbstractCollection;

public interface CollectionManager<T extends AbstractCollection<E>, E> {
    T getCollection();
    void setCollection(T value);
    E getFirstOrNew();
    void addElementToCollection(E value);
    void clearCollection();
}
