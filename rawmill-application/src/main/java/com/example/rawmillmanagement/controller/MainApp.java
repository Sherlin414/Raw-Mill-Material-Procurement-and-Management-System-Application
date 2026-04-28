package com.example.rawmillmanagement.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.example.rawmillmanagement.model.Supplier;
import com.example.rawmillmanagement.model.Material;
import com.example.rawmillmanagement.model.PurchaseOrder;
import com.example.rawmillmanagement.service.MillInventoryManager;
import com.example.rawmillmanagement.service.SupplierManager;
import com.example.rawmillmanagement.service.ProcurementManager;

public class MainApp {

    public static void generateSummaryReport(MillInventoryManager inventoryManager) {
        try (FileWriter fw = new FileWriter("summary_report.txt")) {
            fw.write("===== SYSTEM SUMMARY REPORT =====\n");
            fw.write("Total Suppliers           : " + Supplier.getTotalSupplierCount() + "\n");
            fw.write("Total Materials Added     : " + Material.getTotalMaterialCount() + "\n");
            fw.write("Total Purchase Orders     : " + PurchaseOrder.getTotalPurchaseCount() + "\n");
            fw.write("Total Material Quantity   : " + inventoryManager.getTotalMaterialQuantity() + " Ton\n");
            fw.write("Low Stock Materials Count : " + inventoryManager.getLowStockCount() + "\n");
            System.out.println("Report saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing summary report: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
        System.out.println("===== LOGIN SYSTEM =====");
    System.out.print("Enter Username: ");
    String username = sc.nextLine();

    System.out.print("Enter Password: ");
    String password = sc.nextLine();

    String role = "";

    if(username.equals("admin") && password.equals("admin123")) {
        role = "ADMIN";
        System.out.println("Admin Login Successful.");
    }
    else if(username.equals("user") && password.equals("user123")) {
        role = "USER";
        System.out.println("User Login Successful.");
    }
    else {
        System.out.println("Invalid Login. System closed.");
        sc.close();
        return;
    }

        MillInventoryManager inventoryManager = new MillInventoryManager();
        SupplierManager supplierManager = new SupplierManager();
        ProcurementManager procurementManager = new ProcurementManager(inventoryManager);

        int choice = 0;

        do {
            System.out.println("\n===== RAW MILL PROCUREMENT & MANAGEMENT SYSTEM =====");

if(role.equals("ADMIN")) {
    System.out.println("1. Add Supplier");
    System.out.println("2. Add Material");
    System.out.println("3. View Materials");
    System.out.println("4. Purchase Material");
    System.out.println("5. View Purchases");
    System.out.println("6. Check Low Stock");
    System.out.println("7. System Summary Report");
    System.out.println("8. Exit");
}

if(role.equals("USER")) {
    System.out.println("3. View Materials");
    System.out.println("4. Purchase Material");
    System.out.println("5. View Purchases");
    System.out.println("8. Exit");
}

            try {
                choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {

                    case 1:
                        if(role.equals("USER")) {
                        System.out.println("Access Denied.");
                        break;
                        }
                        try {
                            System.out.print("Supplier ID: ");
                            String sid = sc.nextLine().trim();
                            if (sid.isEmpty()) {
                                throw new IllegalArgumentException("Supplier ID cannot be empty.");
                            }

                            System.out.print("Supplier Name: ");
                            String sname = sc.nextLine().trim();
                            if (sname.isEmpty()) {
                                throw new IllegalArgumentException("Supplier name cannot be empty.");
                            }

                            System.out.print("Material Category: ");
                            String cat = sc.nextLine().trim();
                            if (cat.isEmpty()) {
                                throw new IllegalArgumentException("Material category cannot be empty.");
                            }

                            System.out.print("Contact Person: ");
                            String cp = sc.nextLine().trim();
                            if (cp.isEmpty()) {
                                throw new IllegalArgumentException("Contact person cannot be empty.");
                            }

                            System.out.print("Phone (10 digits): ");
                            String phone = sc.nextLine().trim();
                            if (!phone.matches("\\d{10}")) {
                                throw new IllegalArgumentException("Mobile number must contain exactly 10 digits.");
                            }

                            System.out.print("Email: ");
                            String email = sc.nextLine().trim();
                            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                                throw new IllegalArgumentException("Invalid email format.");
                            }

                            System.out.print("Address: ");
                            String addr = sc.nextLine().trim();
                            if (addr.isEmpty()) {
                                throw new IllegalArgumentException("Address cannot be empty.");
                            }

                            System.out.print("City: ");
                            String city = sc.nextLine().trim();
                            if (city.isEmpty()) {
                                throw new IllegalArgumentException("City cannot be empty.");
                            }

                            System.out.print("State: ");
                            String state = sc.nextLine().trim();
                            if (state.isEmpty()) {
                                throw new IllegalArgumentException("State cannot be empty.");
                            }

                            System.out.print("GST Number: ");
                            String gst = sc.nextLine().trim();
                            if (gst.isEmpty()) {
                                throw new IllegalArgumentException("GST number cannot be empty.");
                            }

                            System.out.print("Rating (1-5): ");
                            double rating = Double.parseDouble(sc.nextLine().trim());
                            if (rating < 1 || rating > 5) {
                                throw new IllegalArgumentException("Supplier rating must be between 1 and 5.");
                            }

                            Supplier s = new Supplier(sid, sname, cat, cp, phone, email, addr, city, state, gst, rating);
                            supplierManager.addSupplier(s);

                        } catch (Exception e) {
                            System.out.println("Supplier input error: " + e.getMessage());
                        }
                        break;

                    case 2:
                        if(role.equals("USER")) {
   System.out.println("Access Denied.");
   break;
}
                        try {
                            System.out.print("Material ID: ");
                            String mid = sc.nextLine().trim();
                            if (mid.isEmpty()) {
                                throw new IllegalArgumentException("Material ID cannot be empty.");
                            }

                            System.out.print("Material Name: ");
                            String mname = sc.nextLine().trim();
                            if (mname.isEmpty()) {
                                throw new IllegalArgumentException("Material name cannot be empty.");
                            }

                            System.out.print("Material Type: ");
                            String mtype = sc.nextLine().trim();
                            if (mtype.isEmpty()) {
                                throw new IllegalArgumentException("Material type cannot be empty.");
                            }

                            System.out.print("Grade: ");
                            String grade = sc.nextLine().trim();
                            if (grade.isEmpty()) {
                                throw new IllegalArgumentException("Grade cannot be empty.");
                            }

                            System.out.print("Purity %: ");
                            double purity = Double.parseDouble(sc.nextLine().trim());
                            if (purity < 0 || purity > 100) {
                                throw new IllegalArgumentException("Purity percentage must be between 0 and 100.");
                            }

                            System.out.print("Quantity: ");
                            double qty = Double.parseDouble(sc.nextLine().trim());
                            if (qty < 0) {
                                throw new IllegalArgumentException("Quantity cannot be negative.");
                            }

                            System.out.print("Unit: ");
                            String unit = sc.nextLine().trim();
                            if (unit.isEmpty()) {
                                throw new IllegalArgumentException("Unit cannot be empty.");
                            }

                            System.out.print("Unit Cost: ");
                            double cost = Double.parseDouble(sc.nextLine().trim());
                            if (cost <= 0) {
                                throw new IllegalArgumentException("Unit cost must be greater than zero.");
                            }

                            System.out.print("Reorder Level: ");
                            double reorder = Double.parseDouble(sc.nextLine().trim());
                            if (reorder < 0) {
                                throw new IllegalArgumentException("Reorder level cannot be negative.");
                            }

                            System.out.print("Supplier ID: ");
                            String supId = sc.nextLine().trim();

                            if (supId.isEmpty()) {
                                throw new IllegalArgumentException("Supplier ID cannot be empty.");
                            }

                            if (supplierManager.findSupplierById(supId) == null) {
                                throw new IllegalArgumentException("Supplier does not exist. Add supplier first.");
                            }

                            System.out.print("Storage Location: ");
                            String loc = sc.nextLine().trim();
                            if (loc.isEmpty()) {
                                throw new IllegalArgumentException("Storage location cannot be empty.");
                            }

                            Material m = new Material(mid, mname, mtype, grade, purity, qty, unit, cost, reorder, supId, loc);
                            inventoryManager.addMaterial(m);

                        } catch (Exception e) {
                            System.out.println("Material input error: " + e.getMessage());
                        }
                        break;

                    case 3:
                        inventoryManager.displayMaterials();
                        break;

                    case 4:
                        try {
                            System.out.print("Order ID: ");
                            String oid = sc.nextLine().trim();
                            if (oid.isEmpty()) {
                                throw new IllegalArgumentException("Order ID cannot be empty.");
                            }

                            System.out.print("Material ID: ");
                            String mid = sc.nextLine().trim();
                            if (mid.isEmpty()) {
                                throw new IllegalArgumentException("Material ID cannot be empty.");
                            }
 
                            if (inventoryManager.findMaterialById(mid) == null) {
                                throw new IllegalArgumentException("Material not found in inventory.");
                            }

                            System.out.print("Material Name: ");
                            String mname = sc.nextLine().trim();
                            if (mname.isEmpty()) {
                                throw new IllegalArgumentException("Material name cannot be empty.");
                            }

                            System.out.print("Supplier ID: ");
                            String sid = sc.nextLine().trim();

                            if (sid.isEmpty()) {
                                throw new IllegalArgumentException("Supplier ID cannot be empty.");
                            }

                            if (supplierManager.findSupplierById(sid) == null) {
                                throw new IllegalArgumentException("Supplier not found. Add supplier first.");
                            }

                            System.out.print("Purchase Quantity: ");
                            double pqty = Double.parseDouble(sc.nextLine().trim());
                            if (pqty <= 0) {
                                throw new IllegalArgumentException("Purchased quantity must be greater than zero.");
                            }

                            System.out.print("Unit Price: ");
                            double price = Double.parseDouble(sc.nextLine().trim());
                            if (price <= 0) {
                                throw new IllegalArgumentException("Unit price must be greater than zero.");
                            }

                            System.out.print("Purchaser Name: ");
                            String pname = sc.nextLine().trim();
                            if (pname.isEmpty()) {
                                throw new IllegalArgumentException("Purchaser name cannot be empty.");
                            }

                            System.out.print("Purchaser Phone: ");
                            String pphone = sc.nextLine().trim();
                            if (!pphone.matches("\\d{10}")) {
                                throw new IllegalArgumentException("Purchaser phone number must be 10 digits.");
                            }

                            System.out.print("Purchaser Email: ");
                            String pemail = sc.nextLine().trim();
                            if (!pemail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                                throw new IllegalArgumentException("Invalid purchaser email format.");
                            }

                            System.out.print("Payment Mode: ");
                            String pay = sc.nextLine().trim();
                            if (pay.isEmpty()) {
                                throw new IllegalArgumentException("Payment mode cannot be empty.");
                            }

                            System.out.print("Delivery Status: ");
                            String status = sc.nextLine().trim();
                            if (status.isEmpty()) {
                                throw new IllegalArgumentException("Delivery status cannot be empty.");
                            }

                            PurchaseOrder po = new PurchaseOrder(
                                    oid, mid, mname, sid, pqty, price,
                                    pname, pphone, pemail, pay, status
                            );

                            procurementManager.placePurchase(po);

                        } catch (Exception e) {
                            System.out.println("Purchase input error: " + e.getMessage());
                        }
                        break;

                    case 5:
                        procurementManager.displayPurchases();
                        break;

                    case 6:
                        if(role.equals("USER")) {
   System.out.println("Access Denied.");
   break;
}
                        inventoryManager.checkLowStock();
                        break;

                    case 7:

                    if(role.equals("USER")) {
   System.out.println("Access Denied.");
   break;
}
                        System.out.println("\n===== SYSTEM SUMMARY REPORT =====");
                        System.out.println("Total Suppliers           : " + Supplier.getTotalSupplierCount());
                        System.out.println("Total Materials Added     : " + Material.getTotalMaterialCount());
                        System.out.println("Total Purchase Orders     : " + PurchaseOrder.getTotalPurchaseCount());
                        System.out.println("Total Material Quantity   : " + inventoryManager.getTotalMaterialQuantity() + " Ton");
                        System.out.println("Low Stock Materials Count : " + inventoryManager.getLowStockCount());
                        generateSummaryReport(inventoryManager);
                        break;

                    case 8:
                        System.out.println("System closed.");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter correct numeric value.");
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }

        } while (choice != 8);

        sc.close();
    }
}
}