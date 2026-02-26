package edu.iutcs.cr;

import edu.iutcs.cr.menu.VehicleType;
import edu.iutcs.cr.vehicles.*;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class VehicleFactory {

    public static Vehicle createFromChoice(VehicleType vehicleType) {
        return switch (vehicleType) {
            case BUS -> {
                System.out.println("\n\nCreate new bus");
                yield new Bus();
            }
            case CAR -> {
                System.out.println("\n\nCreate new car");
                yield new Car();
            }
            case HATCHBACK -> {
                System.out.println("\n\nCreate new hatchback");
                yield new Hatchback();
            }
            case SEDAN -> {
                System.out.println("\n\nCreate new sedan");
                yield new Sedan();
            }
            case SUV -> {
                System.out.println("\n\nCreate new SUV");
                yield new SUV();
            }
        };
    }
}
