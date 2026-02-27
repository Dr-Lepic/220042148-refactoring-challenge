package edu.iutcs.cr.menu;

public enum OrderOperation {
    ADD_TO_CART(1),
    REMOVE_FROM_CART(2),
    VIEW_CART(3),
    CONFIRM_PURCHASE(4),
    RETURN_TO_MAIN_MENU(5);

    private final int choice;

    OrderOperation(int choice) {
        this.choice = choice;
    }

    public static OrderOperation fromChoice(int choice) {
        for (OrderOperation operation : values()) {
            if (operation.choice == choice) {
                return operation;
            }
        }

        return null;
    }
}
