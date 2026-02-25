package edu.iutcs.cr.system;

import edu.iutcs.cr.Invoice;
import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.vehicles.Vehicle;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class DataStore {

    private <T> void saveSet(Set<T> data, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <T> Set<T> loadSet(String fileName) {
        Set<T> data = new HashSet<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            data = (Set<T>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            saveSet(data, fileName);
        }

        return data;
    }

    public void saveInvoices(Set<Invoice> invoices) {
        saveSet(invoices, "invoices.txt");
    }

    public Set<Invoice> loadInvoices() {
        return loadSet("invoices.txt");
    }

    public void saveBuyers(Set<Buyer> buyers) {
        saveSet(buyers, "buyers.txt");
    }

    public Set<Buyer> loadBuyers() {
        return loadSet("buyers.txt");
    }

    public void saveSellers(Set<Seller> sellers) {
        saveSet(sellers, "sellers.txt");
    }

    public Set<Seller> loadSellers() {
        return loadSet("sellers.txt");
    }

    public void saveVehicles(Set<Vehicle> vehicles) {
        saveSet(vehicles, "cars.txt");
    }

    public Set<Vehicle> loadVehicles() {
        return loadSet("cars.txt");
    }
}
