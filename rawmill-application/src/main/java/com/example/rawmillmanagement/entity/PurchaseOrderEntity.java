package com.example.rawmillmanagement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
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
    private String paymentMode;
    private String deliveryStatus;
    private LocalDate orderDate;

    public PurchaseOrderEntity() {}

    public PurchaseOrderEntity(String orderId, String materialId, String materialName,
                               String supplierId, double purchasedQuantity, double unitPrice,
                               String purchaserName, String purchaserPhone, String purchaserEmail,
                               String paymentMode, String deliveryStatus) {
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
    }

    public Long getId() { return id; }
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getMaterialId() { return materialId; }
    public void setMaterialId(String materialId) { this.materialId = materialId; }
    public String getMaterialName() { return materialName; }
    public void setMaterialName(String materialName) { this.materialName = materialName; }
    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }
    public double getPurchasedQuantity() { return purchasedQuantity; }
    public void setPurchasedQuantity(double purchasedQuantity) { this.purchasedQuantity = purchasedQuantity; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getPurchaserName() { return purchaserName; }
    public void setPurchaserName(String purchaserName) { this.purchaserName = purchaserName; }
    public String getPurchaserPhone() { return purchaserPhone; }
    public void setPurchaserPhone(String purchaserPhone) { this.purchaserPhone = purchaserPhone; }
    public String getPurchaserEmail() { return purchaserEmail; }
    public void setPurchaserEmail(String purchaserEmail) { this.purchaserEmail = purchaserEmail; }
    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
    public String getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(String deliveryStatus) { this.deliveryStatus = deliveryStatus; }
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
}
