package com.example.mdp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText emailEditText, passwordEditText;

    Button loginButton;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                  String email = emailEditText.getText().toString();
                  email = String.valueOf(emailEditText.getText());
                  String password = passwordEditText.getText().toString();
                  password = String.valueOf(passwordEditText.getText());
                  if (email.isEmpty()) {
                    emailEditText.setError("Please enter email...");
                    emailEditText.requestFocus();
                  } else if (password.isEmpty()) {
                    passwordEditText.setError("Please enter Password");
                    passwordEditText.requestFocus();
                  } else {
                      String finalPassword = password;
                      mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, task -> {
                         if (!task.isSuccessful()) {
                              // there was an error
                              if (finalPassword.length() < 6) {
                                passwordEditText.setError("Password must be at least 6 characters");
                                passwordEditText.requestFocus();
                              } else {
                                emailEditText.setError("Email or Password is incorrect");
                                emailEditText.requestFocus();
                              }
                         } else {
                              Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                              startActivity(intent);
                              finish();
                         }
                    });
                  }
           }
       }


        );

    }


    public void toRegisterActivity(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}


