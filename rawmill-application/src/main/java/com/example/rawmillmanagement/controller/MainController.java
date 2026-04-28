package com.example.rawmillmanagement.controller;

import com.example.rawmillmanagement.entity.MaterialEntity;
import com.example.rawmillmanagement.entity.PurchaseOrderEntity;
import com.example.rawmillmanagement.entity.SupplierEntity;
import com.example.rawmillmanagement.repository.MaterialRepository;
import com.example.rawmillmanagement.repository.PurchaseOrderRepository;
import com.example.rawmillmanagement.repository.SupplierRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private SupplierRepository supplierRepo;

    @Autowired
    private MaterialRepository materialRepo;

    @Autowired
    private PurchaseOrderRepository purchaseRepo;

    // ─── AUTH ────────────────────────────────────────────────────────────────

    @GetMapping("/")
    public String loginPage(HttpSession session) {
        if (session.getAttribute("role") != null) return "redirect:/dashboard";
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          RedirectAttributes ra) {
        String role = null;
        if (username.equals("admin") && password.equals("admin123")) role = "ADMIN";
        else if (username.equals("user") && password.equals("user123")) role = "USER";

        if (role == null) {
            ra.addFlashAttribute("error", "Invalid credentials. Please try again.");
            return "redirect:/";
        }
        session.setAttribute("role", role);
        session.setAttribute("username", username);
        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // ─── DASHBOARD ───────────────────────────────────────────────────────────

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("role") == null) return "redirect:/";
        List<MaterialEntity> materials = materialRepo.findAll();
        long lowStock = materials.stream().filter(MaterialEntity::isLowStock).count();
        double totalQty = materials.stream().mapToDouble(MaterialEntity::getQuantity).sum();
        double totalValue = purchaseRepo.findAll().stream().mapToDouble(PurchaseOrderEntity::getTotalAmount).sum();

        model.addAttribute("totalSuppliers", supplierRepo.count());
        model.addAttribute("totalMaterials", materialRepo.count());
        model.addAttribute("totalPurchases", purchaseRepo.count());
        model.addAttribute("lowStockCount", lowStock);
        model.addAttribute("totalQuantity", totalQty);
        model.addAttribute("totalValue", totalValue);
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("recentPurchases", purchaseRepo.findAll().stream()
                .sorted((a, b) -> b.getOrderDate().compareTo(a.getOrderDate()))
                .limit(5).toList());
        return "dashboard";
    }

    // ─── SUPPLIERS ───────────────────────────────────────────────────────────

    @GetMapping("/suppliers")
    public String suppliers(HttpSession session, Model model) {
        if (session.getAttribute("role") == null) return "redirect:/";
        model.addAttribute("suppliers", supplierRepo.findAll());
        model.addAttribute("role", session.getAttribute("role"));
        return "suppliers";
    }

    @GetMapping("/supplier/add")
    public String supplierAddForm(HttpSession session, Model model) {
        if (!"ADMIN".equals(session.getAttribute("role"))) return "redirect:/dashboard";
        model.addAttribute("role", session.getAttribute("role"));
        return "supplier";
    }

    @PostMapping("/supplier/save")
    public String saveSupplier(@RequestParam String supplierId,
                               @RequestParam String supplierName,
                               @RequestParam String materialCategory,
                               @RequestParam String contactPerson,
                               @RequestParam String phoneNumber,
                               @RequestParam String emailId,
                               @RequestParam String address,
                               @RequestParam String city,
                               @RequestParam String state,
                               @RequestParam String gstNumber,
                               @RequestParam double rating,
                               HttpSession session,
                               RedirectAttributes ra) {
        if (!"ADMIN".equals(session.getAttribute("role"))) return "redirect:/dashboard";
        if (supplierRepo.existsBySupplierId(supplierId)) {
            ra.addFlashAttribute("error", "Supplier ID already exists.");
            return "redirect:/supplier/add";
        }
        supplierRepo.save(new SupplierEntity(supplierId, supplierName, materialCategory,
                contactPerson, phoneNumber, emailId, address, city, state, gstNumber, rating));
        ra.addFlashAttribute("success", "Supplier added successfully!");
        return "redirect:/suppliers";
    }

    // ─── MATERIALS ───────────────────────────────────────────────────────────

    @GetMapping("/materials")
    public String materials(HttpSession session, Model model) {
        if (session.getAttribute("role") == null) return "redirect:/";
        model.addAttribute("materials", materialRepo.findAll());
        model.addAttribute("role", session.getAttribute("role"));
        return "materials";
    }

    @GetMapping("/material/add")
    public String materialAddForm(HttpSession session, Model model) {
        if (!"ADMIN".equals(session.getAttribute("role"))) return "redirect:/dashboard";
        model.addAttribute("suppliers", supplierRepo.findAll());
        model.addAttribute("role", session.getAttribute("role"));
        return "material";
    }

    @PostMapping("/material/save")
    public String saveMaterial(@RequestParam String materialId,
                               @RequestParam String materialName,
                               @RequestParam String materialType,
                               @RequestParam String grade,
                               @RequestParam double purityPercentage,
                               @RequestParam double quantity,
                               @RequestParam String unit,
                               @RequestParam double unitCost,
                               @RequestParam double reorderLevel,
                               @RequestParam String supplierId,
                               @RequestParam String storageLocation,
                               HttpSession session,
                               RedirectAttributes ra) {
        if (!"ADMIN".equals(session.getAttribute("role"))) return "redirect:/dashboard";
        if (materialRepo.existsByMaterialId(materialId)) {
            ra.addFlashAttribute("error", "Material ID already exists.");
            return "redirect:/material/add";
        }
        if (!supplierRepo.existsBySupplierId(supplierId)) {
            ra.addFlashAttribute("error", "Supplier ID not found. Add supplier first.");
            return "redirect:/material/add";
        }
        materialRepo.save(new MaterialEntity(materialId, materialName, materialType, grade,
                purityPercentage, quantity, unit, unitCost, reorderLevel, supplierId, storageLocation));
        ra.addFlashAttribute("success", "Material added successfully!");
        return "redirect:/materials";
    }

    // ─── PURCHASES ───────────────────────────────────────────────────────────

    @GetMapping("/purchases")
    public String purchases(HttpSession session, Model model) {
        if (session.getAttribute("role") == null) return "redirect:/";
        model.addAttribute("purchaseList", purchaseRepo.findAll());
        model.addAttribute("role", session.getAttribute("role"));
        return "purchases";
    }

    @GetMapping("/purchase/add")
    public String purchaseForm(HttpSession session, Model model) {
        if (session.getAttribute("role") == null) return "redirect:/";
        model.addAttribute("materials", materialRepo.findAll());
        model.addAttribute("suppliers", supplierRepo.findAll());
        model.addAttribute("role", session.getAttribute("role"));
        return "purchase";
    }

    @PostMapping("/purchase/save")
    public String savePurchase(@RequestParam String orderId,
                               @RequestParam String materialId,
                               @RequestParam String materialName,
                               @RequestParam String supplierId,
                               @RequestParam double purchasedQuantity,
                               @RequestParam double unitPrice,
                               @RequestParam String purchaserName,
                               @RequestParam String purchaserPhone,
                               @RequestParam String purchaserEmail,
                               @RequestParam String paymentMode,
                               @RequestParam String deliveryStatus,
                               HttpSession session,
                               RedirectAttributes ra) {
        if (session.getAttribute("role") == null) return "redirect:/";
        if (purchaseRepo.existsByOrderId(orderId)) {
            ra.addFlashAttribute("error", "Order ID already exists.");
            return "redirect:/purchase/add";
        }
        // Update material quantity in SQLite
        materialRepo.findByMaterialId(materialId).ifPresent(m -> {
            m.setQuantity(m.getQuantity() + purchasedQuantity);
            materialRepo.save(m);
        });
        purchaseRepo.save(new PurchaseOrderEntity(orderId, materialId, materialName, supplierId,
                purchasedQuantity, unitPrice, purchaserName, purchaserPhone, purchaserEmail,
                paymentMode, deliveryStatus));
        ra.addFlashAttribute("success", "Purchase order placed successfully!");
        return "redirect:/purchases";
    }

    // ─── LOW STOCK ───────────────────────────────────────────────────────────

    @GetMapping("/lowstock")
    public String lowstock(HttpSession session, Model model) {
        if (!"ADMIN".equals(session.getAttribute("role"))) return "redirect:/dashboard";
        List<MaterialEntity> low = materialRepo.findAll().stream()
                .filter(MaterialEntity::isLowStock).toList();
        model.addAttribute("lowStockMaterials", low);
        model.addAttribute("role", session.getAttribute("role"));
        return "lowstock";
    }

    // ─── SUMMARY ─────────────────────────────────────────────────────────────

    @GetMapping("/summary")
    public String summary(HttpSession session, Model model) {
        if (!"ADMIN".equals(session.getAttribute("role"))) return "redirect:/dashboard";
        List<MaterialEntity> materials = materialRepo.findAll();
        long lowStock = materials.stream().filter(MaterialEntity::isLowStock).count();
        double totalQty = materials.stream().mapToDouble(MaterialEntity::getQuantity).sum();
        double totalValue = purchaseRepo.findAll().stream().mapToDouble(PurchaseOrderEntity::getTotalAmount).sum();

        model.addAttribute("totalSuppliers", supplierRepo.count());
        model.addAttribute("totalMaterials", materialRepo.count());
        model.addAttribute("totalPurchases", purchaseRepo.count());
        model.addAttribute("lowStockCount", lowStock);
        model.addAttribute("totalQuantity", totalQty);
        model.addAttribute("totalValue", totalValue);
        model.addAttribute("role", session.getAttribute("role"));
        return "summary";
    }
}
