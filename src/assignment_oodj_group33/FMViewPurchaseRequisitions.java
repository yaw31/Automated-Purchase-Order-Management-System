/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class FMViewPurchaseRequisitions extends FMTableLoader{
    private List<PurchaseRequisition> requisitions;

    public FMViewPurchaseRequisitions(DefaultTableModel model) {
        super(model);
        this.requisitions = FileHandler.readPurchaseRequisitions();
    }

    @Override
    public void loadTable() {
        model.setRowCount(0);
        for (PurchaseRequisition pr : requisitions) {
            model.addRow(new Object[] {
                pr.getPrId(), 
                pr.getItemId(), 
                pr.getItemName(),
                pr.getQuantity(), 
                pr.getRequireDate(),
                pr.getSupplierId(), 
                pr.getSalesManager()
            });
        }
    }
    
    private void loadTable(List<PurchaseRequisition> list) {
        model.setRowCount(0);
        for (PurchaseRequisition pr : list) {
            model.addRow(new Object[] {
                pr.getPrId(), 
                pr.getItemId(), 
                pr.getItemName(),
                pr.getQuantity(), 
                pr.getRequireDate(),
                pr.getSupplierId(), 
                pr.getSalesManager()
            });
        }
    }    
    
    public boolean loadTableByItemId(String itemId) {
        List<PurchaseRequisition> filtered = new ArrayList<>();
        for (PurchaseRequisition pr : requisitions) {
            if (pr.getItemId().equalsIgnoreCase(itemId)) {
                filtered.add(pr);
            }
        }
        loadTable(filtered);
        return !filtered.isEmpty();
    }

    
    public boolean loadTableBySupplierId(String supplierId) {
        List<PurchaseRequisition> filtered = new ArrayList<>();
        for (PurchaseRequisition pr : requisitions) {
            if (pr.getSupplierId().equalsIgnoreCase(supplierId)) {
                filtered.add(pr);
            }
        }
        loadTable(filtered);
        return !filtered.isEmpty(); // return true if the filtered is not empty
    }

    

    
}

