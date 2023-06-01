package lab5.collection.managers.validators;

public class InputValidator{
    boolean canBeNull = false;
    public String getDescr() {
        return "Проверяет правильность ввода";
    }
    public boolean validate(String value) {
        if (!canBeNull)
            return !value.isEmpty() && !value.isBlank();
        return true;
    }

    public void canBeNull(boolean canBeNull) {
        this.canBeNull = canBeNull;
    }
}
