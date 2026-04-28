package com.example.rawmillmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "materials")
public class MaterialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
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

    public MaterialEntity() {}

    public MaterialEntity(String materialId, String materialName, String materialType,
                          String grade, double purityPercentage, double quantity,
                          String unit, double unitCost, double reorderLevel,
                          String supplierId, String storageLocation) {
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
    }

    public boolean isLowStock() {
        return quantity <= reorderLevel;
    }

    public Long getId() { return id; }
    public String getMaterialId() { return materialId; }
    public void setMaterialId(String materialId) { this.materialId = materialId; }
    public String getMaterialName() { return materialName; }
    public void setMaterialName(String materialName) { this.materialName = materialName; }
    public String getMaterialType() { return materialType; }
    public void setMaterialType(String materialType) { this.materialType = materialType; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public double getPurityPercentage() { return purityPercentage; }
    public void setPurityPercentage(double purityPercentage) { this.purityPercentage = purityPercentage; }
    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public double getUnitCost() { return unitCost; }
    public void setUnitCost(double unitCost) { this.unitCost = unitCost; }
    public double getReorderLevel() { return reorderLevel; }
    public void setReorderLevel(double reorderLevel) { this.reorderLevel = reorderLevel; }
    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }
    public String getStorageLocation() { return storageLocation; }
    public void setStorageLocation(String storageLocation) { this.storageLocation = storageLocation; }
}
