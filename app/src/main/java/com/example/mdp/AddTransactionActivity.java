package com.example.mdp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.type.DateTime;

import java.util.HashMap;
import java.util.Map;

public class AddTransactionActivity extends AppCompatActivity {
    Button nextButton;
    EditText amountInput;
    EditText transactionTitleInput;
    Spinner expenseSpinner;

    // Get a Firestore instance
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        // Initialize your input fields
        nextButton = findViewById(R.id.nextButton);
        amountInput = findViewById(R.id.amountInput);
        transactionTitleInput = findViewById(R.id.transactionTitleInput);
        expenseSpinner = findViewById(R.id.expenseSpinner);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the data from the input fields
                String transactionTitle = transactionTitleInput.getText().toString();
                String expenseCategory = expenseSpinner.getSelectedItem().toString();
                double amount = Double.parseDouble(amountInput.getText().toString());
                FirebaseUser currentUser = mAuth.getCurrentUser();

                // Create a new transaction object
                Map<String, Object> transaction = new HashMap<>();
                transaction.put("amount", amount);
                transaction.put("name", transactionTitle);
                transaction.put("category", expenseCategory);
                transaction.put("date", FieldValue.serverTimestamp());
                assert currentUser != null;
                transaction.put("user", currentUser.getUid());
                db.collection("Transactions")
                        .add(transaction)
                        .addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                Toast.makeText(AddTransactionActivity.this, "Transaction added successfully!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                String error = e.getMessage();
                                Toast.makeText(AddTransactionActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                            }});}});}}
