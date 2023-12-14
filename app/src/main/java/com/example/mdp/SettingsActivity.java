package com.example.mdp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class SettingsActivity extends AppCompatActivity {

    LinearLayout mainActivity;
    LinearLayout thisMonthActivity;
    LinearLayout billsActivity;

    LinearLayout settingsActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mainActivity = findViewById(R.id.nav_item_1);
        mainActivity.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
        });
        thisMonthActivity = findViewById(R.id.this_month_nav);
        thisMonthActivity.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, ThisMonthActivity.class);
            startActivity(intent);
        });
        billsActivity = findViewById(R.id.bills_nav);
        billsActivity.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, BillsActivity.class);
            startActivity(intent);
        });
        settingsActivity = findViewById(R.id.nav_item_4);
        settingsActivity.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
            startActivity(intent);
        });


    }



}