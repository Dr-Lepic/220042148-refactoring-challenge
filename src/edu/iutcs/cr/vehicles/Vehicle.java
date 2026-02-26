package edu.iutcs.cr.vehicles;

import edu.iutcs.cr.value.RegistrationNumber;
import edu.iutcs.cr.value.VehicleYear;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Vehicle implements Serializable {

    private String make;
    private String model;
    private VehicleYear year;
    private double price;
    private boolean available;
    private RegistrationNumber registrationNumber;

    public Vehicle() {
        setRegistrationNumber();
        setMake();
        setModel();
        setYear();
        setPrice();
        this.available = true;
    }

    public Vehicle(String registrationNumber) {
        this.registrationNumber = new RegistrationNumber(registrationNumber);
    }

    public String getRegistrationNumber() {
        return this.registrationNumber.getValue();
    }

    public void setRegistrationNumber() {
        Scanner scanner = new Scanner(System.in);
        while (this.registrationNumber == null) {
            System.out.print("Enter registration number: ");
            String input = scanner.nextLine();

            try {
                this.registrationNumber = new RegistrationNumber(input);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public String getMake() {
        return make;
    }

    public void setMake() {
        Scanner scanner = new Scanner(System.in);

        while (this.make == null || this.make.isBlank()) {
            System.out.print("Enter make: ");
            this.make = scanner.nextLine();

            if (make == null || make.isBlank()) {
                System.out.println("Make is mandatory!");
            }
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel() {
        Scanner scanner = new Scanner(System.in);

        while (this.model == null || this.model.isBlank()) {
            System.out.print("Enter model: ");
            this.model = scanner.nextLine();

            if (model == null || model.isBlank()) {
                System.out.println("Model is mandatory!");
            }
        }
    }

    public String getYear() {
        return year == null ? null : year.toString();
    }

    public void setYear() {
        Scanner scanner = new Scanner(System.in);

        while (this.year == null) {
            System.out.print("Enter year: ");
            String input = scanner.nextLine();

            try {
                this.year = new VehicleYear(input);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter price: ");
        this.price = scanner.nextDouble();
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean canBePurchased() {
        return available;
    }

    public void markAsSold() {
        this.available = false;
    }

    public void setUnavailable() {
        markAsSold();
    }

    @Override
    public String toString() {
        return "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", registrationNumber='" + registrationNumber + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return Objects.equals(registrationNumber, vehicle.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }
}
