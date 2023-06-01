package lab5.collection.managers.mode.userMode;

import lab5.collection.managers.mode.ModeManager;
import lab5.collection.managers.validators.InputValidator;
import lab5.exceptions.BuildObjectException;

import java.util.Scanner;
import lab5.collection.SpaceMarine.Weapon;

public class WeaponCLIManager implements ModeManager<Weapon> {
    @Override
    public Weapon buildObject() throws BuildObjectException {
        Scanner scanner = new Scanner(System.in);
        InputValidator inputValidator = new InputValidator();
        System.out.println();

        inputValidator.canBeNull(false);
        EnumRequester<Weapon> enumRequester = new EnumRequester<>();
        Weapon weapon = enumRequester.requestEnum(Weapon.values(), Weapon.class.getSimpleName(), scanner, inputValidator);
        return weapon;
    }
}
