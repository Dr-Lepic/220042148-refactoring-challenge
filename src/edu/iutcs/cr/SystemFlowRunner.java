package edu.iutcs.cr;

import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.system.SystemDatabase;
import edu.iutcs.cr.vehicles.Vehicle;

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
        OrderFlow.createOrder();
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

        Vehicle newItem = VehicleFactory.createFromChoice(vehicleType);

        database.getVehicles().add(newItem);
    }
}
