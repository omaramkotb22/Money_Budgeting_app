package com.example.mdp;

public class BillsItems {
    private String title;
    private String totalBillAmount;
    private String billLabel;
    // ... Add other fields you might need

    public BillsItems(String title, String totalBillAmount, String billLabel) {
        this.title = title;
        this.totalBillAmount = totalBillAmount;
        this.billLabel = billLabel;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public String getTotalBillAmount() {
        return totalBillAmount;
    }

    public String getBillLabel() {
        return billLabel;
    }
}
