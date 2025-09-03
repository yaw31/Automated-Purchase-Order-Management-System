/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Administrator extends User{
    public Administrator(String userId, String password) {
        super(userId, password, "Administrator");
    }
    
    public Administrator(){}
    
    @Override
    public void openDashboard() {
        new AdminMenuGUI(this).setVisible(true);
    }

    public void createUser(String userId, String password, String role) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("user_accounts.txt", true));
        writer.write(userId + "," + password + "," + role);
        writer.newLine();
        writer.close();
    }
    public boolean editUser(String userId, String newPassword, String newRole) throws IOException {
        List<String> updatedLines = new ArrayList<>();
        boolean updated = false;

        BufferedReader reader = new BufferedReader(new FileReader("user_accounts.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3 && parts[0].equals(userId)) {
                updatedLines.add(userId + "," + newPassword + "," + newRole);
                updated = true;
            } else {
                updatedLines.add(line);
            }
        }
        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter("user_accounts.txt"));
        for (String updatedLine : updatedLines) {
            writer.write(updatedLine);
            writer.newLine();
        }
        writer.close();
        return updated;
    }
    public boolean deleteUser(String userId) throws IOException {
        List<String> updatedLines = new ArrayList<>();
        boolean deleted = false;

        BufferedReader reader = new BufferedReader(new FileReader("user_accounts.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3 && parts[0].equals(userId)) {
                deleted = true; 
            } else {
                updatedLines.add(line);
            }
        }
        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter("user_accounts.txt"));
        for (String updatedLine : updatedLines) {
            writer.write(updatedLine);
            writer.newLine();
        }
        writer.close();
        return deleted;
    }

    
}
