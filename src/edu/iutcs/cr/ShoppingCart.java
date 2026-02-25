package edu.iutcs.cr;

import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.system.SystemDatabase;
import edu.iutcs.cr.vehicles.Vehicle;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static java.util.Objects.isNull;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class ShoppingCart implements Serializable {

    private final Set<Vehicle> vehicles;
    private final SystemDatabase database;

    public ShoppingCart() {
        this.vehicles = new HashSet<>();
        database = SystemDatabase.getInstance();
    }

    public Set<Vehicle> getVehicles() {
        return this.vehicles;
    }

    public boolean isEmpty() {
        return vehicles.isEmpty();
    }

    public boolean addVehicle(Vehicle vehicle) {
        if (isNull(vehicle) || !vehicle.canBePurchased()) {
            return false;
        }

        return vehicles.add(vehicle);
    }

    public boolean removeVehicleByRegistrationNumber(String registrationNumber) {
        return vehicles.remove(new Vehicle(registrationNumber));
    }

    public double getTotalPrice() {
        double totalPrice = 0;

        for (Vehicle vehicle : vehicles) {
            totalPrice += vehicle.getPrice();
        }

        return totalPrice;
    }

    public Invoice checkout(Buyer buyer, Seller seller, boolean paymentDone) {
        Invoice invoice = new Invoice(buyer, seller, this);
        invoice.applyPaymentStatus(paymentDone);
        invoice.finalizeSale();
        return invoice;
    }

    public void addItem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter registration number of vehicle: ");
        String registrationNumber = scanner.next();

        Vehicle vehicle = database.findVehicleByRegistrationNumber(registrationNumber);

        if (!addVehicle(vehicle)) {
            System.out.println("Vehicle not available");
            return;
        }
    }

    public void removeItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the registration number of the vehicle: ");
        String registrationNumber = scanner.nextLine();
        removeVehicleByRegistrationNumber(registrationNumber);
    }

    public void viewCart() {
        System.out.println("\n\nShopping cart\n\n");

        if(isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }
    }
}
