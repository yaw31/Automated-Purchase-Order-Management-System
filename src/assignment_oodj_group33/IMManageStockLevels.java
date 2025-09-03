/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;
import java.time.LocalDate;
import java.util.*;
import javax.swing.table.DefaultTableModel;


public class IMManageStockLevels extends IMTableLoader{
    public IMManageStockLevels(DefaultTableModel model) {
        super(model);
    }
    
    public void loadTable() {
        List<Item> items = FileHandler.imloadItems();
        model.setRowCount(0);

        for (Item item : items) {
            boolean isLowStock = "Low-Stock".equalsIgnoreCase(item.getStatus());

            model.addRow(new Object[] {
                item.getItemID(),
                item.getItemName(),
                item.getSupplierID(),
                item.getQuantity(),
                isLowStock
            });
        }
    }
    
    public void updateStockItems(){
        List<Item> originalItems = FileHandler.imloadItems();
        Map<String, Item> itemMap = new HashMap<>();
        for (Item item : originalItems) {
            itemMap.put(item.getItemID(), item);
        }

        List<Item> updatedItems = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String itemId = model.getValueAt(i, 0).toString();
            String itemName = model.getValueAt(i, 1).toString();
            String supplierId = model.getValueAt(i, 2).toString();
            int quantity = Integer.parseInt(model.getValueAt(i, 3).toString());

            String status;
            if (quantity > 10) {
                status = "Normal";
            } else {
                status = "Low-Stock";
            }

            Item original = itemMap.get(itemId);
            double price = original.getPrice();
            String date = LocalDate.now().toString();

            Item updated = new Item(itemId, itemName, supplierId, quantity, price, date, status);
            updatedItems.add(updated);
        }

        FileHandler.updateItem(updatedItems);
    }
}
