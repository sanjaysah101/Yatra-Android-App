package com.example.yatra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ------------- Splash Screen Start -------------
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashScreen.setKeepOnScreenCondition(new SplashScreen.KeepOnScreenCondition() {
                    @Override
                    public boolean shouldKeepOnScreen() {
                        return false;
                    }
                });
            }
        }, 5000);
        // ------------- Splash Screen Ends --------------

    }
}