package com.example.yatra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Objects.requireNonNull(getSupportActionBar()).hide();

        TextView signUpTextBtn, forgetpass;
        signUpTextBtn = findViewById(R.id.signUpTextBtn);
        forgetpass = findViewById(R.id.textViewForgetPassword);
        Intent signUpIntent = new Intent(this, SignupActivity.class);
        Intent forgetIntent = new Intent(this, ForgetPasswordActivity.class);


        signUpTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(signUpIntent);
            }
        });
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(forgetIntent);
            }
        });
    }
}