package com.example.yatra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yatra.Fragments.MyCartFragment;
import com.example.yatra.Models.AddToCart;
import com.example.yatra.Models.Product;

import java.util.ArrayList;
import java.util.List;

public class SingleProductPageActivity extends AppCompatActivity {

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

//        numberPickerQuantity.setMaxValue(10);
//        numberPickerQuantity.setMinValue(1);
        Product product = (Product) getIntent().getSerializableExtra("product");
//        String productId = getIntent().getStringExtra("productId");
//        String productId = product.getName();

        imageProduct.setImageResource(getIntent().getIntExtra("productImage", 0));
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
                ArrayList<AddToCart> mycart = new ArrayList<>();
                AddToCart addToCart = new AddToCart(product, totalProduct);
                mycart.add(addToCart);

                btnAddToCart.setText(getResources().getString(R.string.btnAddToCartClicked));
                btnAddToCart.setBackgroundColor(getResources().getColor(R.color.btnColor2));
//                Intent intent = new Intent(getApplicationContext(), MyCartFragment.class);
//                finish();

                FragmentManager fragmentManager = getSupportFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                final MyCartFragment myFragment = new MyCartFragment();
//
//                Bundle b = new Bundle();
//                b.putString("message", "Saved to cart");
//                myFragment.setArguments(b);
//                fragmentTransaction.add(R.id.frameLayout, myFragment).commit();

//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.add(R.id.recyclerViewMyItems, new MyCartFragment());
//                fragmentTransaction.commit();

            }
        });


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
}