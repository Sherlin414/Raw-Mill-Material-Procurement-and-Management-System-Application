package com.example.rawmillmanagement.model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class PurchaseOrder {

    private static int totalPurchaseCount = 0;

    private String orderId;
    private String materialId;
    private String materialName;
    private String supplierId;
    private double purchasedQuantity;
    private double unitPrice;
    private double totalAmount;
    private String purchaserName;
    private String purchaserPhone;
    private String purchaserEmail;
    private LocalDate orderDate;
    private String paymentMode;
    private String deliveryStatus;

    public PurchaseOrder(String orderId, String materialId, String materialName,
                         String supplierId, double purchasedQuantity, double unitPrice,
                         String purchaserName, String purchaserPhone, String purchaserEmail,
                         String paymentMode, String deliveryStatus) {

        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Order ID cannot be empty.");
        }
        if (materialId == null || materialId.trim().isEmpty()) {
            throw new IllegalArgumentException("Material ID cannot be empty.");
        }
        if (materialName == null || materialName.trim().isEmpty()) {
            throw new IllegalArgumentException("Material name cannot be empty.");
        }
        if (supplierId == null || supplierId.trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier ID cannot be empty.");
        }
        if (purchasedQuantity <= 0) {
            throw new IllegalArgumentException("Purchased quantity must be greater than zero.");
        }
        if (unitPrice <= 0) {
            throw new IllegalArgumentException("Unit price must be greater than zero.");
        }
        if (purchaserName == null || purchaserName.trim().isEmpty()) {
            throw new IllegalArgumentException("Purchaser name cannot be empty.");
        }
        if (!purchaserPhone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Purchaser phone number must be 10 digits.");
        }
        if (purchaserEmail == null || !purchaserEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid purchaser email format.");
        }
        if (paymentMode == null || paymentMode.trim().isEmpty()) {
            throw new IllegalArgumentException("Payment mode cannot be empty.");
        }
        if (deliveryStatus == null || deliveryStatus.trim().isEmpty()) {
            throw new IllegalArgumentException("Delivery status cannot be empty.");
        }

        this.orderId = orderId;
        this.materialId = materialId;
        this.materialName = materialName;
        this.supplierId = supplierId;
        this.purchasedQuantity = purchasedQuantity;
        this.unitPrice = unitPrice;
        this.totalAmount = purchasedQuantity * unitPrice;
        this.purchaserName = purchaserName;
        this.purchaserPhone = purchaserPhone;
        this.purchaserEmail = purchaserEmail;
        this.paymentMode = paymentMode;
        this.deliveryStatus = deliveryStatus;
        this.orderDate = LocalDate.now();

        totalPurchaseCount++;
        writeOrderToFile();
    }

    private void writeOrderToFile() {
        try (FileWriter fw = new FileWriter("orders.txt", true)) {
            fw.write(orderId + "," + materialId + "," + materialName + "," + supplierId + "," +
                    purchasedQuantity + "," + unitPrice + "," + totalAmount + "," +
                    purchaserName + "," + purchaserPhone + "," + purchaserEmail + "," +
                    orderDate + "," + paymentMode + "," + deliveryStatus + "\n");
        } catch (IOException e) {
            System.out.println("File error while saving purchase order: " + e.getMessage());
        }
    }

    public void displayOrder(int index) {
        System.out.println(index + ". Order ID: " + orderId);
        System.out.println("   Material ID: " + materialId);
        System.out.println("   Material Name: " + materialName);
        System.out.println("   Supplier ID: " + supplierId);
        System.out.println("   Purchased Qty: " + purchasedQuantity);
        System.out.println("   Unit Price: " + unitPrice);
        System.out.println("   Total Cost: " + totalAmount);
        System.out.println("   Purchaser Name: " + purchaserName);
        System.out.println("   Phone: " + purchaserPhone);
        System.out.println("   Email: " + purchaserEmail);
        System.out.println("   Payment Mode: " + paymentMode);
        System.out.println("   Delivery Status: " + deliveryStatus);
        System.out.println("--------------------------------------------------");
    }

    public static int getTotalPurchaseCount() {
        return totalPurchaseCount;
    }

    public String getMaterialId() {
        return materialId;
    }

    public double getPurchasedQuantity() {
        return purchasedQuantity;
    }

    public String getPurchaserPhone() {
        return purchaserPhone;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public String getPurchaserEmail() {
        return purchaserEmail;
    }
}