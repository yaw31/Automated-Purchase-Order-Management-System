/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;

import java.util.*;
import javax.swing.table.DefaultTableModel;


public class IMGenerateStockReport extends IMTableLoader {
    public IMGenerateStockReport(DefaultTableModel model) {
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
    
    public String getNextReportID(List<IMStockReport> existingReports) {
        int max = 0;
        for (IMStockReport r : existingReports) {
            try {
                int num = Integer.parseInt(r.getReportID().replaceAll("[^\\d]", ""));
                if (num > max) max = num;
            } catch (NumberFormatException e) {
                System.err.println("Invalid Stock Report ID");
            }
        }
        return "SR" + (max + 1);
    }
    
    public void saveStockReport(String reportID, String reportType, String owner, String date, List<Item> items) {
        IMStockReport report = new IMStockReport(reportID, reportType, owner, date, items);
        FileHandler.saveStockReport(report);
    }
}
