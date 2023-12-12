package com.example.mdp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AddBillActivity extends AppCompatActivity {
    private LinearLayout containerTaggedUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

        containerTaggedUsers = findViewById(R.id.container_tagged_users);

    }
    public void onAddField(View v) {
        // Hide the current "+" icon
        v.setVisibility(View.GONE);

        // Inflate new user amount view
        View userAmountView = getLayoutInflater().inflate(R.layout.item_user_amount, containerTaggedUsers, false);

        // Add the new view to the container
        containerTaggedUsers.addView(userAmountView);
    }


}