package com.example.yatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yatra.Adapters.RecyclerCardProductsAdapter;
import com.example.yatra.Models.RecyclerCardProductsModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recyclerCardProducts);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (R.id.bottom_nav_home == item.getItemId()){
                    loadFrag(new HomeFragment());
                    Toast.makeText(MainActivity.this, "Home clicked", Toast.LENGTH_SHORT).show();
                }else if (R.id.bottom_nav_cart == item.getItemId()){
                    Toast.makeText(MainActivity.this, "Cart clicked", Toast.LENGTH_SHORT).show();
                }else if (R.id.bottom_nav_favorite_list == item.getItemId()){
                    Toast.makeText(MainActivity.this, "favorite clicked", Toast.LENGTH_SHORT).show();
                }else if (R.id.bottom_nav_my_Profile == item.getItemId()){
                    Toast.makeText(MainActivity.this, "profile clicked", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        RecyclerCardProductsAdapter recyclerCardProductsAdapter = new RecyclerCardProductsAdapter(this, productArrayList());
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

    private ArrayList<RecyclerCardProductsModel> productArrayList(){
        ArrayList<RecyclerCardProductsModel> models = new ArrayList<>();

        models.add(new RecyclerCardProductsModel(R.drawable.broccoli, "Broccoli", "45 Rs."));
        models.add(new RecyclerCardProductsModel(R.drawable.potato, "Potato", "30 Rs."));
        models.add(new RecyclerCardProductsModel(R.drawable.onion, "Onion", "20 Rs."));
        models.add(new RecyclerCardProductsModel(R.drawable.capsicum, "Capsicum", "25 Rs"));
//        models.add(new RecyclerCardProductsModel(R.drawable.broccoli, "Broccoli", "45 Rs."));
//        models.add(new RecyclerCardProductsModel(R.drawable.potato, "Potato", "30 Rs."));
//        models.add(new RecyclerCardProductsModel(R.drawable.onion, "Onion", "20 Rs."));
//        models.add(new RecyclerCardProductsModel(R.drawable.capsicum, "Capsicum", "25 Rs"));
//        models.add(new RecyclerCardProductsModel(R.drawable.broccoli, "Broccoli", "45 Rs."));
//        models.add(new RecyclerCardProductsModel(R.drawable.potato, "Potato", "30 Rs."));
//        models.add(new RecyclerCardProductsModel(R.drawable.onion, "Onion", "20 Rs."));
//        models.add(new RecyclerCardProductsModel(R.drawable.capsicum, "Capsicum", "25 Rs"));

        return models;
    }

    public void loadFrag(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, fragment);
        fragmentTransaction.commit();
    }

    
   
}