/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class FileHandler {

//Below is Finance Manager File I/O Usage
    
    // Read all purchase orders from file
    public static List<PurchaseOrder> readPurchaseOrders() {
        List<PurchaseOrder> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("purchase_order.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 12) {
                    try {
                        String poId = parts[0].trim();
                        String prId = parts[1].trim();
                        String itemId = parts[2].trim();
                        String itemName = parts[3].trim();
                        int quantity = Integer.parseInt(parts[4].trim());
                        String supplierId = parts[5].trim();
                        double unitPrice = Double.parseDouble(parts[6].trim());
                        double totalAmount = Double.parseDouble(parts[7].trim());
                        String purchaseManager = parts[8].trim();
                        String financeManager = parts[9].trim();
                        String status = parts[10].trim();
                        int receivedQty = Integer.parseInt(parts[11].trim());

                        orders.add(new PurchaseOrder(poId, prId, itemId, itemName, quantity,
                            supplierId, unitPrice, totalAmount, purchaseManager, financeManager, status, receivedQty));
                    } catch (NumberFormatException e) {
                        System.err.println("Number format error: " + line);
                    }
                } else {
                    System.err.println("Invalid line (not 12 fields): " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading purchase_orders.txt: " + e.getMessage());
        }
        return orders;
    }
    
    
    // Write all purchase orders to file
    public static void writePurchaseOrders(List<PurchaseOrder> orders) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("purchase_order.txt"))){
        for (PurchaseOrder order : orders) {
            writer.write(order.toFileString());
            writer.newLine();
            }
        } catch (java.io.IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
    
    // Read Supplier from file
    public static List<Supplier> readSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("supplier.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String itemId = parts[2].trim();
                    double unitPrice = Double.parseDouble(parts[3].trim());
                    suppliers.add(new Supplier(id, name, itemId, unitPrice));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading supplier.txt: " + e.getMessage());
        }
        return suppliers;
    }
    
    //Read Purchase Requisition from file
    public static List<PurchaseRequisition> readPurchaseRequisitions() {
        List<PurchaseRequisition> requisitions = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader("purchase_requisitions.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String prId = parts[0].trim();
                    String itemId = parts[1].trim();
                    String itemName = parts[2].trim();
                    int quantity = Integer.parseInt(parts[3].trim());
                    LocalDate requireDate = LocalDate.parse(parts[4].trim(), formatter);
                    String supplierId = parts[5].trim();
                    String raisedBySalesManager = parts[6].trim();

                    PurchaseRequisition pr = new PurchaseRequisition(prId, itemId, itemName, quantity, requireDate, supplierId, raisedBySalesManager);
                    requisitions.add(pr);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading purchase requisitions: " + e.getMessage());
        }

        return requisitions;
    }
    //read from budget file
    public static List<String> readBudgetLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("budget.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }
    // write to budget file
    public static void writeBudgetLines(List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("budget.txt"))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
    
    
 //Login and Admin File I/O Usage
    
    // Read User id and Pasword from file
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("user_accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String userId = parts[0];
                    String password = parts[1];
                    String role = parts[2];   
                    User user = null;

                    switch (role) {
                        case "Finance Manager":
                            user = new FinanceManager(userId, password);
                            break;
                        case "Purchase Manager":
                            user = new PurchaseManager(userId, password);
                            break;
                        case "Inventory Manager":
                            user = new InventoryManager(userId, password);
                            break;
                        case "Sales Manager":
                            user = new SalesManager(userId, password);
                            break;
                        case "Administrator":
                            user = new Administrator(userId, password);
                            break;
                        default:
                            System.err.println("Unknown role: " + role);
                            break;
                    }

                    if (user != null) {
                        users.add(user);
                    }
                }
                    
                }
            
        }catch (IOException e){
            System.err.println("Error reading User Account: " + e.getMessage());
        }
        
        return users;
    }

    
    
//Below is Sales Manager file I/O Usage
    
    
    //read item from file
    public static ArrayList<Item> loadItem(String fileName) {
        ArrayList<Item> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String itemID = parts[0];
                    String itemName = parts[1];
                    String supplierID = parts[2];
                    int quantity = Integer.parseInt(parts[3]);
                    double price = Double.parseDouble(parts[4]);
                    String lastupdated = parts[5];
                    String status = parts[6];


                    list.add(new Item(itemID, itemName, supplierID, quantity, price , lastupdated, status));
                } 
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            }
        return list;
        }
    //save changes to item file
    public static void smItemSaveAddToFile(Item IM, String fileName){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))){
            bw.write(IM.toString());
            bw.newLine();
        } catch (IOException e){
            System.out.println("Error Writing to file: "+ e.getMessage());
        }
    }
    //save edit to item file
    public static void smItemSaveEditDeleteToFile(ArrayList<Item> list, String fileName) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
        for (Item IM : list) {
            bw.write(IM.toString());
            bw.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    //add item to file
    public static void addItem(Item newItem, String fileName) throws IllegalArgumentException {
        ArrayList<Item> list = loadItem(fileName);
        for (Item item : list) {
            if (item.getItemID().equalsIgnoreCase(newItem.getItemID())) {
                throw new IllegalArgumentException("Item ID already exists.");
            }
        }
        smItemSaveAddToFile(newItem, fileName);
    }
    //edit item from item file
    public static void editItem(int index, Item updatedItem, String fileName) throws IllegalArgumentException {
        ArrayList<Item> list = loadItem(fileName);
        if (index < 0 || index >= list.size()) {
            throw new IllegalArgumentException("Invalid item index.");
        }
        list.set(index, updatedItem);
        smItemSaveEditDeleteToFile(list, fileName);
    }
    //delete item from item file
    public static void deleteItem(int index, String fileName) throws IllegalArgumentException {
        ArrayList<Item> list = loadItem(fileName);
        if (index < 0 || index >= list.size()) {
            throw new IllegalArgumentException("Invalid item index.");
        }
        list.remove(index);
        smItemSaveEditDeleteToFile(list, fileName);
    }
    
        
    public static ArrayList<PurchaseOrder> loadPOFile(String fileName) {
        ArrayList<PurchaseOrder> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 12) {
                    String poID = parts[0];
                    String prID = parts[1];
                    String itemID = parts[2];
                    String itemName = parts[3];
                    int quantity = Integer.parseInt(parts[4]);
                    String supplierID = parts[5];
                    double unitprice = Double.parseDouble(parts[6]);
                    double totalprice = Double.parseDouble(parts[7]);
                    String purchasemanagerID = parts[8];
                    String financemanagerID = parts[9];
                    String status = parts[10];
                    int receivedQty = Integer.parseInt(parts[11]);

                    list.add(new PurchaseOrder(poID, prID, itemID, itemName, quantity, supplierID, 
                            unitprice, totalprice, purchasemanagerID, financemanagerID, status, receivedQty));
                } 
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            }
        return list;
        }
    
    public static ArrayList<PurchaseOrder> Search(String fileName, String poIDKeyword, String prIDKeyword) {
        ArrayList<PurchaseOrder> allOrders = loadPOFile(fileName);
        ArrayList<PurchaseOrder> filteredOrders = new ArrayList<>();
        for (PurchaseOrder order : allOrders) {
            boolean matchPO = poIDKeyword.isEmpty() || order.getPoId().toLowerCase().contains(poIDKeyword.toLowerCase());
            boolean matchPR = prIDKeyword.isEmpty() || order.getPrId().toLowerCase().contains(prIDKeyword.toLowerCase());
            if (matchPO && matchPR) {
                filteredOrders.add(order);
            }
        }

        if (filteredOrders.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No matching Purchase Order or Purchase Requisition found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        }
        return filteredOrders;
        }
    
    public static ArrayList<PurchaseRequisition> loadPRFile(String fileName) {
        ArrayList<PurchaseRequisition> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String prID = parts[0];
                    String itemID = parts[1];
                    String itemName = parts[2];
                    int quantity = Integer.parseInt(parts[3]);
                    LocalDate requireDate = LocalDate.parse(parts[4].trim());
                    String supplierID = parts[5];
                    String salesmanagerID = parts[6];

                    list.add(new PurchaseRequisition(prID, itemID, itemName, quantity, requireDate, supplierID, salesmanagerID));
                } 
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            }
        return list;
        }
    
    public static void smPRSaveAddToFile(PurchaseRequisition pr, String fileName){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))){
            bw.newLine();
            bw.write(pr.getPRDetails());
        } catch (IOException e){
            System.out.println("Error Writing to file: "+ e.getMessage());
        }
    }
    
    public static void smPRsaveEditDeleteToFile(ArrayList<PurchaseRequisition> list, String fileName) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
        for (PurchaseRequisition pr : list) {
            bw.write(pr.getPRDetails());
            bw.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
   public static ArrayList<Supplier> loadSupFile(String fileName) {
        ArrayList<Supplier> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String supplierID = parts[0];
                    String itemID = parts[1];
                    String itemName = parts[2];
                    double unitPrice = Double.parseDouble(parts[3]);

                    list.add(new Supplier(supplierID, itemID,itemName, unitPrice));
                } 
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            }
        return list;
        }

    public static void smSupSaveAddToFile(Supplier SM, String fileName){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))){
            bw.newLine();
            bw.write(SM.getSupplierDetails());
        } catch (IOException e){
            System.out.println("Error Writing to file: "+ e.getMessage());
        }
    }
    
    public static void smSupSaveEditDeleteToFile(ArrayList<Supplier> list, String fileName) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
        for (Supplier SM : list) {
            bw.write(SM.getSupplierDetails());
            bw.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public static boolean addNewSupplier(
        String supplierID,
        String itemID,
        double unitPrice,
        String itemFilePath,
        String supplierFilePath
    ) {
        ArrayList<Supplier> existingSuppliers = loadSupFile(supplierFilePath);

        for (Supplier item : existingSuppliers) {
            if (item.getItemId().equalsIgnoreCase(itemID) && item.getSupplierId().equalsIgnoreCase(supplierID)) {
                throw new IllegalArgumentException("The supplier with the same item ID is already exist. Please enter a new item ID or new supplier ID.");
            }
        }

        ArrayList<Item> itemList = FileHandler.loadItem(itemFilePath);

        boolean itemFound = false;
        String foundItemName = "";

        for (Item item : itemList) {
            if (item.getItemID().equalsIgnoreCase(itemID)) {
                itemFound = true;
                foundItemName = item.getItemName();

                item.setSupplierID(supplierID);
                try {
                    item.setPrice(unitPrice);
                } catch (InvalidNumberException ex) {
                    System.err.println("Invalid number format when setting quantity: " + ex.getMessage());
                }
                break;
            }
        }

        if (!itemFound) {
            throw new IllegalArgumentException("Item ID not found in item.txt.");
        }

        FileHandler.smItemSaveEditDeleteToFile(itemList, itemFilePath);
        Supplier supplier = new Supplier(supplierID, itemID, foundItemName, unitPrice);
        smSupSaveAddToFile(supplier, supplierFilePath);
        return true;
    }
    
    public static void editSupplier(
        String supplierID,
        String newItemID,
        String newItemName,
        double newUnitPrice,
        String itemFilePath,
        String supplierFilePath
    ) {
        ArrayList<Supplier> supplierList = loadSupFile(supplierFilePath);
        ArrayList<Item> itemList = FileHandler.loadItem(itemFilePath);

        Supplier existingSupplier = null;
        for (Supplier supplier : supplierList) {
            if (supplier.getSupplierId().equalsIgnoreCase(supplierID) &&
                supplier.getItemId().equalsIgnoreCase(newItemID)) {
                existingSupplier = supplier;
                
                break;
            }
        }

        if (existingSupplier == null) {
            throw new IllegalArgumentException("Supplier with given Supplier ID and Item ID not found.");
        }

        boolean itemFound = false;
        for (Item item : itemList) {
            if (item.getItemID().equalsIgnoreCase(newItemID)) {
                item.setSupplierID(supplierID);
                try {
                    item.setPrice(newUnitPrice);
                } catch (InvalidNumberException ex) {
                    System.err.println("Invalid number format when setting quantity: " + ex.getMessage());
                }
                item.setLatestUpdateDate(java.time.LocalDate.now().toString());
                itemFound = true;
                break;
            }
        }
        
        existingSupplier.setSupplierId(supplierID);
        existingSupplier.setItemId(newItemID);
        existingSupplier.setItemName(newItemName);
        existingSupplier.setUnitPrice(newUnitPrice);

        if (!itemFound) {
            throw new IllegalArgumentException("Item ID not found.");
        }

        smSupSaveEditDeleteToFile(supplierList, supplierFilePath);
        FileHandler.smItemSaveEditDeleteToFile(itemList, itemFilePath);
    }
    
    public static void deleteSupplier(
        String supplierID,
        String itemID,
        String itemFilePath,
        String supplierFilePath
    ) {
        ArrayList<Supplier> supplierList = loadSupFile(supplierFilePath);
        ArrayList<Item> itemList = FileHandler.loadItem(itemFilePath);

        Supplier targetSupplier = null;
        for (Supplier supplier : supplierList) {
            if (supplier.getSupplierId().equalsIgnoreCase(supplierID) && supplier.getItemId().equalsIgnoreCase(itemID)) {
                targetSupplier = supplier;
                break;
            }
        }

        if (targetSupplier == null) {
            throw new IllegalArgumentException("Supplier ID not found.");
        }
        
        supplierList.remove(targetSupplier);
        smSupSaveEditDeleteToFile(supplierList, supplierFilePath);
        FileHandler.smItemSaveEditDeleteToFile(itemList, itemFilePath);
    }    
    

// Below is Purchase Manger file I/O usage   


    public static List<PurchaseOrder> loadFromPOFile(String filename) {
        List<PurchaseOrder> orders = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                lineNum++;
                String[] data = line.split(",");
                if (data.length >= 12) {
                    try {
                        PurchaseOrder order = new PurchaseOrder(
                                data[0], data[1], data[2], data[3],
                                Integer.parseInt(data[4]), data[5],Double.parseDouble(data[6]), Double.parseDouble(data[7]),
                                 data[8], data[9], data[10],Integer.parseInt(data[11])
                        );
                        orders.add(order);
                    } catch (NumberFormatException nfe) {
                        System.err.println("Format error in line " + lineNum + ": " + line);
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error loading purchase orders: " + ex.getMessage());
        }
        return orders;
    }

    public static void fillTableWithOrders(JTable table, String filename) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<PurchaseOrder> orders = loadFromPOFile(filename);
        for (PurchaseOrder order : orders) {
            model.addRow(new Object[]{
                order.getPoId(),
                order.getItemId(),
                order.getItemName(),
                order.getQuantity(),
                order.getTotalAmount(),
                order.getSupplierId(),
                order.getPurchaseManager(),
                order.getFinanceManager(),
                order.getStatus()
            });
        }
    }

    public static List<String> loadRequisitionIds(String filename) {
        List<String> ids = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                lineNum++;
                String[] data = line.split(",");
                if (data.length > 0 && !data[0].trim().isEmpty()) {
                    ids.add(data[0].trim());
                } else {
                    System.err.println("Empty or bad line in requisitions at line: " + lineNum);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error loading requisition IDs: " + ex.getMessage());
        }
        return ids;
    }

    public static List<String> loadOrderIds(String filename) {
        List<String> ids = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0) {
                    ids.add(data[0]);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error loading order IDs: " + ex.getMessage());
        }
        return ids;
    }

    public static String[] findOrderById(String filename, String orderId) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 12 && data[0].equals(orderId)) {
                    return data;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading order: " + ex.getMessage());
        }
        return null;
    }

    public static boolean deleteOrder(String filename, String orderId) {
        File file = new File(filename);
        List<String> lines = new ArrayList<>();
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 12 && data[0].equals(orderId)) {
                    found = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + ex.getMessage());
            return false;
        }
        if (!found) {
            return false;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error writing file: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public static boolean updateOrder(String filename, String orderId, int quantity, double unitPrice, double totalPrice, String fmId, String status) {
        File file = new File(filename);
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 12 && data[0].equals(orderId)) {
                    data[4] = String.valueOf(quantity);
                    data[6] = String.valueOf(unitPrice);
                    data[7] = String.valueOf(totalPrice);
                    data[9] = fmId;
                    data[10] = status;
                    String newLine = String.join(",", data);
                    lines.add(newLine);
                    found = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading purchase_order.txt: " + ex.getMessage());
            return false;
        }

        if (!found) {
            return false;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving purchase_order.txt: " + ex.getMessage());
            return false;
        }
        return true;
    }   
    
    public static void loadRequisitionsToTable(JTable table, String filename, java.awt.Component parent) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 6) { 
                    String reqId = data[0].trim();
                    String itemId = data[1].trim();
                    String itemName = data[2].trim();
                    double unitPrice = Double.parseDouble(data[3].trim());
                    String date = data[4].trim();
                    String supplierId = data[5].trim();
                    model.addRow(new Object[]{reqId, itemId, itemName, unitPrice, date, supplierId});
                }
            }
        } catch (IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(parent, "Error loading requisitions: " + ex.getMessage());
        }
    }
    public static void loadItemsToTable(JTable table, String filename, java.awt.Component parent) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    String itemId = data[0].trim();
                    String itemName = data[1].trim();
                    String supplierId = data[2].trim();
                    int quantity = Integer.parseInt(data[3].trim());
                    float price = Float.parseFloat(data[4].trim());
                    String date = data[5].trim();
                    String status = data[6].trim();
                    model.addRow(new Object[]{itemId, itemName, supplierId, quantity, price, date, status});
                }
            }
        } catch (IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(parent, "Error loading items: " + ex.getMessage());
        }
    }
    public static void fillTableWithOrders(javax.swing.JTable table, String filename, java.awt.Component parent) {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 12) {
                    model.addRow(new Object[]{
                        data[0], 
                        data[1], 
                        data[2], 
                        data[3], 
                        Integer.valueOf(data[4].trim()), 
                        Double.valueOf(data[6].trim()), 
                        Double.valueOf(data[7].trim()), 
                        data[10] 
                    });
                }
            }
        }   catch (IOException | NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(parent, "Error loading purchase orders: " + ex.getMessage());
        }
    }   
    public static void loadSuppliersToTable(JTable table, String filename, java.awt.Component parent) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String supplierId = data[0].trim();
                    String itemId = data[1].trim();
                    String itemName = data[2].trim();
                    double unitPrice = Double.parseDouble(data[3].trim());
                    model.addRow(new Object[]{supplierId, itemId, itemName, unitPrice});
                }
            }
        } catch (IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(parent, "Error loading suppliers: " + ex.getMessage());
        }
    }
    
    
//Below is Inventory Manager File I/O Usage    
    
    
    public static List<Item> imloadItems() {
        List<Item> items = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("item.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 7) {
                String itemID = parts[0].trim();
                String itemName = parts[1].trim();
                String supplierID = parts[2].trim();
                int quantity = Integer.parseInt(parts[3].trim());
                double price = Double.parseDouble(parts[4].trim());
                String updateDate = parts[5].trim();
                String status = parts[6].trim();

                Item item = new Item(itemID, itemName, supplierID, quantity, price, updateDate, status);
                items.add(item);
            }
            else{
                System.err.println("Invalid line (Not 7 fields)" + line);
            }
        }
    } catch (IOException | NumberFormatException e) {
        System.out.println("Error reading item.txt: " + e.getMessage());
    }

    return items;
    }
    
    public static void updateItem(List<Item> items) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("item.txt"))) {
            for (Item item : items) {
                pw.println(item.toFileString());
            }
        } catch (IOException e) {
            System.err.println("Error writing to item.txt: " + e.getMessage());
        }
    }

    
    public static List<PurchaseOrder> loadPurchaseOrderDetails() {
        List<PurchaseOrder> order = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("purchase_order.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 12) {
                    String poID = parts[0];
                    String prID = parts[1];
                    String itemID = parts[2];
                    String itemName = parts[3];
                    int purchasedQuantity = Integer.parseInt(parts[4]);
                    String supplierID = parts[5];
                    double unitPrice = Double.parseDouble(parts[6]);
                    double totalPrice = Double.parseDouble(parts[7]);
                    String pmID = parts[8];
                    String fmID = parts[9];
                    String status = parts[10];
                    int receivedQuantity = Integer.parseInt(parts[11]);

                    order.add(new PurchaseOrder(poID, prID, itemID, itemName, purchasedQuantity,
                            supplierID, unitPrice,totalPrice, pmID, fmID, status, receivedQuantity));
                } else {
                    System.err.println("Invalid line (Not 11 fields): " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading purchase_order.txt: " + e.getMessage());
        }
        return order;
    }
    
    public static void updatePurchaseOrder(List<PurchaseOrder> updatedOrders){
        try (PrintWriter writer = new PrintWriter("purchase_order.txt")) {
            for (PurchaseOrder order : updatedOrders) {
                writer.println(order.toFileString());
            }
        } catch (IOException e) {
            System.err.println("Failed to update purchase_order.txt: " + e.getMessage());
        }
    }
    
    public static List<IMStockReport> loadExistingStockReports() {
        List<IMStockReport> reports = new ArrayList<>();
        File folder = new File("Stock Report");
        if (!folder.exists()) folder.mkdir();

        File[] files = folder.listFiles((dir, name) -> name.matches("SR\\d+\\.csv"));
        if (files != null) {
            for (File file : files) {
                String reportID = file.getName().replace(".csv", "");
                reports.add(new IMStockReport(reportID, "", "", "", new ArrayList<>()));
            }
        }
        return reports;
    }
    
    public static void saveStockReport(IMStockReport report) {
        String folderPath = "Stock Report";
        File folder = new File(folderPath);
        if (!folder.exists()) folder.mkdir();

        String filename = folderPath + "\\" + report.getReportID() + ".csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.print(report.toFileString());
        } catch (IOException e) {
            System.err.println("Failed to save stock report: " + e.getMessage());
        }
    }
    
}