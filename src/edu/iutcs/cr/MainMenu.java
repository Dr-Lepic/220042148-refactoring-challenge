package edu.iutcs.cr;

import edu.iutcs.cr.menu.MainOperation;

import java.util.Scanner;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class MainMenu {

    private void showMenu() {
        System.out.println("Please enter the type of vehicle [1-9]: ");
        System.out.println("1. Add new seller");
        System.out.println("2. Add new customer");
        System.out.println("3. Add car");
        System.out.println("4. View inventory");
        System.out.println("5. View seller list");
        System.out.println("6. View buyer list");

        System.out.println();
        System.out.println("7. Add new order");
        System.out.println("8. View all invoices");

        System.out.println();
        System.out.println("9. Save System and Exit");
    }

    public MainOperation showAndSelectOperation() {
        Scanner scanner = new Scanner(System.in);
        showMenu();
        MainOperation selectedOperation = null;

        while(selectedOperation == null) {
            System.out.print("Enter your choice: ");
            selectedOperation = MainOperation.fromChoice(scanner.nextInt());

            if(selectedOperation == null) {
                System.out.print("Enter a valid operation: ");
            }
        }

        return selectedOperation;
    }
}
