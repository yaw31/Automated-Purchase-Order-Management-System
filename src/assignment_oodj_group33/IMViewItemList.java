/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class IMViewItemList extends IMTableLoader {
    public IMViewItemList(DefaultTableModel model) {
        super(model);
    }

    public void loadTable() {
        List<Item> items = FileHandler.imloadItems();
        model.setRowCount(0);

        for (Item item : items) {
            model.addRow(new Object[] {
                item.getItemID(),
                item.getItemName(),
                item.getSupplierID(),
                item.getQuantity(),
                item.getPrice()
            });
        }
    }
}

