package com.example.rawmillmanagement.service;

import java.util.ArrayList;
import java.util.HashSet;

import com.example.rawmillmanagement.model.Supplier;

public class SupplierManager {

    private ArrayList<Supplier> supplierList;
    private HashSet<String> supplierIds;

    public SupplierManager() {
        supplierList = new ArrayList<>();
        supplierIds = new HashSet<>();
    }

    public void addSupplier(Supplier supplier) {
        if (supplier == null) {
            throw new IllegalArgumentException("Supplier object cannot be null.");
        }
        if (supplierIds.contains(supplier.getSupplierId().toLowerCase())) {
            throw new IllegalArgumentException("Duplicate supplier ID is not allowed.");
        }

        supplierList.add(supplier);
        supplierIds.add(supplier.getSupplierId().toLowerCase());
        System.out.println("Supplier added successfully.");
    }

    public void displaySuppliers() {
        if (supplierList.isEmpty()) {
            System.out.println("No suppliers available.");
            return;
        }

        System.out.println("===== SUPPLIER LIST =====");
        int index = 1;
        for (Supplier s : supplierList) {
            s.displaySupplier(index++);
        }
        System.out.println("Total Suppliers: " + supplierList.size());
    }

    public Supplier findSupplierById(String supplierId) {
        for (Supplier s : supplierList) {
            if (s.getSupplierId().equalsIgnoreCase(supplierId)) {
                return s;
            }
        }
        return null;
    }
}