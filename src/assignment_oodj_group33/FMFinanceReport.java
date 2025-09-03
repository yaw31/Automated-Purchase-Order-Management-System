/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FMFinanceReport implements ReportGenerator {

    private List<PurchaseOrder> orders;
    private JTable table;
    private JLabel totalLabel;
    private JLabel pendingLabel;

   //Constructor to get financial report detail
    public FMFinanceReport(List<PurchaseOrder> orders, JTable table, JLabel totalLabel, JLabel pendingLabel) {
        this.orders = orders;
        this.table = table;
        this.totalLabel = totalLabel;
        this.pendingLabel = pendingLabel;
    }

    
    @Override
    public void generateReport() {
    
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        double totalPaid = 0;
        int pendingCount = 0;

        for (PurchaseOrder po : orders) {
            if ("Paid".equalsIgnoreCase(po.getStatus())) {
                model.addRow(new Object[]{
                    po.getPoId(), 
                    po.getSupplierId(), 
                    po.getItemName(), 
                    po.getItemId(),
                    String.format("RM %.2f", po.getTotalAmount()), 
                    po.getStatus()
                });
                totalPaid += po.getTotalAmount();
            } else {
                pendingCount++;
            }
        }

        totalLabel.setText(String.format("RM %.2f", totalPaid));
        pendingLabel.setText(String.valueOf(pendingCount));
    }
    
    public static void exportTableToCSV(JTable table, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            // Write column names
            for (int i = 0; i < model.getColumnCount(); i++) {
                writer.print(model.getColumnName(i));
                if (i < model.getColumnCount() - 1) writer.print(",");
            }
            writer.println();

            // Write rows
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    writer.print(model.getValueAt(row, col));
                    if (col < model.getColumnCount() - 1) writer.print(",");
                }
                writer.println();
            }

            JOptionPane.showMessageDialog(null, "CSV exported successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error exporting CSV: " + e.getMessage());
        }
    }
}



