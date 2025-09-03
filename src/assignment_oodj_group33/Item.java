/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;


public class Item {
    private String itemID;
    private String itemName;
    private String supplierID;
    private int quantity;
    private double price;
    private String latestUpdateDate;
    private String status;
    
    public Item(String itemID, String itemName, String supplierID, int quantity, double price, String latestUpdateDate, String status){
        this.itemID = itemID;
        this.itemName = itemName;
        this.supplierID = supplierID;
        this.quantity = quantity;
        this.price = price;
        this.latestUpdateDate = latestUpdateDate;
        this.status = status;
    }

    public Item(String itemID, String ItemName, String SupplierID, int quantity,
                               double price, String latestUpdateDate) {
        this.itemID = itemID;
        this.itemName = ItemName;
        this.supplierID = SupplierID;
        this.quantity = quantity;
        this.price = price;
        this.latestUpdateDate = latestUpdateDate;
        this.status = "Low-Stock";
    }
    
    public Item(String ItemID, String ItemName, int quantity, double price, String SupplierID) {
        this.itemID = ItemID;
        this.itemName = ItemName;
        this.quantity = quantity;
        this.price = price;
        this.supplierID = SupplierID;
    }
    
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws InvalidNumberException {
        if (quantity < 0){
            throw new InvalidNumberException("Quantity cannot be negative");
        }
        else{
            this.quantity = quantity;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws InvalidNumberException {
        if (price <= 0){
            throw new InvalidNumberException("Price cannot be 0 or less than 0");
        }
        else{
            this.price = price;
        }
    }
    
    public String getLatestUpdateDate() {
        return latestUpdateDate;
    }
    
    public void setLatestUpdateDate(String latestUpdateDate) {
        this.latestUpdateDate = latestUpdateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return itemID + "," + itemName + "," + supplierID + "," + quantity + "," + price + "," + latestUpdateDate + "," + status;
    }
    
    public String toFileString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s",itemID,itemName,supplierID,quantity,price,latestUpdateDate,status);
    }
}