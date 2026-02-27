package edu.iutcs.cr;

import edu.iutcs.cr.menu.OrderOperation;
import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.system.SystemDatabase;

import java.util.Scanner;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class OrderFlow {

    public static void createOrder() {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        while (true) {
            OrderOperation selectedOperation = showAndSelectOrderOperation(scanner);

            boolean shouldReturnToMainMenu = handleOrderOperation(selectedOperation, cart);
            if (shouldReturnToMainMenu) {
                return;
            }
        }
    }

    private static OrderOperation showAndSelectOrderOperation(Scanner scanner) {
        OrderOperation selectedOperation;

        System.out.println("Please enter the type of operation: [1-5]");
        System.out.println("1. Add new vehicle to cart");
        System.out.println("2. Remove vehicle from cart");
        System.out.println("3. View cart");
        System.out.println("4. Confirm purchase");
        System.out.println();
        System.out.println("5. Return to main menu");

        selectedOperation = OrderOperation.fromChoice(scanner.nextInt());

        while (selectedOperation == null) {
            System.out.print("Please select a valid operation: ");
            selectedOperation = OrderOperation.fromChoice(scanner.nextInt());
        }

        return selectedOperation;
    }

    private static boolean handleOrderOperation(OrderOperation selectedOperation, ShoppingCart cart) {
        switch (selectedOperation) {
            case ADD_TO_CART -> cart.addItem();
            case REMOVE_FROM_CART -> cart.removeItem();
            case VIEW_CART -> cart.viewCart();
            case CONFIRM_PURCHASE -> {
                createInvoice(cart);
                return true;
            }
            case RETURN_TO_MAIN_MENU -> {
                return true;
            }
        }

        return false;
    }

    private static void createInvoice(ShoppingCart cart) {
        Scanner scanner = new Scanner(System.in);
        SystemDatabase database = SystemDatabase.getInstance();

        if (cart.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        Buyer buyer = null;
        Seller seller = null;

        do {
            System.out.print("Enter buyer id: ");
            String buyerId = scanner.nextLine();
            buyer = database.findBuyerById(buyerId);

            if (buyer == null) {
                System.out.println("Buyer not found. Try again!");
            }
        } while (buyer == null);

        do {
            System.out.print("Enter seller id: ");
            String sellerId = scanner.nextLine();
            seller = database.findSellerById(sellerId);

            if (seller == null) {
                System.out.println("Seller not found. Try again!");
            }
        } while (seller == null);

        System.out.print("Is payment done (true/false): ");
        boolean paymentDone = scanner.nextBoolean();

        Invoice invoice = cart.checkout(buyer, seller, paymentDone);
        invoice.printInvoice();
        database.getInvoices().add(invoice);
    }
}
