
package assignment_oodj_group33;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class User {
    protected String userId;
    protected String password;
    protected String role;

    
    public User(String userId, String password, String role) {
        this.userId = userId;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }


    public String getRole() {
        return role;
    }

    
    public abstract void openDashboard();

    
    public static User authenticate(String userId, String password) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("user_accounts.txt"));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String fileUserId = parts[0].trim();
                String filePassword = parts[1].trim();
                String fileRole = parts[2].trim();
                

                if (fileUserId.equals(userId) && filePassword.equals(password)) {
                    switch (fileRole) {
                        case "Finance Manager":
                            return new FinanceManager(userId, password);
                        case "Sales Manager":
                            return new SalesManager(userId, password);
                        case "Administrator":
                            return new Administrator(userId, password);
                        case "Inventory Manager":
                            return new InventoryManager(userId, password);
                        case "Purchase Manager":
                            return new PurchaseManager(userId, password); 
                        default:
                         break;
                    }
                }
            }
        }
        return null;
    }
     
       
}


