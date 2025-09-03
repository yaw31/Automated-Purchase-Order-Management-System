/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;

import java.util.List;
import javax.swing.table.DefaultTableModel;


public abstract class FMTableLoader {
    protected DefaultTableModel model;
    protected List<PurchaseOrder> purchaseOrders;

    public FMTableLoader(DefaultTableModel model) {
        this.model = model;
        this.purchaseOrders = FileHandler.readPurchaseOrders(); 
    }

    public abstract void loadTable();  
     
}

