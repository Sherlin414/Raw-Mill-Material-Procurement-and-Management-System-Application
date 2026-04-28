package com.example.rawmillmanagement.model;

import java.io.FileWriter;
import java.io.IOException;

public class Supplier {

    private static int totalSupplierCount = 0;

    private String supplierId;
    private String supplierName;
    private String materialCategory;
    private String contactPerson;
    private String phoneNumber;
    private String emailId;
    private String address;
    private String city;
    private String state;
    private String gstNumber;
    private double rating;

    public Supplier(String supplierId, String supplierName, String materialCategory,
                    String contactPerson, String phoneNumber, String emailId,
                    String address, String city, String state, String gstNumber,
                    double rating) {

        if (supplierId == null || supplierId.trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier ID cannot be empty.");
        }
        if (supplierName == null || supplierName.trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier name cannot be empty.");
        }
        if (materialCategory == null || materialCategory.trim().isEmpty()) {
            throw new IllegalArgumentException("Material category cannot be empty.");
        }
        if (contactPerson == null || contactPerson.trim().isEmpty()) {
            throw new IllegalArgumentException("Contact person cannot be empty.");
        }
        if (!phoneNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Mobile number must contain exactly 10 digits.");
        }
        if (emailId == null || !emailId.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty.");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City cannot be empty.");
        }
        if (state == null || state.trim().isEmpty()) {
            throw new IllegalArgumentException("State cannot be empty.");
        }
        if (gstNumber == null || gstNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("GST number cannot be empty.");
        }
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Supplier rating must be between 1 and 5.");
        }

        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.materialCategory = materialCategory;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.gstNumber = gstNumber;
        this.rating = rating;

        totalSupplierCount++;
        writeSupplierToFile();
    }

    private void writeSupplierToFile() {
        try (FileWriter fw = new FileWriter("suppliers.txt", true)) {
            fw.write(supplierId + "," + supplierName + "," + materialCategory + "," +
                    contactPerson + "," + phoneNumber + "," + emailId + "," +
                    address + "," + city + "," + state + "," + gstNumber + "," + rating + "\n");
        } catch (IOException e) {
            System.out.println("File error while saving supplier: " + e.getMessage());
        }
    }

    public void displaySupplier(int index) {
        System.out.println(index + ". " + supplierName +
                " | Category: " + materialCategory +
                " | Contact: " + contactPerson +
                " | Phone: " + phoneNumber +
                " | Rating: " + rating);
    }

    public static int getTotalSupplierCount() {
        return totalSupplierCount;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }
}