package com.example.yatra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Button sendOTPbtn;
        sendOTPbtn = findViewById(R.id.sendOTPbtn);
        Intent enterOTPIntent = new Intent(this, EnterOTPActivity.class);
        sendOTPbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(enterOTPIntent);
            }
        });
    }
}