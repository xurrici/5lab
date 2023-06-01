package lab5.collection.managers.validators;

public class HeartCountValidator implements Validator<Integer> {
    public String getDescr() {
        return "0 < count <= 3";
    }
    @Override
    public boolean validate(Integer value) {
        return 0 < value && value <= 3;
    }
}
