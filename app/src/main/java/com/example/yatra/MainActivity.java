package com.example.yatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.yatra.Adapters.ViewPagerAdapter;
import com.example.yatra.Fragments.AboutFragment;
import com.example.yatra.Fragments.AccountFragment;
import com.example.yatra.Fragments.FavoriteFragment;
import com.example.yatra.Fragments.FruitsFragment;
import com.example.yatra.Fragments.HomeFragment;
import com.example.yatra.Fragments.MyCartFragment;
import com.example.yatra.Fragments.EditProfileFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ############################### if user is not log in logout send to signInActivity  ########################
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        }


//        ############## Initialize variables #########################
        AdView adView = findViewById(R.id.bannerAdView);
        ViewPager viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        drawerLayout = findViewById(R.id.my_drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_bar);
        RelativeLayout rlMain = findViewById(R.id.rlMain);
        FrameLayout frameLayout = findViewById(R.id.homeFragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        DatabaseReference reference = database.getReference("Users").child(currentUser.getUid());

        //        ########################### Fetching User's Detail from Firebase ################################
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                User user = snapshot.getValue(User.class);
                if(user != null){
//                    textViewUsername.setText(user.fname);
                    loadFrag(new HomeFragment());
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


//        ########################### Mobile Adds Start ##################################
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
//        ########################### Mobile Adds End ##################################

        progressBar.setVisibility(View.GONE);

//        ####################### View page Starts Here ##############################
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.add(new HomeFragment(), "Vegetable");
        viewPagerAdapter.add(new FruitsFragment(), "Fruits");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

//        ############################# Bottom Navigation Start #########################################

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (R.id.bottom_nav_home == item.getItemId()){
                    rlMain.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.GONE);
                    //loadFrag(new HomeFragment());
                    progressBar.setVisibility(View.INVISIBLE);
                }else if (R.id.bottom_nav_cart == item.getItemId()){
                    progressBar.setVisibility(View.VISIBLE);
                    rlMain.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                    loadFrag(new MyCartFragment());
                    progressBar.setVisibility(View.INVISIBLE);
                }else if (R.id.bottom_nav_favorite_list == item.getItemId()){
                    progressBar.setVisibility(View.VISIBLE);
                    rlMain.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                    loadFrag(new FavoriteFragment());
                    progressBar.setVisibility(View.INVISIBLE);
                }else if (R.id.bottom_nav_my_Profile == item.getItemId()){
                    progressBar.setVisibility(View.VISIBLE);
                    rlMain.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                    loadFrag(new AccountFragment());
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        //        ############################# Bottom Navigation Start #########################################3


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // to perform event on menu item click
        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (R.id.nav_home == item.getItemId()) {
                    rlMain.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                    loadFrag(new HomeFragment());
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (R.id.nav_profile == item.getItemId()) {
                    rlMain.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                    loadFrag(new AccountFragment());
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (R.id.nav_favorite == item.getItemId()) {
                    rlMain.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                    loadFrag(new FavoriteFragment());
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (R.id.nav_about == item.getItemId()) {
                    rlMain.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                    loadFrag(new AboutFragment());
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void logoutUser(){
//      ##################   Logout Function #######################3
        FirebaseAuth.getInstance().signOut();
        Intent signInIntent = new Intent(this, SignInActivity.class);
        startActivity(signInIntent);
        finish();
    }


    public void loadFrag(Fragment fragment){
//        ########## Function to load Fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeFragment, fragment);
        fragmentTransaction.commit();
    }

    
   
}