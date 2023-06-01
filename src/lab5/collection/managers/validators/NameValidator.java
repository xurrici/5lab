package lab5.collection.managers.validators;

public class NameValidator implements Validator<String> {
    @Override
    public String getDescr() {
        return "Проверяет правильность ввода";
    }
    @Override
    public boolean validate(String value) {
        return !value.isEmpty() && !value.isBlank();
    }
}