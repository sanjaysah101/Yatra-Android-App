package com.example.yatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yatra.Models.Product;
import com.example.yatra.Sqlite.SQLiteDbHelper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SingleProductPageActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
//    NumberPicker numberPickerQuantity;
    ImageView imageProduct;
    TextView productTitle, productPrice,productDescription, productQuantity, totalPrice;
    ImageButton rating1, rating2, rating3, rating4, rating5;
    ImageButton removeProduct, addProduct;
    Button btnAddToCart;
    int totalProduct;
    List<ImageButton> ratingButtons = new ArrayList<ImageButton>();


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product_page);

        initRating();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });
        AdView adView = findViewById(R.id.bannerAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        RewardAds.initRewardAd(this);
//        Intent myCartActivityIntent = new Intent(this, MyCartActivity.class);
//        Fragment fragment = new MyCartFragment();
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

//        numberPickerQuantity = findViewById(R.id.numberPickerQuantity);
        imageProduct = findViewById(R.id.imageProduct);
        productTitle = findViewById(R.id.productTitle);
        productDescription = findViewById(R.id.productDescription);
        productPrice = findViewById(R.id.productPrice);
        totalPrice = findViewById(R.id.totalPrice);
        addProduct = findViewById(R.id.addProduct);
        removeProduct = findViewById(R.id.removeProduct);
        productQuantity = findViewById(R.id.productQuantity);
        btnAddToCart = findViewById(R.id.addToCartBtn);
        SQLiteDbHelper dbHelper = new SQLiteDbHelper(this);

//        numberPickerQuantity.setMaxValue(10);
//        numberPickerQuantity.setMinValue(1);
        Product product = (Product) getIntent().getSerializableExtra("product");
//        String productId = getIntent().getStringExtra("productId");
//        String productId = product.getName();

        Picasso.get().load(product.getImage())
                .resize(200,200)
                        .into(imageProduct);
//        productTitle.setText(getIntent().getStringExtra("productTitle"));
        productTitle.setText(product.getName());
        String unit = product.getUnit();
        int unitPrice = product.getPrice();
        productDescription.setText(product.getDescription());
        productPrice.setText(""+unitPrice+" Rs / " + unit);


        addProduct.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                totalProduct++;
                productQuantity.setText("" + totalProduct);
//                if(productPrice.getText() != null){
                    int total = unitPrice * totalProduct;
                    totalPrice.setText("Total: "+total+" Rs");
//                }
            }
        });
        removeProduct.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(totalProduct > 1)
                    totalProduct--;
                productQuantity.setText(""+totalProduct);
//                if(productPrice.getText() != null){
                    int total = unitPrice * totalProduct;
                    totalPrice.setText("Total: "+total+" Rs");
//                }
            }
        });

        for(ImageButton rattingButton: ratingButtons){
            rattingButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
//                    index++;
                    ImageButton clickedRating = (ImageButton) view;
                    int clickedRatingTag = Integer.parseInt(view.getTag().toString());
                    updateRating(clickedRatingTag);
//                    Toast.makeText(SingleProductPageActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.addNewData(product.getName(), product.getImage(), Integer.parseInt(productQuantity.getText().toString()), product.getPrice());
                Intent intent = new Intent(SingleProductPageActivity.this, DoneActivity.class);
                Toast.makeText(SingleProductPageActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
                intent.putExtra("title",product.getName());
                intent.putExtra("quantity",productQuantity.getText().toString());
                intent.putExtra("price",product.getPrice());

                if (RewardAds.mRewardedAd != null) {
                    RewardAds.showRewardAd(SingleProductPageActivity.this, new RewardAds.iReward() {
                        @Override
                        public void onResponse(boolean rewards) {
                            if (rewards) {
                                startNextActivity(intent);
                            } else {
                                startNextActivity(intent);
                            }
                        }
                    });
                } else {
                    startNextActivity(intent);
                }
            }
        });
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRating(){
        rating1 = findViewById(R.id.rating1);
        rating2 = findViewById(R.id.rating2);
        rating3 = findViewById(R.id.rating3);
        rating4 = findViewById(R.id.rating4);
        rating5 = findViewById(R.id.rating5);

        ratingButtons.add(rating1);
        ratingButtons.add(rating2);
        ratingButtons.add(rating3);
        ratingButtons.add(rating4);
        ratingButtons.add(rating5);
    }
    private void updateRating(int tag){
//        for()
        int index = 1;
        for (ImageButton rattingButton :
                ratingButtons) {
            if(index <= tag){
                rattingButton.setImageResource(R.drawable.ic_baseline_star_rate_24);
            }else{
                rattingButton.setImageResource(R.drawable.ic_baseline_star_outline_24);
            }
            index++;
        }
    }

    private void startNextActivity(Intent intent) {
        startActivity(intent);
        finish();
    }
}