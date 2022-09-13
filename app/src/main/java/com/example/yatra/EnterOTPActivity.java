package com.example.yatra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnterOTPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);
        Button verifyOTPbtn = findViewById(R.id.verifyOTPbtn);
        Intent enterPasswordIntent = new Intent(this, EnterPasswordActivity.class);

        verifyOTPbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(enterPasswordIntent);
            }
        });
    }
}