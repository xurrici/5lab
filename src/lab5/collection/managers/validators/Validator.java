package lab5.collection.managers.validators;

public interface Validator<T> {
    boolean canBeNull = false;
    boolean validate(T value);
    String getDescr();
}
