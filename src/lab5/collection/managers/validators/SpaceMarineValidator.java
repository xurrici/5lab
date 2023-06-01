package lab5.collection.managers.validators;

import java.util.Optional;
import lab5.collection.SpaceMarine.Coordinates;
import lab5.collection.SpaceMarine.SpaceMarine;

public class SpaceMarineValidator implements Validator<SpaceMarine>{
    @Override
    public boolean validate(SpaceMarine spaceMarine) {
        return new NameValidator().validate(spaceMarine.getName())
                && new CoordinateXValidator().validate(Optional.of(spaceMarine).map(SpaceMarine::getCoordinates).map(Coordinates::getX).orElse(null))
                && new HealthValidator().validate(spaceMarine.getHealth())
                && new HeartCountValidator().validate(spaceMarine.getHeartCount())
                && new NameValidator().validate(spaceMarine.getAchievements());
    }
    @Override
    public String getDescr() {
        return "Проверяет SpaceMarine";
    }
}
