package com.example.rawmillmanagement.service;

import java.util.ArrayList;
import com.example.rawmillmanagement.model.Material;
public abstract class InventoryManager {

    protected ArrayList<Material> materialList;

    public InventoryManager() {
        materialList = new ArrayList<>();
    }

    public abstract void addMaterial(Material material);

    public abstract void displayMaterials();

    public abstract Material findMaterialById(String materialId);

    public abstract void checkLowStock();

    public int getMaterialCount() {
        return materialList.size();
    }

    public ArrayList<Material> getMaterialList() {
        return materialList;
    }
}