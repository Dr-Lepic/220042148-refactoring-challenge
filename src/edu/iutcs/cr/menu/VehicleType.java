package edu.iutcs.cr.menu;

public enum VehicleType {
    BUS(1),
    CAR(2),
    HATCHBACK(3),
    SEDAN(4),
    SUV(5);

    private final int choice;

    VehicleType(int choice) {
        this.choice = choice;
    }

    public static VehicleType fromChoice(int choice) {
        for (VehicleType vehicleType : values()) {
            if (vehicleType.choice == choice) {
                return vehicleType;
            }
        }

        return null;
    }
}
