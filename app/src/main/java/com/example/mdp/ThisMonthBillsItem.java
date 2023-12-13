package com.example.mdp;

public class ThisMonthBillsItem {
    private String title;
    private String amount;

    // Constructor
    public ThisMonthBillsItem(String title, String amount) {
        this.title = title;
        this.amount = amount;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAmount() {
        return amount;
    }

    // Setters, if you need to modify the data later
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}