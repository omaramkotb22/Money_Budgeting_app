package com.example.mdp;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class Transaction {
    private String name;
    private double amount;
    private String category;
    private Timestamp dateTime;
    private DocumentReference user;
    private boolean isExpanded;

    public Transaction() {
    }

    public Transaction(String name, double amount, String category, Timestamp dateTime, DocumentReference user) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.dateTime = dateTime;
        this.user = user;
        this.isExpanded = false;
    }


    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public DocumentReference getUser() {
        return user;
    }

    public void setUser(DocumentReference user) {
        this.user = user;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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



    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }


}
