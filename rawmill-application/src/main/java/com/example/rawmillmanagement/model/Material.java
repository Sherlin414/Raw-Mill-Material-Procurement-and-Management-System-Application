package com.example.rawmillmanagement.model;

import java.io.FileWriter;
import java.io.IOException;

public class Material {

    private static int totalMaterialCount = 0;

    private String materialId;
    private String materialName;
    private String materialType;
    private String grade;
    private double purityPercentage;
    private double quantity;
    private String unit;
    private double unitCost;
    private double reorderLevel;
    private String supplierId;
    private String storageLocation;

    public Material(String materialId, String materialName, String materialType,
                    String grade, double purityPercentage, double quantity,
                    String unit, double unitCost, double reorderLevel,
                    String supplierId, String storageLocation) {

        if (materialId == null || materialId.trim().isEmpty()) {
            throw new IllegalArgumentException("Material ID cannot be empty.");
        }
        if (materialName == null || materialName.trim().isEmpty()) {
            throw new IllegalArgumentException("Material name cannot be empty.");
        }
        if (materialType == null || materialType.trim().isEmpty()) {
            throw new IllegalArgumentException("Material type cannot be empty.");
        }
        if (grade == null || grade.trim().isEmpty()) {
            throw new IllegalArgumentException("Grade cannot be empty.");
        }
        if (purityPercentage < 0 || purityPercentage > 100) {
            throw new IllegalArgumentException("Purity percentage must be between 0 and 100.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        if (unit == null || unit.trim().isEmpty()) {
            throw new IllegalArgumentException("Unit cannot be empty.");
        }
        if (unitCost <= 0) {
            throw new IllegalArgumentException("Unit cost must be greater than zero.");
        }
        if (reorderLevel < 0) {
            throw new IllegalArgumentException("Reorder level cannot be negative.");
        }
        if (supplierId == null || supplierId.trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier ID cannot be empty.");
        }
        if (storageLocation == null || storageLocation.trim().isEmpty()) {
            throw new IllegalArgumentException("Storage location cannot be empty.");
        }

        this.materialId = materialId;
        this.materialName = materialName;
        this.materialType = materialType;
        this.grade = grade;
        this.purityPercentage = purityPercentage;
        this.quantity = quantity;
        this.unit = unit;
        this.unitCost = unitCost;
        this.reorderLevel = reorderLevel;
        this.supplierId = supplierId;
        this.storageLocation = storageLocation;

        totalMaterialCount++;
        writeMaterialToFile();
    }

    public synchronized void addQuantity(double qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        this.quantity += qty;
    }

    public boolean isLowStock() {
        return quantity <= reorderLevel;
    }

    private void writeMaterialToFile() {
        try (FileWriter fw = new FileWriter("materials.txt", true)) {
            fw.write(materialId + "," + materialName + "," + materialType + "," + grade + "," +
                    purityPercentage + "," + quantity + "," + unit + "," + unitCost + "," +
                    reorderLevel + "," + supplierId + "," + storageLocation + "\n");
        } catch (IOException e) {
            System.out.println("File error while saving material: " + e.getMessage());
        }
    }

    public void displayMaterial(int index) {
        System.out.println(index + ". " + materialName +
                " | Type: " + materialType +
                " | Grade: " + grade +
                " | Purity: " + purityPercentage + "%" +
                " | Qty: " + quantity + " " + unit +
                " | Cost: " + unitCost +
                " | Supplier: " + supplierId);
    }

    public static int getTotalMaterialCount() {
        return totalMaterialCount;
    }

    public String getMaterialId() {
        return materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getReorderLevel() {
        return reorderLevel;
    }

    public String getUnit() {
        return unit;
    }

    public String getSupplierId() {
        return supplierId;
    }
}
