package edu.iutcs.cr.menu;

public enum MainOperation {
    ADD_SELLER(1),
    ADD_BUYER(2),
    ADD_VEHICLE(3),
    VIEW_INVENTORY(4),
    VIEW_SELLERS(5),
    VIEW_BUYERS(6),
    ADD_ORDER(7),
    VIEW_INVOICES(8),
    SAVE_AND_EXIT(9);

    private final int choice;

    MainOperation(int choice) {
        this.choice = choice;
    }

    public static MainOperation fromChoice(int choice) {
        for (MainOperation operation : values()) {
            if (operation.choice == choice) {
                return operation;
            }
        }

        return null;
    }
}
