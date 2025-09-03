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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class FMBudgetManager {
   
        public List<Budget> readBudgets() {
        List<Budget> budgets = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("budget.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String month = parts[0].trim();
                    double amount = Double.parseDouble(parts[1].trim());
                    budgets.add(new Budget(month, amount));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading budget.txt: " + e.getMessage());
        }
        return budgets;
    }

    public void updateBudget(String month, double newAmount) {
        List<Budget> budgets = readBudgets();
        boolean updated = false;

        for (Budget b : budgets) {
            if (b.getMonth().equals(month)) {
                b.setAmount(newAmount);
                updated = true;
                break;
            }
        }
        if (!updated) {
            budgets.add(new Budget(month, newAmount));
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("budget.txt"))) {
            for (Budget b : budgets) {
                bw.write(b.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to budget.txt: " + e.getMessage());
        }
    }

    public Budget getBudgetByMonth(String month) {
        for (Budget b : readBudgets()) {
            if (b.getMonth().equals(month)) {
                return b;
            }
        }
        return null;
    }
    public List<String> getAvailableMonths() {
        List<String> months = new ArrayList<>();
        for (Budget budget : readBudgets()) {
            months.add(budget.getMonth());
        }
        return months;
    }


    public String getFormattedBudget(String month) {
        Budget b = getBudgetByMonth(month);
        if (b == null) return "Budget not found.";
        String monthName = LocalDate.parse(month + "-01").format(DateTimeFormatter.ofPattern("MMMM yyyy"));
        return "Current Budget (" + monthName + "): RM " + String.format("%,.2f", b.getAmount());
    }
    public double getTotalPaidThisMonth(List<PurchaseOrder> orders, String month) {
        double total = 0.0;
        for (PurchaseOrder po : orders) {
            if ("Paid".equalsIgnoreCase(po.getStatus())) {
                total += po.getTotalAmount();
            }
        }
        return total;
    }
}
