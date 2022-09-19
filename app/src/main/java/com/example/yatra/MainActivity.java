package com.example.yatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.recyclerview.widget.RecyclerView;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.contentcapture.DataRemovalRequest;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<RecyclerCardProductsModel> arrContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recyclerCardProducts);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_foreground, "Sanjay Kumar Sah", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_foreground, "Sanjay Kumar Sah", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_foreground, "Sanjay Kumar Sah", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));
        arrContacts.add(new RecyclerCardProductsModel(R.drawable.ic_launcher_background, "Sanjay", "8797421521"));


        RecyclerCardProductsAdapter recyclerCardProductsAdapter = new RecyclerCardProductsAdapter(this, arrContacts);
        recyclerView.setAdapter(recyclerCardProductsAdapter);
        // ------------- Splash Screen Start -------------
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                splashScreen.setKeepOnScreenCondition(new SplashScreen.KeepOnScreenCondition() {
//                    @Override
//                    public boolean shouldKeepOnScreen() {
//                        return false;
//                    }
//                });
//            }
//        }, 5000);
        // ------------- Splash Screen Ends --------------

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        }

//        TextView textViewUsername = findViewById(R.id.textView1);
//        Button btnLogout = findViewById(R.id.btnLogout);



//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                logoutUser();
//            }
//        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Users").child(currentUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
//                if(user != null){
//                    textViewUsername.setText(user.fname);
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void logoutUser(){
        FirebaseAuth.getInstance().signOut();
        Intent signInIntent = new Intent(this, SignInActivity.class);
        startActivity(signInIntent);
        finish();
    }


    
   
}