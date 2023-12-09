package com.example.mdp;

public class Transaction {
    private String name;
    private double amount;
    private String date;
    private boolean isExpanded;




    public Transaction(String name, double amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.isExpanded = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
