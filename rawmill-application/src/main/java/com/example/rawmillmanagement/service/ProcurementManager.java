package com.example.rawmillmanagement.service;

import java.util.ArrayList;
import java.util.HashSet;

import com.example.rawmillmanagement.model.PurchaseOrder;
import com.example.rawmillmanagement.model.EmailNotification;
import com.example.rawmillmanagement.model.SMSNotification;


public class ProcurementManager {

    private ArrayList<PurchaseOrder> purchaseList;
    private MillInventoryManager inventoryManager;
    private HashSet<String> orderIds = new HashSet<>();
    public ProcurementManager(MillInventoryManager inventoryManager) {
        if (inventoryManager == null) {
            throw new IllegalArgumentException("Inventory manager cannot be null.");
        }
        this.inventoryManager = inventoryManager;
        purchaseList = new ArrayList<>();
    }

    public void placePurchase(PurchaseOrder order) {

    if (order == null) {
        throw new IllegalArgumentException("Purchase order cannot be null.");
    }

    if (orderIds.contains(order.getMaterialId() + order.getPurchaserPhone())) {
        throw new IllegalArgumentException("Duplicate purchase order detected.");
    }

    orderIds.add(order.getMaterialId() + order.getPurchaserPhone());

        purchaseList.add(order);

        inventoryManager.updateMaterialAfterPurchase(
                order.getMaterialId(),
                order.getPurchasedQuantity()
        );

        System.out.println("Purchase order placed successfully.");

        EmailNotification email = new EmailNotification(
                order.getPurchaserName(),
                order.getPurchaserEmail(),
                "Purchase Confirmation",
                "Purchase completed for material ID: " + order.getMaterialId() +
                        " | Quantity: " + order.getPurchasedQuantity(),
                "Medium"
        );

        SMSNotification sms = new SMSNotification(
                order.getPurchaserName(),
                order.getPurchaserPhone(),
                "Your purchase order has been placed successfully."
        );

        Thread emailThread = new Thread(email);
        Thread smsThread = new Thread(sms);

        emailThread.start();
        smsThread.start();

        try {
            emailThread.join();
            smsThread.join();
        } catch (InterruptedException e) {
            System.out.println("Notification thread interrupted: " + e.getMessage());
        }

        inventoryManager.checkLowStock();
    }

    public void displayPurchases() {
        if (purchaseList.isEmpty()) {
            System.out.println("No purchases recorded.");
            return;
        }

        System.out.println("===== PURCHASE HISTORY =====");
        int index = 1;
        for (PurchaseOrder p : purchaseList) {
            p.displayOrder(index++);
        }
        System.out.println("Total Purchases: " + purchaseList.size());
    }
}
