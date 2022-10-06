package com.example.yatra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        TextView message = findViewById(R.id.message);
        Button goToHome = findViewById(R.id.goToHome);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String quantity = intent.getStringExtra("quantity");
        String price = intent.getStringExtra("price");
//        int totalAmt = Integer.parseInt(quantity) * Integer.parseInt(price);
//        String messageText = "You have added "+quantity+" of "+title.toUpperCase() + " with an total of " ;
        String messageText = "You have successfully added "+title+" in cart" ;
        message.setText(messageText);

        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }
}