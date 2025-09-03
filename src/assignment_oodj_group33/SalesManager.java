/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;

public class SalesManager extends User{
    public SalesManager(String userId, String password) {
        super(userId, password, "Sales Manager");
    }
    
    @Override
    public void openDashboard(){
        new SalesManagerMenuGUI(this).setVisible(true);
    }
}
