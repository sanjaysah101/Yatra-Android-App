package com.example.yatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText userEmail;
    private ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        userEmail = findViewById(R.id.userEmail);
        Button resetPasswordButton = findViewById(R.id.sendOTPbtn);
        progressBar =  findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();
        Intent enterOTPIntent = new Intent(this, EnterOTPActivity.class);
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
//                startActivity(enterOTPIntent);
            }
        });
    }
    private void resetPassword(){
        String email = userEmail.getText().toString().trim();
        if(email.isEmpty()){
            userEmail.setError("Email is required");
            userEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            userEmail.setError("Invalid Email");
            userEmail.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.INVISIBLE);
                if(task.isSuccessful()){
                    Toast.makeText(ForgetPasswordActivity.this, "Check your email to reset your password!", Toast.LENGTH_SHORT).show();
                    
                }else{
                    Toast.makeText(ForgetPasswordActivity.this, "Try again! Something wrong happened!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}