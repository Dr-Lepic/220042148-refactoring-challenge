package edu.iutcs.cr;

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
            int selectedOperation = showAndSelectOrderOperation(scanner);

            boolean shouldReturnToMainMenu = handleOrderOperation(selectedOperation, cart);
            if (shouldReturnToMainMenu) {
                return;
            }
        }
    }

    private static int showAndSelectOrderOperation(Scanner scanner) {
        int selectedOperation;

        System.out.println("Please enter the type of operation: [1-5]");
        System.out.println("1. Add new vehicle to cart");
        System.out.println("2. Remove vehicle from cart");
        System.out.println("3. View cart");
        System.out.println("4. Confirm purchase");
        System.out.println();
        System.out.println("5. Return to main menu");

        selectedOperation = scanner.nextInt();

        while (selectedOperation < 1 || selectedOperation > 5) {
            System.out.print("Please select a valid operation: ");
            selectedOperation = scanner.nextInt();
        }

        return selectedOperation;
    }

    private static boolean handleOrderOperation(int selectedOperation, ShoppingCart cart) {
        switch (selectedOperation) {
            case 1 -> cart.addItem();
            case 2 -> cart.removeItem();
            case 3 -> cart.viewCart();
            case 4 -> {
                createInvoice(cart);
                return true;
            }
            case 5 -> {
                return true;
            }
            default -> {
                return false;
            }
        }

        return false;
    }

    private static void createInvoice(ShoppingCart cart) {
        Scanner scanner = new Scanner(System.in);
        SystemDatabase database = SystemDatabase.getInstance();

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

        Invoice invoice = new Invoice(buyer, seller, cart);
        invoice.printInvoice();
        database.getInvoices().add(invoice);
    }
}
