package com.example.rawmillmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "suppliers")
public class SupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
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

    public SupplierEntity() {}

    public SupplierEntity(String supplierId, String supplierName, String materialCategory,
                          String contactPerson, String phoneNumber, String emailId,
                          String address, String city, String state, String gstNumber, double rating) {
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
    }

    public Long getId() { return id; }
    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }
    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    public String getMaterialCategory() { return materialCategory; }
    public void setMaterialCategory(String materialCategory) { this.materialCategory = materialCategory; }
    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getGstNumber() { return gstNumber; }
    public void setGstNumber(String gstNumber) { this.gstNumber = gstNumber; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}
