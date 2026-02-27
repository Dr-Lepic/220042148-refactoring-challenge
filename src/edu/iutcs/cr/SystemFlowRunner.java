package edu.iutcs.cr;

import edu.iutcs.cr.menu.MainOperation;
import edu.iutcs.cr.menu.VehicleType;
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

            MainOperation selectedOperation = mainMenu.showAndSelectOperation();

            boolean shouldExit = handleMainOperation(selectedOperation, database);
            if (shouldExit) {
                return;
            }
        }
    }

    private static boolean handleMainOperation(MainOperation selectedOperation, SystemDatabase database) {
        switch (selectedOperation) {
            case ADD_SELLER -> handleAddSeller(database);
            case ADD_BUYER -> handleAddBuyer(database);
            case ADD_VEHICLE -> handleAddVehicle();
            case VIEW_INVENTORY -> handleViewInventory(database);
            case VIEW_SELLERS -> handleViewSellers(database);
            case VIEW_BUYERS -> handleViewBuyers(database);
            case ADD_ORDER -> handleCreateOrder();
            case VIEW_INVOICES -> handleViewInvoices(database);
            case SAVE_AND_EXIT -> {
                database.saveSystem();
                return true;
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

        VehicleType vehicleType = null;
        while(vehicleType == null) {
            System.out.print("Enter your choice: ");
            vehicleType = VehicleType.fromChoice(scanner.nextInt());

            if(vehicleType == null) {
                System.out.println("Enter a valid vehicle type!");
            }
        }

        Vehicle newItem = VehicleFactory.createFromChoice(vehicleType);

        database.getVehicles().add(newItem);
    }
}
