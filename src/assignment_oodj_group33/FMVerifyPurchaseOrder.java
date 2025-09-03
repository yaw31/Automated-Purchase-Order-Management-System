/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_oodj_group33;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class FMVerifyPurchaseOrder extends FMTableLoader{
    private List<Supplier> suppliers;
    
    
    public FMVerifyPurchaseOrder(DefaultTableModel model) {
        super(model);
        
    }
    
    @Override
     public void loadTable(){
        purchaseOrders = FileHandler.readPurchaseOrders();
        model.setRowCount(0);
        for (PurchaseOrder order : purchaseOrders) {
            Object[] rowData = {
                order.getPoId(),
                order.getPrId(),
                order.getItemId(),
                order.getItemName(),
                order.getQuantity(),
                order.getSupplierId(),
                order.getUnitPrice(),
                order.getTotalAmount(),
                order.getPurchaseManager(),
                order.getFinanceManager(),
                order.getStatus(),
                
            };
            model.addRow(rowData);
        }
    }
    
    private void loadTable(List<PurchaseOrder> list){
        model.setRowCount(0);
        for (PurchaseOrder order : list) {
            Object[] rowData = {
                order.getPoId(),
                order.getPrId(),
                order.getItemId(),
                order.getItemName(),
                order.getQuantity(),
                order.getSupplierId(),
                order.getUnitPrice(),
                order.getTotalAmount(),
                order.getPurchaseManager(),
                order.getFinanceManager(),
                order.getStatus(),
                
            };
            model.addRow(rowData);
        }
    }
    public boolean loadTableByPOId(String POId) {
        List<PurchaseOrder> filtered = new ArrayList<>();
        for (PurchaseOrder po : purchaseOrders) {
            if (po.getPoId().equalsIgnoreCase(POId)) {
                filtered.add(po);
            }
        }
        loadTable(filtered);
        return !filtered.isEmpty();
    }

 
    
    public void loadSupplierCombo(JTable table, JComboBox<String> comboBox) {
        int selectedRow = table.getSelectedRow();

        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String selectedItemId = model.getValueAt(selectedRow, 2).toString(); 

            suppliers = FileHandler.readSuppliers();
            comboBox.removeAllItems();

            for (Supplier s : suppliers) {
                if (s.getItemId().equals(selectedItemId)) {
                    comboBox.addItem(s.getSupplierId());
                }
            }
        }
    }

    public void approveSelectedOrder(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null,
                "Please select an order to approve.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String currentStatus = model.getValueAt(selectedRow, 10).toString(); //status

        if (!"Pending".equalsIgnoreCase(currentStatus)) {
            JOptionPane.showMessageDialog(null,
                "This purchase order is already approved or not eligible for approval.",
                "Already Approved",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
            "Are you sure you want to approve this purchase order?",
            "Confirm",
            JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return; 
        }

        // Update table
        model.setValueAt("Approved", selectedRow, 10); // status

        // Update in memory and save
        purchaseOrders = FileHandler.readPurchaseOrders();
        String poId = model.getValueAt(selectedRow, 0).toString(); 
        for (PurchaseOrder order : purchaseOrders) {
            if (order.getPoId().equals(poId)) {
                order.setStatus("Approved");
                break;
            }
        }

        FileHandler.writePurchaseOrders(purchaseOrders);

        JOptionPane.showMessageDialog(null,
            "Order " + poId + " has been approved.",
            "Order Approved",
            JOptionPane.INFORMATION_MESSAGE);
    }

    public void rejectSelectedOrder(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null,
                "Please select an order to reject.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String currentStatus = model.getValueAt(selectedRow, 10).toString();

        if (!"Pending".equalsIgnoreCase(currentStatus)) {
            JOptionPane.showMessageDialog(null,
                "This purchase order is already approved or not eligible for reject.",
                "Already Approved",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
            "Are you sure you want to reject this purchase order?",
            "Confirm",
            JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return; 
        }

        // Update table
        model.setValueAt("Rejected", selectedRow, 10); //status

        // Update in memory and save
        purchaseOrders = FileHandler.readPurchaseOrders();
        String poId = model.getValueAt(selectedRow, 0).toString(); 

        for (PurchaseOrder order : purchaseOrders) {
            if (order.getPoId().equals(poId)) {
                order.setStatus("Rejected");
                break;
            }
        }

        FileHandler.writePurchaseOrders(purchaseOrders);

        JOptionPane.showMessageDialog(null,
            "Order " + poId + " has been rejected.",
            "Order Approved",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void editSelectedOrder(JTable table, JTextField quantityField, JComboBox<String> comboBox) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null,
                "Please select an order to edit.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String newQuantityStr = quantityField.getText().trim();
        String newSupplierId = (String) comboBox.getSelectedItem();

        try {
            int newQuantity = Integer.parseInt(newQuantityStr);
            

            int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to save these changes?",
                "Confirm Edit",
                JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) return;

            // Get unit price from supplier list
            double unitPrice = 0.0;
            suppliers = FileHandler.readSuppliers();
            for (Supplier supplier : suppliers) {
                if (supplier.getSupplierId().equals(newSupplierId)) {
                    unitPrice = supplier.getUnitPrice();
                    break;
                }
            }

            double newTotalAmount = newQuantity * unitPrice;

            

            // Update in memory and file
            purchaseOrders = FileHandler.readPurchaseOrders();
            String poId = model.getValueAt(selectedRow, 0).toString(); 

            for (PurchaseOrder order : purchaseOrders) {
                if (order.getPoId().equals(poId)) {
                    order.setQuantity(newQuantity);
                    order.setSupplierId(newSupplierId);
                    order.setUnitPrice(unitPrice);
                    order.setTotalAmount(newTotalAmount);
                    break;
                }
            }
            // Update JTable
            model.setValueAt(newQuantity, selectedRow, 4);      // Quantity
            model.setValueAt(newSupplierId, selectedRow, 5);    // Supplier ID
            model.setValueAt(unitPrice, selectedRow, 6);        // Unit Price
            model.setValueAt(newTotalAmount, selectedRow, 7);   // Total Amount
            
            
            FileHandler.writePurchaseOrders(purchaseOrders);
            JOptionPane.showMessageDialog(null, "Purchase Order updated successfully.");

        } catch (InvalidNumberException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid Quantity", JOptionPane.ERROR_MESSAGE);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a number.");
            
        }
    }
    //update unit price when select different supplier
    public void updateUnitPrice(JComboBox<String> comboBox, JLabel unitPriceLabel) {
        String selectedId = (String) comboBox.getSelectedItem();
        if (selectedId == null) return;

        suppliers = FileHandler.readSuppliers();

        for (Supplier supplier : suppliers) {
            if (supplier.getSupplierId().equals(selectedId)) {
                unitPriceLabel.setText("RM " + String.format("%.2f", supplier.getUnitPrice()));
                break;
            }
        }
    }

    
}


