/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package assignment_oodj_group33;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class OWSBLoginGUI extends javax.swing.JFrame {

    public OWSBLoginGUI() {
        initComponents();
        lblForgotPassword.setText("<HTML><U>Forgot Password?</U></HTML>");
    }
    
    private void validateUserIdField() {
        String userId = useridTextField.getText().trim();
        ValidationResult result = Validator.validateUserId(userId);
        if (!result.isValid()) {
            useridTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
            useridTextField.setToolTipText(result.getMessage());
        } else {
            useridTextField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            useridTextField.setToolTipText(null);
        }
    }

    private void validatePasswordField() {
        String password = new String(passwordTextField.getPassword());
        ValidationResult result = Validator.validatePassword(password);
        if (!result.isValid()) {
            passwordTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
            passwordTextField.setToolTipText(result.getMessage());
        } else {
            passwordTextField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            passwordTextField.setToolTipText(null);
        }
    }

    private void handleForgotPassword() {
        String userId = JOptionPane.showInputDialog(this, "Enter your User ID:");

        if (userId == null || userId.trim().isEmpty()) {
            return; 
        }
        List<User> users = FileHandler.loadUsers();
        for (User user : users) {
            if (user.getUserId().equalsIgnoreCase(userId.trim())) {
                JOptionPane.showMessageDialog(this, 
                    "Your password is: " + user.getPassword(),
                    "Password Recovery",
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
            JOptionPane.showMessageDialog(this, 
                "User ID not found.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);

    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        useridTextField = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        cboxShowPass = new javax.swing.JCheckBox();
        passwordTextField = new javax.swing.JPasswordField();
        lblForgotPassword = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Welcome To Automated Purchase Order Management System (OWBS)");

        jLabel2.setText("User ID:");

        jLabel3.setText("Password:");

        useridTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                useridTextFieldFocusLost(evt);
            }
        });

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        cboxShowPass.setText("Show Password");
        cboxShowPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxShowPassActionPerformed(evt);
            }
        });

        passwordTextField.setEchoChar('\u2022');
        passwordTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordTextFieldFocusLost(evt);
            }
        });
        passwordTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextFieldActionPerformed(evt);
            }
        });

        lblForgotPassword.setText("Forgot Password");
        lblForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblForgotPasswordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblForgotPasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblForgotPasswordMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(btnLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblForgotPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(useridTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                    .addComponent(passwordTextField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboxShowPass)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 23, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(useridTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cboxShowPass))
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblForgotPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnExit))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        useridTextField.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        
        String userId = useridTextField.getText();
        String password = new String(passwordTextField.getPassword());
        
        // Validate user ID using the Validator class
        ValidationResult userIdValidation = Validator.validateUserId(userId);
        if (!userIdValidation.isValid()) {
            JOptionPane.showMessageDialog(OWSBLoginGUI.this, userIdValidation.getMessage(), "Invalid User ID", JOptionPane.ERROR_MESSAGE);
            useridTextField.setText("");          
            passwordTextField.setText("");
            return;
        }
        
        // Validate password using the Validator class
        ValidationResult passwordValidation = Validator.validatePassword(password);
        if (!passwordValidation.isValid()) {
            JOptionPane.showMessageDialog(OWSBLoginGUI.this, passwordValidation.getMessage(), "Invalid Password", JOptionPane.ERROR_MESSAGE);                    
            passwordTextField.setText("");           
            return;
        }
        
        try {
            User authenticatedUser = User.authenticate(userId, password);
            if (authenticatedUser != null) {
                authenticatedUser.openDashboard(); 
                dispose(); // Close login window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials", "Login Failed", JOptionPane.ERROR_MESSAGE);
                useridTextField.setText("");          
                passwordTextField.setText("");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading user accounts file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    
    private void passwordTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTextFieldActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void cboxShowPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxShowPassActionPerformed
         if (cboxShowPass.isSelected()) {
            // Show password as plain text
            passwordTextField.setEchoChar((char) 0);
        } else {
            // Hide password (use default bullet character)
            passwordTextField.setEchoChar('\u2022');
        }
    }//GEN-LAST:event_cboxShowPassActionPerformed

    private void useridTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_useridTextFieldFocusLost
        validateUserIdField();
    }//GEN-LAST:event_useridTextFieldFocusLost

    private void passwordTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordTextFieldFocusLost
        validatePasswordField();
    }//GEN-LAST:event_passwordTextFieldFocusLost

    private void lblForgotPasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgotPasswordMouseEntered
        lblForgotPassword.setForeground(Color.RED); 
    }//GEN-LAST:event_lblForgotPasswordMouseEntered

    private void lblForgotPasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgotPasswordMouseExited
        lblForgotPassword.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblForgotPasswordMouseExited

    private void lblForgotPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgotPasswordMouseClicked
        handleForgotPassword();
    }//GEN-LAST:event_lblForgotPasswordMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OWSBLoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OWSBLoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OWSBLoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OWSBLoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OWSBLoginGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox cboxShowPass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblForgotPassword;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JTextField useridTextField;
    // End of variables declaration//GEN-END:variables
}
