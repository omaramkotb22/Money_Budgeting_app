package com.example.mdp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AddBillActivity extends AppCompatActivity {
    private LinearLayout containerTaggedUsers;
    private EditText billTitleEditText;
    private EditText totalBillEditText;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

        containerTaggedUsers = findViewById(R.id.container_tagged_users);
        Button addBillButton = findViewById(R.id.button_add_bill);
        addBillButton.setOnClickListener(view -> {
            addBillToFirestore();
            Intent intent = new Intent(AddBillActivity.this, BillsActivity.class);
            startActivity(intent);
        });

    }
    public void onAddField(View v) {
        // Hide the current "+" icon
        v.setVisibility(View.GONE);

        // Inflate new user amount view
        View userAmountView = getLayoutInflater().inflate(R.layout.item_user_amount, containerTaggedUsers, false);

        // Add the new view to the container
        containerTaggedUsers.addView(userAmountView);


    }


    private void addBillToFirestore() {
        db = FirebaseFirestore.getInstance();
        billTitleEditText = findViewById(R.id.editText_bill_title);
        String title = billTitleEditText.getText().toString();
        totalBillEditText = findViewById(R.id.editText_total_bill);
        String totalBillAmount = totalBillEditText.getText().toString();
        List<Map<String, Object>> users = new ArrayList<>();

        for (int i = 0; i < containerTaggedUsers.getChildCount(); i++) {
            View userView = containerTaggedUsers.getChildAt(i);
            EditText userEmailEditText = userView.findViewById(R.id.editText_user_name); // Assuming you have an EditText for email
            EditText userAmountEditText = userView.findViewById(R.id.editText_amount);

            String userEmail = userEmailEditText.getText().toString();
            String userAmount = userAmountEditText.getText().toString();

            Map<String, Object> userMap = new HashMap<>();
//            String userId = userEmail;
            Log.d("AddBillActivity", "userId: " + userEmail);

            userMap.put(userEmail, userAmount);
            users.add(userMap);
        }

        Map<String, Object> billData = new HashMap<>();
        billData.put("title", title);
        billData.put("bill_creator", Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        billData.put("users", users);
        billData.put("bill_label", "pending");
        billData.put("Timestamp", new Timestamp(new Date()));
        billData.put("total_bill_amount", totalBillAmount);
        Log.d("Bill Data", billData.toString());
        db.collection("Bills").add(billData)
                .addOnSuccessListener(documentReference -> Log.d("AddBillActivity", "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("AddBillActivity", "Error adding document", e));
    }




}