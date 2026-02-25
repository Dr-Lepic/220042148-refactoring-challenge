package edu.iutcs.cr;

import edu.iutcs.cr.vehicles.*;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class VehicleFactory {

    public static Vehicle createFromChoice(int vehicleType) {
        return switch (vehicleType) {
            case 1 -> {
                System.out.println("\n\nCreate new bus");
                yield new Bus();
            }
            case 2 -> {
                System.out.println("\n\nCreate new car");
                yield new Car();
            }
            case 3 -> {
                System.out.println("\n\nCreate new hatchback");
                yield new Hatchback();
            }
            case 4 -> {
                System.out.println("\n\nCreate new sedan");
                yield new Sedan();
            }
            default -> {
                System.out.println("\n\nCreate new SUV");
                yield new SUV();
            }
        };
    }
}
