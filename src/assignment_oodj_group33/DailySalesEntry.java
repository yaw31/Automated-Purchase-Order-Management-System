/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DailySalesEntry {
    private String salesEntryID;
    private String date;
    private String itemID;
    private String itemName;
    private int quantitySold;
    private String salesManagerID;

    public DailySalesEntry(String SalesEntryID, String Date, String ItemID, String ItemName, int QuantitySold, String SalesManagerID){
        this.salesEntryID = SalesEntryID;
        this.date = Date;
        this.itemID = ItemID;
        this.itemName = ItemName;
        this.quantitySold = QuantitySold;
        this.salesManagerID = SalesManagerID;
    }
    
    public DailySalesEntry(String SalesEntryID, String ItemID, int QuantitySold, String Date, String SalesManagerID){
        this.salesEntryID = SalesEntryID;
        this.itemID = ItemID;
        this.quantitySold = QuantitySold;
        this.date = Date;
        this.salesManagerID = SalesManagerID;
    }
    
     public String getSalesEntryID() {
        return salesEntryID;
    }

    public void setSalesEntryID(String salesEntryID) {
        this.salesEntryID = salesEntryID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public String getSalesManagerID() {
        return salesManagerID;
    }

    public void setSalesManagerID(String salesManagerID) {
        this.salesManagerID = salesManagerID;
    }

    public String getSalesDetails() {
        return salesEntryID + "," + date + "," + itemID + "," + itemName + "," + quantitySold + "," + salesManagerID;
    }
    
    public static ArrayList<DailySalesEntry> loadFromFile(String fileName) {
    ArrayList<DailySalesEntry> list = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                String salesentryID = parts[0];
                String date = parts[1];
                String itemID = parts[2];
                String itemName = parts[3];
                int quantitySold = Integer.parseInt(parts[4]);
                String salesmanagerID = parts[5];

                list.add(new DailySalesEntry(salesentryID, date, itemID, itemName, quantitySold, salesmanagerID));
            } 
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
        }
    return list;
    }
    
    public static void saveAddToFile(DailySalesEntry SE, String fileName){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))){
            bw.write(SE.getSalesDetails());
            bw.newLine();
        } catch (IOException e){
            System.out.println("Error Writing to file: "+ e.getMessage());
        }
    }
    
    public static void saveEditDeleteToFile(ArrayList<DailySalesEntry> list, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (DailySalesEntry SE : list) {
                bw.write(SE.getSalesDetails());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public static boolean addNewSale(
        String salesentryID,
        String itemID,
        int quantitySold,
        String date,
        String salesmanagerID,
        String itemFilePath,
        String salesEntryFilePath
    ) {
        ArrayList<DailySalesEntry> existingSalesEntry = loadFromFile(salesEntryFilePath);
        for (DailySalesEntry sale : existingSalesEntry) {
            if (sale.getSalesEntryID().equalsIgnoreCase(salesentryID)) {
                throw new IllegalArgumentException("Sales Entry ID already exists. Please enter a new ID.");
            }
        }
        ArrayList<Item> itemList = FileHandler.loadItem(itemFilePath);
        boolean itemFound = false;
        String itemName = "";

        for (Item item : itemList) {
            if (item.getItemID().equalsIgnoreCase(itemID)) {
                itemFound = true;
                itemName = item.getItemName();
                int currentStock = item.getQuantity();

                if (quantitySold > currentStock) {
                    throw new IllegalArgumentException("Not enough stock available.");
                }
                try{
                item.setQuantity(currentStock - quantitySold);
                }catch(InvalidNumberException e){
                    System.err.println("Invalid number format when setting quantity: " + e.getMessage());
                }
                item.setLatestUpdateDate(date);
                break;
            }
        }

        if (!itemFound) {
            throw new IllegalArgumentException("Item ID not found in item.txt.");
        }

        FileHandler.smItemSaveEditDeleteToFile(itemList, itemFilePath);
        DailySalesEntry entry = new DailySalesEntry(salesentryID, date, itemID, itemName, quantitySold, salesmanagerID);
        saveAddToFile(entry, salesEntryFilePath);
        return false;
    }
    
    public static void editSale(
        String salesentryID,
        String newItemID,
        int newQuantitySold,
        String newDate,
        String salesmanagerID,
        String itemFilePath,
        String salesEntryFilePath
    ) {
        ArrayList<DailySalesEntry> salesList = loadFromFile(salesEntryFilePath);
        ArrayList<Item> itemList = FileHandler.loadItem(itemFilePath);
        
        DailySalesEntry originalSaleEn = null;
        for (DailySalesEntry saleEn : salesList) {
            if (saleEn.getSalesEntryID().equalsIgnoreCase(salesentryID)) {
                originalSaleEn = saleEn;
                break;
            }
        }
        
        if (originalSaleEn == null) {
            throw new IllegalArgumentException("Sales Entry ID not found.");
        }
        for (Item item : itemList) {
            if (item.getItemID().equalsIgnoreCase(originalSaleEn.getItemID())) {
                try {
                    item.setQuantity(item.getQuantity() + originalSaleEn.getQuantitySold());
                } catch (InvalidNumberException ex) {
                    System.err.println("Invalid number format when setting quantity: " + ex.getMessage());
                }
                break;
            }
        }
        
        boolean itemFound = false;
        String itemName = "";
        for (Item item : itemList) {
            if (item.getItemID().equalsIgnoreCase(newItemID)) {
                itemFound = true;
                itemName = item.getItemName();

                if (newQuantitySold > item.getQuantity()) {
                    throw new IllegalArgumentException("Not enough stock for the new quantity.");
                }

                try {
                    item.setQuantity(item.getQuantity() - newQuantitySold);
                } catch (InvalidNumberException ex) {
                    System.err.println("Invalid number format when setting quantity: " + ex.getMessage());
                }
                item.setLatestUpdateDate(newDate);
                break;
            }
        }
        
        if (!itemFound) {
            throw new IllegalArgumentException("Item ID not found.");
        }
        originalSaleEn.itemID = newItemID;
        originalSaleEn.itemName = itemName;
        originalSaleEn.quantitySold = newQuantitySold;
        originalSaleEn.date = newDate;
        originalSaleEn.salesManagerID = salesmanagerID;

        saveEditDeleteToFile(salesList, salesEntryFilePath);
        FileHandler.smItemSaveEditDeleteToFile(itemList, itemFilePath);
    }
    
    public static void deleteSale(String salesentryID, String itemFilePath, String salesEntryFilePath) {
        ArrayList<DailySalesEntry> salesList = loadFromFile(salesEntryFilePath);
        ArrayList<Item> itemList = FileHandler.loadItem(itemFilePath);

        DailySalesEntry toDelete = null;
        for (DailySalesEntry sale : salesList) {
            if (sale.getSalesEntryID().equalsIgnoreCase(salesentryID)) {
                toDelete = sale;
                break;
            }
        }

        if (toDelete == null) {
            throw new IllegalArgumentException("Sales Entry ID not found.");
        }
        for (Item item : itemList) {
            if (item.getItemID().equalsIgnoreCase(toDelete.getItemID())) {
                try {
                    item.setQuantity(item.getQuantity() + toDelete.getQuantitySold());
                } catch (InvalidNumberException ex) {
                    System.err.println("Invalid number format when setting quantity: " + ex.getMessage());
                }
                break;
            }
        }
        salesList.remove(toDelete);
        saveEditDeleteToFile(salesList, salesEntryFilePath);
        FileHandler.smItemSaveEditDeleteToFile(itemList, itemFilePath);
    }
}