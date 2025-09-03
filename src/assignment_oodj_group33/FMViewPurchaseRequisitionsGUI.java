/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package assignment_oodj_group33;

import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class FMViewPurchaseRequisitionsGUI extends javax.swing.JFrame {
    private DefaultTableModel model = new DefaultTableModel();
    private String[] columnName = {"PR Id", "Item Id", "Item Name", "Quantity", "Require Date", 
        "Supplier Id", "RaisedBySalesManager"};
    private List<PurchaseRequisition> purchaseRequisitions;
    private ButtonGroup filterGroup = new ButtonGroup();
    private User user;

    public FMViewPurchaseRequisitionsGUI(User user) {
        this.user = user;
        initComponents();
        filterGroup.add(itemIdRadio);
        filterGroup.add(supplierIdRadio);
        model.setColumnIdentifiers(columnName);
        loadData();
       
    }
    
    private void loadData() {
       FMViewPurchaseRequisitions loader = new FMViewPurchaseRequisitions(model); 
       loader.loadTable(); 
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePR = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        itemIdRadio = new javax.swing.JRadioButton();
        supplierIdRadio = new javax.swing.JRadioButton();
        searchField = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        tablePR.setModel(model);
        jScrollPane1.setViewportView(tablePR);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("View Purchase Requisitions");

        itemIdRadio.setText("Item Id");

        supplierIdRadio.setText("Supplier Id");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(itemIdRadio)
                        .addGap(65, 65, 65)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(supplierIdRadio)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSearch)
                        .addGap(46, 46, 46)
                        .addComponent(btnRefresh)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(201, 201, 201))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemIdRadio)
                    .addComponent(supplierIdRadio))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnRefresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchText = searchField.getText().trim();
        FMViewPurchaseRequisitions logic = new FMViewPurchaseRequisitions(model);

        // Check radio selection
        if (!itemIdRadio.isSelected() && !supplierIdRadio.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select a filter type (Item Id or Supplier Id).",
                    "Filter Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if text field is empty
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Search field cannot be empty.",
                    "Filter Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Format validation
        if (itemIdRadio.isSelected()) {
            if (!searchText.matches("ITEM\\d{3}")) {
                JOptionPane.showMessageDialog(this, "Invalid Item ID format. Use: ITEM001, ITEM002, etc.",
                        "Format Error", JOptionPane.ERROR_MESSAGE);
                searchField.setText(""); 
                return;
            }
        } else if (supplierIdRadio.isSelected()) {
            if (!searchText.matches("SUP\\d{3}")) {
                JOptionPane.showMessageDialog(this, "Invalid Supplier ID format. Use: SUP001, SUP002, etc.",
                        "Format Error", JOptionPane.ERROR_MESSAGE);
                searchField.setText(""); 
                return;
            }
        }  
                    
        boolean found;
        if (itemIdRadio.isSelected()) {
            found = logic.loadTableByItemId(searchText);
        } else {
            found = logic.loadTableBySupplierId(searchText);
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "No results found for: " + searchText, 
                    "No Match", JOptionPane.INFORMATION_MESSAGE);
        }
        searchField.setText(""); 
            
       
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadData();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        dispose(); 
        FinanceManagerMenuGUI Window = new FinanceManagerMenuGUI(this.user);
        Window.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

 

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JRadioButton itemIdRadio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchField;
    private javax.swing.JRadioButton supplierIdRadio;
    private javax.swing.JTable tablePR;
    // End of variables declaration//GEN-END:variables
}
