package com.example.rawmillmanagement.model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class MillProduct {

    private static int totalProductCount = 0;

    private String productId;
    private String productName;
    private String productType;
    private String qualityGrade;
    private double productionQuantity;
    private String unit;
    private double productionCost;
    private LocalDate productionDate;
    private String producedBy;
    private String storageSilo;
    private String remarks;

    public MillProduct(String productId, String productName, String productType,
                       String qualityGrade, double productionQuantity, String unit,
                       double productionCost, String producedBy, String storageSilo,
                       String remarks) {

        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty.");
        }
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        if (productType == null || productType.trim().isEmpty()) {
            throw new IllegalArgumentException("Product type cannot be empty.");
        }
        if (qualityGrade == null || qualityGrade.trim().isEmpty()) {
            throw new IllegalArgumentException("Quality grade cannot be empty.");
        }
        if (productionQuantity <= 0) {
            throw new IllegalArgumentException("Production quantity must be greater than zero.");
        }
        if (unit == null || unit.trim().isEmpty()) {
            throw new IllegalArgumentException("Unit cannot be empty.");
        }
        if (productionCost < 0) {
            throw new IllegalArgumentException("Production cost cannot be negative.");
        }
        if (producedBy == null || producedBy.trim().isEmpty()) {
            throw new IllegalArgumentException("Produced by cannot be empty.");
        }
        if (storageSilo == null || storageSilo.trim().isEmpty()) {
            throw new IllegalArgumentException("Storage silo cannot be empty.");
        }

        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.qualityGrade = qualityGrade;
        this.productionQuantity = productionQuantity;
        this.unit = unit;
        this.productionCost = productionCost;
        this.productionDate = LocalDate.now();
        this.producedBy = producedBy;
        this.storageSilo = storageSilo;
        this.remarks = remarks;

        totalProductCount++;
        writeProductToFile();
    }

    private void writeProductToFile() {
        try (FileWriter fw = new FileWriter("millproducts.txt", true)) {
            fw.write(productId + "," + productName + "," + productType + "," +
                    qualityGrade + "," + productionQuantity + "," + unit + "," +
                    productionCost + "," + productionDate + "," + producedBy + "," +
                    storageSilo + "," + remarks + "\n");
        } catch (IOException e) {
            System.out.println("File error while saving mill product: " + e.getMessage());
        }
    }

    public void displayProduct(int index) {
        System.out.println(index + ". " + productName +
                " | Type: " + productType +
                " | Grade: " + qualityGrade +
                " | Qty: " + productionQuantity + " " + unit +
                " | Silo: " + storageSilo);
    }

    public static int getTotalProductCount() {
        return totalProductCount;
    }
}