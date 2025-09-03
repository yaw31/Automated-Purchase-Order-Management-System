/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;

public class InventoryManager extends User{
    public InventoryManager(String userId, String password) {
        super(userId, password, "Inventory Manager");
    }
    
    @Override
    public void openDashboard(){
        new InventoryManagerMenuGUI(this).setVisible(true);
    }
}