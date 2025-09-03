/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;
import java.util.*;

public class IMStockReport {
    private String reportID;
    private String reportType;
    private String reportOwner;
    private String reportDate;
    private List<Item> item;

    public IMStockReport(String reportID, String reportType, String reportOwner, String reportDate, List<Item> item) {
        this.reportID = reportID;
        this.reportType = reportType;
        this.reportOwner = reportOwner;
        this.reportDate = reportDate;
        this.item = item;
    }

    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportOwner() {
        return reportOwner;
    }

    public void setReportOwner(String reportOwner) {
        this.reportOwner = reportOwner;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return reportID + "," + reportType + "," + reportOwner + "," + reportDate + "," + item + '}';
    }
    
    public String toFileString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Report ID: ").append(reportID).append("\n");
        sb.append("Report Type: ").append(reportType).append("\n");
        sb.append("Report Owner: ").append(reportOwner).append("\n");
        sb.append("Report Date: ").append(reportDate).append("\n\n");

        sb.append("Item ID,Item Name,Supplier ID,Quantity,Price,Last Update,Status\n");

        for (Item i : item) {
            sb.append(i.getItemID()).append(",")
              .append(i.getItemName()).append(",")
              .append(i.getSupplierID()).append(",")
              .append(i.getQuantity()).append(",")
              .append(i.getPrice()).append(",")
              .append(i.getLatestUpdateDate()).append(",")
              .append(i.getStatus()).append("\n");
        }

        return sb.toString();
    }
}
