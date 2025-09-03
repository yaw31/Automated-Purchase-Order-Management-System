/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_oodj_group33;

public class Supplier {
    private String supplierId;
    private String itemId;
    private String itemName;
    private double unitPrice;
    
    public Supplier(String supplierId, String itemId, String itemName, double unitPrice) {
        this.supplierId = supplierId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
    }
    
    public String getSupplierId() {
        return supplierId;
    }
    
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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
    
    public double getUnitPrice(){
        return unitPrice;
    }
    
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
  
    public String getSupplierDetails() {
        return supplierId + "," + itemId + "," + itemName + "," + unitPrice;
    }
}
