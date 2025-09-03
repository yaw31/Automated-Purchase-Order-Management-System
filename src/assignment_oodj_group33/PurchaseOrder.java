/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class PurchaseOrder {
    private String poId, prId, itemId, itemName, supplierId, purchaseManager,
            status, financeManager;
    private int quantity, receivedQuantity;
    private double unitPrice, totalAmount;
    
    public PurchaseOrder(String poId, String prId, String itemId, String itemName, int quantity, double totalAmount, String status) {
        this.poId = poId;
        this.prId = prId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.status = status;
    }
    
    public PurchaseOrder(String poId, String prId, String itemId, String itemName, 
            int quantity, String supplierId, double unitPrice, double totalAmount, String purchaseManager, 
            String financeManager, String status, int receivedQuantity) {
        this.poId = poId;
        this.prId = prId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.supplierId = supplierId;
        this.unitPrice = unitPrice;
        this.totalAmount = totalAmount;
        this.purchaseManager = purchaseManager;
        this.financeManager = financeManager;
        this.status = status;
        this.receivedQuantity = receivedQuantity;
    }

    
    
    public String getPoId() {
        return poId;
    }
    
    public void setPoId(String poId) {
        this.poId = poId;
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
    
    public void setQuantity(int quantity) throws InvalidNumberException {
        if (quantity < 0) {
            throw new InvalidNumberException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }
    
    public String getSupplierId() {
        return supplierId;
    }
    
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    
    public double getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getPurchaseManager() {
        return purchaseManager;
    }
    
    public void setPurchaseManager(String purchaseManager) {
        this.purchaseManager = purchaseManager;
    }
    
    public String getFinanceManager() {
        return financeManager;
    }
    
    public void setFinanceManager(String financeManager) {
        this.financeManager = financeManager;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getReceivedQuantity() {
        return receivedQuantity;
    }
    
    public void setReceivedQuantity(int receivedQuantity){
        this.receivedQuantity = receivedQuantity;
    }

    public String getPODetails() {
        return poId + "," + prId + "," + itemId + "," + itemName + "," + quantity + "," + supplierId + "," + totalAmount + "," +
               purchaseManager + "," + financeManager + "," + status + "," + receivedQuantity;
    }    
    
    public String getFinanceFlag() {
        if (receivedQuantity > quantity) {
            return "Over Delivered";
        } else if (receivedQuantity < quantity) {
            return "Under Delivered";
        } else {
            return "Matched";
        }
    }


    public String toFileString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", poId, prId, itemId, itemName,
           quantity, supplierId, unitPrice, totalAmount,
           purchaseManager, financeManager,status, receivedQuantity);}
    
    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            String line = poId + "," + prId + "," + itemId + "," + itemName + "," + quantity + "," + supplierId + ","
                    + unitPrice + "," + totalAmount + "," + purchaseManager + "," + financeManager + "," + status + "," + receivedQuantity;
            
            bw.write(line);
            bw.newLine();
        }
    }


}
