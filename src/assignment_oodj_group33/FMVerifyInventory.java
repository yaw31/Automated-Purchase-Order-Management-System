/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class FMVerifyInventory extends FMTableLoader{
    
    
    public FMVerifyInventory(DefaultTableModel model) {
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
                order.getStatus(),
                order.getReceivedQuantity(),
                order.getFinanceFlag()
            };
            model.addRow(rowData);
        }
    }
    
    
    public void loadOnlyReceivedOrders() {
        model.setRowCount(0);
        for (PurchaseOrder order : purchaseOrders) {
            if ("Received".equalsIgnoreCase(order.getStatus())) {
                model.addRow(new Object[]{
                    order.getPoId(),
                    order.getPrId(),
                    order.getItemId(),
                    order.getItemName(),
                    order.getQuantity(),
                    order.getSupplierId(),
                    order.getUnitPrice(),
                    order.getTotalAmount(),
                    order.getPurchaseManager(),
                    order.getStatus(),
                    order.getReceivedQuantity(),
                    order.getFinanceFlag()
                });
            }
        }
    }
    public void loadPaidData() {
        model.setRowCount(0);
        for (PurchaseOrder order : purchaseOrders) {
            if ("Paid".equalsIgnoreCase(order.getStatus())) {
                model.addRow(new Object[]{
                    order.getPoId(), 
                    order.getPrId(), 
                    order.getItemId(),
                    order.getItemName(), 
                    order.getQuantity(),
                    order.getSupplierId(), 
                    order.getUnitPrice(),
                    order.getTotalAmount(), 
                    order.getPurchaseManager(),
                    order.getStatus(),
                    order.getReceivedQuantity(),
                    order.getFinanceFlag()
                });
            }
        }
    }
    
    public boolean processPayment(int selectedRow, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null,
                "Please select a purchase order to process payment.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        String currentStatus = model.getValueAt(selectedRow, 9).toString();

        if ("Paid".equalsIgnoreCase(currentStatus)) {
            JOptionPane.showMessageDialog(null,
                "This purchase order has already been paid.",
                "Already Paid", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (!"Received".equalsIgnoreCase(currentStatus)) {
            JOptionPane.showMessageDialog(null,
                "Only Purchase Orders with status 'Received' can be processed for payment.",
                "Invalid Status", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
            "Are you sure you want to mark this purchase order as Paid?",
            "Confirm Payment", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return false;
        }

        // Update table display
        model.setValueAt("Paid", selectedRow, 9);// status

        // Update in memory + file
        purchaseOrders = FileHandler.readPurchaseOrders();
        String poId = model.getValueAt(selectedRow, 0).toString();

        for (PurchaseOrder order : purchaseOrders) {
            if (order.getPoId().equals(poId)) {
                order.setStatus("Paid");
                break;
            }
        }

        FileHandler.writePurchaseOrders(purchaseOrders);

        JOptionPane.showMessageDialog(null,
            "Payment processed successfully. Status updated to 'Paid'.",
            "Payment Complete", JOptionPane.INFORMATION_MESSAGE);

        return true;
    }
    
    
    
}
