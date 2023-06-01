package lab5.collection.managers.validators;

public class CoordinateXValidator implements Validator<Double> {
    public String getDescr() {
        return "x > -389";
    }
    @Override
    public boolean validate(Double value) {
        return value > -389;
    }
}
