package lab5.collection.managers.validators;

public class HealthValidator implements Validator<Integer> {
    public String getDescr() {
        return "health > 0";
    }
    @Override
    public boolean validate(Integer value) {
        return 0 < value;
    }
}
