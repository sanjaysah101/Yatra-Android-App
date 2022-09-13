package com.example.yatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            finish();
            return;
        }
        Objects.requireNonNull(getSupportActionBar()).hide();

        TextView signUpText, forgetPassword;
        Button btnLognin = findViewById(R.id.loginbtn);
        signUpText = findViewById(R.id.textViewSignUp);
        forgetPassword = findViewById(R.id.textViewForgetPassword);


        Intent signUpIntent = new Intent(this, SignupActivity.class);
        Intent forgetIntent = new Intent(this, ForgetPasswordActivity.class);


        btnLognin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticateUser();
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(forgetIntent);
            }
        });
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(signUpIntent);
            }
        });
    }
    private void authenticateUser(){
        EditText etUserEmail, etLoginPassword;
        etUserEmail = findViewById(R.id.userEmail);
        etLoginPassword = findViewById(R.id.password);

        String email = etUserEmail.getText().toString().trim();
        String password = etLoginPassword.getText().toString().trim();

        if(email.isEmpty()){
            etUserEmail.setError("Email is required");
            etUserEmail.requestFocus();
            return;
        }if(password.isEmpty()){
            etLoginPassword.setError("Password is required");
            etLoginPassword.requestFocus();
            return;
        }if(password.length() < 6  ){
            etLoginPassword.setError("Password must be greater than 6 characters");
            etLoginPassword.requestFocus();
            return;
        }

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if(task.isSuccessful()){
                            showDashboard();
                            finish();
                        }else{
                            Toast.makeText(SignInActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    private void showDashboard(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}