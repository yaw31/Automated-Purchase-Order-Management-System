/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class IMViewPurchaseOrder extends IMTableLoader{
    public IMViewPurchaseOrder(DefaultTableModel model) {
        super(model);
    }
    
    public boolean loadTable(String poID) {
        List<PurchaseOrder> orderDetails = FileHandler.loadPurchaseOrderDetails();
        model.setRowCount(0);
        boolean found = false;

        for (PurchaseOrder order : orderDetails) {
            if (order.getPoId().equalsIgnoreCase(poID)) {
                model.addRow(new Object[] {
                    order.getItemId(),
                    order.getItemName(),
                    order.getSupplierId(),
                    order.getQuantity(),
                    order.getReceivedQuantity(),
                    order.getStatus()
                });
                found = true;
            }
        }
        return found;
    }
    
    public void updatePurchaseOrder(String poID, String itemID, int receivedQuantity) throws InvalidNumberException {
        List<PurchaseOrder> allOrders = FileHandler.loadPurchaseOrderDetails();
        boolean updated = false;

        for (PurchaseOrder order : allOrders) {
            if (order.getPoId().equalsIgnoreCase(poID) &&
                order.getItemId().equalsIgnoreCase(itemID) &&
                order.getStatus().equalsIgnoreCase("Approved")) {

                order.setReceivedQuantity(receivedQuantity);

                if (receivedQuantity == order.getQuantity()) {
                    order.setStatus("Received");
                } else {
                    order.setStatus("Error");
                }

                updated = true;
                break;
            }
        }

        if (updated) {
            FileHandler.updatePurchaseOrder(allOrders);
        } else {
            System.err.println("Error: No matching Purchase Order found or Purchase Order is not Approved.");
        }
    }
    
    public void updateItem(String itemID, int receivedQuantity) throws InvalidNumberException {
        List<Item> items = FileHandler.imloadItems();

        for (Item item : items) {
            if (item.getItemID().equalsIgnoreCase(itemID)) {
                try {
                    int newQuantity = item.getQuantity() + receivedQuantity;
                    item.setQuantity(newQuantity);
                    item.setLatestUpdateDate(java.time.LocalDate.now().toString());

                    if (item.getStatus().equalsIgnoreCase("Low-Stock") && newQuantity > 10) {
                        item.setStatus("Normal");
                    }

                    break;
                } catch (InvalidNumberException e) {
                    System.err.println("Quantity error: " + e.getMessage());
                }
            }
        }

        FileHandler.updateItem(items);
    }
}