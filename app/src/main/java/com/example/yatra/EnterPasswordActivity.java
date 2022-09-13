package com.example.yatra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnterPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);
        Button updatePasswordBtn = findViewById(R.id.updatePasswordBtn);
        Intent passwordResetSuccessIntent = new Intent(this, PasswordResetSuccessfulActivity.class);
        updatePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(passwordResetSuccessIntent);
            }
        });
    }
}