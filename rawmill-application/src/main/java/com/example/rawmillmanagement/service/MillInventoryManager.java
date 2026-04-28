package com.example.rawmillmanagement.service;

import java.util.HashSet;

import com.example.rawmillmanagement.model.Material;

public class MillInventoryManager extends InventoryManager {

    private HashSet<String> materialIds;

    public MillInventoryManager() {
        super();
        materialIds = new HashSet<>();
    }

    @Override
    public void addMaterial(Material material) {
        if (material == null) {
            throw new IllegalArgumentException("Material object cannot be null.");
        }
        if (materialIds.contains(material.getMaterialId().toLowerCase())) {
            throw new IllegalArgumentException("Duplicate material ID is not allowed.");
        }

        materialList.add(material);
        materialIds.add(material.getMaterialId().toLowerCase());
        System.out.println("Material added successfully.");
    }

    @Override
    public void displayMaterials() {
        if (materialList.isEmpty()) {
            System.out.println("No materials available in inventory.");
            return;
        }

        System.out.println("===== MATERIAL LIST =====");
        int index = 1;
        for (Material m : materialList) {
            m.displayMaterial(index++);
        }
        System.out.println("Total Materials: " + materialList.size());
    }

    @Override
    public Material findMaterialById(String materialId) {
        for (Material m : materialList) {
            if (m.getMaterialId().equalsIgnoreCase(materialId)) {
                return m;
            }
        }
        return null;
    }

    public synchronized void updateMaterialAfterPurchase(String materialId, double purchasedQty) {
        Material m = findMaterialById(materialId);

        if (m == null) {
            throw new IllegalArgumentException("Material not found in inventory.");
        }

        m.addQuantity(purchasedQty);
        System.out.println("Inventory updated after purchase.");
    }

    @Override
    public void checkLowStock() {
        System.out.println("===== LOW STOCK MATERIALS =====");
        boolean found = false;
        int index = 1;

        for (Material m : materialList) {
            if (m.isLowStock()) {
                System.out.println(index + ". Material ID: " + m.getMaterialId());
                System.out.println("   Material Name: " + m.getMaterialName());
                System.out.println("   Current Quantity: " + m.getQuantity() + " " + m.getUnit());
                System.out.println("   Reorder Level: " + m.getReorderLevel() + " " + m.getUnit());
                System.out.println("   Status: REORDER REQUIRED");
                System.out.println();
                found = true;
                index++;
            }
        }

        if (!found) {
            System.out.println("No low stock materials.");
        }
    }

    public int getLowStockCount() {
        int count = 0;
        for (Material m : materialList) {
            if (m.isLowStock()) {
                count++;
            }
        }
        return count;
    }

    public double getTotalMaterialQuantity() {
        double total = 0;
        for (Material m : materialList) {
            total += m.getQuantity();
        }
        return total;
    }
}