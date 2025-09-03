/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;

import java.time.LocalDate;


public class PurchaseRequisition {
    private String prId;
    private String itemId;
    private String itemName;
    private int quantity;
    private LocalDate requireDate;
    private String supplierId;
    private String salesManager;

    public PurchaseRequisition(String prId, String itemId, String itemName, int quantity,
                               LocalDate requireDate, String supplierId, String salesManager) {
        this.prId = prId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.requireDate = requireDate;
        this.supplierId = supplierId;
        this.salesManager = salesManager;
    }
    
    public PurchaseRequisition(String prId, String itemId, int quantity, LocalDate requireDate) {
        this.prId = prId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.requireDate = requireDate;
    }

    public String getPrId() {
        return prId;
    }

    public void setPrId(String prId) {
        this.prId = prId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getRequireDate() {
        return requireDate;
    }

    public void setRequireDate(LocalDate requireDate) {
        this.requireDate = requireDate;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSalesManager() {
        return salesManager;
    }

    public void setSalesManager(String salesManager) {
        this.salesManager = salesManager;
    }
    
    public String getPRDetails() {
        return prId + "," + itemId + "," + itemName + "," + quantity + "," +
               requireDate + "," + supplierId + "," + salesManager;
    }
    
}
