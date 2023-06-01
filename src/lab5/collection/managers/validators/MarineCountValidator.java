package lab5.collection.managers.validators;

public class MarineCountValidator implements Validator<Long> {
    public String getDescr() {
        return "0 < count < 1000";
    }
    @Override
    public boolean validate(Long value) {
        return 0 < value && value < 1000;
    }
}
