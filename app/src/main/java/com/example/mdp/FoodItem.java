package com.example.mdp;

// Model class for food items
public class FoodItem {
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    private String amount;

    // Constructor
    public FoodItem(String title, String amount) {
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


}
