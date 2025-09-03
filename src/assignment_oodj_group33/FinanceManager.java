/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;

public class FinanceManager extends User{
    public FinanceManager(String userId, String password) {
        super(userId, password, "Finance Manager");
    }
    
    @Override
    public void openDashboard(){
        new FinanceManagerMenuGUI(this).setVisible(true);
    }
}