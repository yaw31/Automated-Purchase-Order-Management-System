/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;

public class Validator {
    
    /**
     * User ID Rules:
     * - Must be exactly 4 characters long
     * - First character must be an uppercase letter
     * - Next 3 characters must be digits
     */
    public static ValidationResult validateUserId(String userId) {
        
        if (userId.length() != 4) {
            return new ValidationResult(false, "User ID must be exactly 4 characters long");
        }
        
        
        if (userId.charAt(0) < 'A' || userId.charAt(0) > 'Z') {
            return new ValidationResult(false, "User ID must start with an uppercase letter");
        }
        
        for (int i = 1; i < 4; i++) {
            if (userId.charAt(i) < '0' || userId.charAt(i) > '9') {
                return new ValidationResult(false, "User ID must have 3 digits following the uppercase letter");
            }
        }
        
        return new ValidationResult(true, "Valid User ID");
    }
    
    /**
     * Password Rules:
     * - Must be between 6 to 10 characters long
     * - Must contain at least one lowercase letter
     * - Must contain at least one uppercase letter
     * - Must contain at least one digit
     * - Must contain at least one special symbol ($, @, #, !)
     * - Must only contain allowed characters (letters, digits, and the allowed symbols)
     */
    public static ValidationResult validatePassword(String password) {
        // Password must be between 6 to 10 characters long
        if (password.length() < 6 || password.length() > 10) {
            return new ValidationResult(false, "Password must be between 6 to 10 characters long");
        }
        
        boolean hasLowercase = false;
        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;
        
        // Check each character in the password
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            
            // Check for lowercase letters
            if (c >= 'a' && c <= 'z') {
                hasLowercase = true;
            }
            // Check for uppercase letters
            else if (c >= 'A' && c <= 'Z') {
                hasUppercase = true;
            }
            // Check for digits
            else if (c >= '0' && c <= '9') {
                hasDigit = true;
            }
            // Check for allowed symbols ($, @, #, !)
            else if (c == '$' || c == '@' || c == '#' || c == '!') {
                hasSymbol = true;
            }
            // Check for invalid characters
            else {
                return new ValidationResult(false, "Password contains invalid characters. Only letters, numbers, and symbols ($, @, #, !) are allowed");
            }
        }
        
        // Validate that all required character types are present
        if (!hasLowercase) {
            return new ValidationResult(false, "Password must contain at least one lowercase letter");
        }
        
        if (!hasUppercase) {
            return new ValidationResult(false, "Password must contain at least one uppercase letter");
        }
        
        if (!hasDigit) {
            return new ValidationResult(false, "Password must contain at least one digit");
        }
        
        if (!hasSymbol) {
            return new ValidationResult(false, "Password must contain at least one of these symbols: $, @, #, !");
        }
        
        return new ValidationResult(true, "Valid password");
    }
}