package edu.iutcs.cr;

import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.system.SystemDatabase;
import edu.iutcs.cr.vehicles.*;

import java.util.Scanner;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class SystemFlowRunner {

    public static void run() {
        System.out.println("Welcome to Car Hut");

        System.out.println("Loading existing system");
        SystemDatabase database = SystemDatabase.getInstance();
        System.out.println("Existing system loaded");

        MainMenu mainMenu = new MainMenu();

        while (true) {
            System.out.println("\n\n\n");

            int selectedOperation = mainMenu.showAndSelectOperation();

            boolean shouldExit = handleMainOperation(selectedOperation, database);
            if (shouldExit) {
                return;
            }
        }
    }

    private static boolean handleMainOperation(int selectedOperation, SystemDatabase database) {
        switch (selectedOperation) {
            case 1 -> handleAddSeller(database);
            case 2 -> handleAddBuyer(database);
            case 3 -> handleAddVehicle();
            case 4 -> handleViewInventory(database);
            case 5 -> handleViewSellers(database);
            case 6 -> handleViewBuyers(database);
            case 7 -> handleCreateOrder();
            case 8 -> handleViewInvoices(database);
            case 9 -> {
                database.saveSystem();
                return true;
            }
            default -> {
                return false;
            }
        }

        return false;
    }

    private static void handleAddSeller(SystemDatabase database) {
        System.out.println("\n\n\nAdd new seller");
        database.getSellers().add(new Seller());
        promptToViewMainMenu();
    }

    private static void handleAddBuyer(SystemDatabase database) {
        System.out.println("\n\n\nAdd new customer");
        database.getBuyers().add(new Buyer());
        promptToViewMainMenu();
    }

    private static void handleAddVehicle() {
        System.out.println("\n\n\nAdd new vehicle");
        addCar();
        promptToViewMainMenu();
    }

    private static void handleViewInventory(SystemDatabase database) {
        System.out.println("\n\n\nInventory list");
        database.showInventory();
        promptToViewMainMenu();
    }

    private static void handleViewSellers(SystemDatabase database) {
        System.out.println("\n\n\nSeller's list");
        database.showSellerList();
        promptToViewMainMenu();
    }

    private static void handleViewBuyers(SystemDatabase database) {
        System.out.println("\n\n\nCustomer's list");
        database.showBuyerList();
        promptToViewMainMenu();
    }

    private static void handleCreateOrder() {
        System.out.println("\n\n\nCreate order");
        createOrder();
    }

    private static void handleViewInvoices(SystemDatabase database) {
        System.out.println("\n\n\nInvoice list");
        database.showInvoices();
        promptToViewMainMenu();
    }

    private static void promptToViewMainMenu() {
        System.out.print("\n\nEnter 0 to view main menu: ");

        Scanner scanner = new Scanner(System.in);
        int val = -1;

        do {
            val = scanner.nextInt();
        } while (val != 0);
    }

    private static void addCar() {
        Scanner scanner = new Scanner(System.in);
        SystemDatabase database = SystemDatabase.getInstance();

        System.out.println("Please enter the type of vehicle [1-6]: ");
        System.out.println("1. Bus");
        System.out.println("2. Car");
        System.out.println("3. Hatchback");
        System.out.println("4. Sedan");
        System.out.println("5. SUV");

        int vehicleType = -1;
        while(vehicleType<1 || vehicleType>5) {
            System.out.print("Enter your choice: ");
            vehicleType = scanner.nextInt();

            if(vehicleType<1 || vehicleType>5) {
                System.out.println("Enter a valid vehicle type!");
            }
        }

        Vehicle newItem = switch (vehicleType) {
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

        database.getVehicles().add(newItem);
    }

    private static void createOrder() {
        Scanner scanner = new Scanner(System.in);
        SystemDatabase systemDatabase = SystemDatabase.getInstance();
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
