/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class FMSupplierPerformanceReport implements ReportGenerator{

    private List<PurchaseOrder> orders;
    private JTable table;
;

   //Constructor to get financial report detail
    public FMSupplierPerformanceReport(List<PurchaseOrder> orders, JTable table) {
        this.orders = orders;
        this.table = table;

    }
    
    @Override
    public void generateReport() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); 

        for (PurchaseOrder po : orders) {
            int orderedQty = po.getQuantity();
            int receivedQty = po.getReceivedQuantity();

            double fulfillmentRate = 0;
            String status;

            if (orderedQty > 0) {
                fulfillmentRate = (double) receivedQty / orderedQty * 100;
            }

            if (receivedQty == 0) {
                status = "Haven't Received";
            } else if (fulfillmentRate > 75.0) {
                status = "Reliable";
            } else {
                status = "Need Attention";
            }

            model.addRow(new Object[]{
                po.getSupplierId(),
                orderedQty,
                receivedQty,
                String.format("%.2f%%", fulfillmentRate),
                status
            });
        }
    }
    
    
}
