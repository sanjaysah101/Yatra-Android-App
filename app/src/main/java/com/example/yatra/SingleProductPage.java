package com.example.yatra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SingleProductPage extends AppCompatActivity {

//    NumberPicker numberPickerQantity;
    ImageView imageProduct;
    TextView productTitle, productPrice,productDescription, productQuantity;
    ImageButton rating1, rating2, rating3, rating4, rating5;
    ImageButton removeProduct, addProduct;
    Button btnAddToCart;
    int totalProduct, index;
    List<ImageButton> ratingButtons = new ArrayList<ImageButton>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product_page);

        initRating();
//        numberPickerQantity = findViewById(R.id.numberPickerQantity);
        imageProduct = findViewById(R.id.imageProduct);
        productTitle = findViewById(R.id.productTitle);
        productDescription = findViewById(R.id.productDescription);
        productPrice = findViewById(R.id.productPrice);
        addProduct = findViewById(R.id.addProduct);
        removeProduct = findViewById(R.id.removeProduct);
        productQuantity = findViewById(R.id.productQuantity);
        btnAddToCart = findViewById(R.id.addToCartBtn);

//        numberPickerQantity.setMaxValue(10);
//        numberPickerQantity.setMinValue(1);

        imageProduct.setImageResource(getIntent().getIntExtra("productImage", 0));
        productTitle.setText(getIntent().getStringExtra("productTitle"));
        productPrice.setText(getIntent().getStringExtra("productPrice"));


        addProduct.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                totalProduct++;
                productQuantity.setText("" + totalProduct);



            }
        });
        removeProduct.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(totalProduct > 1)
                    totalProduct--;
                productQuantity.setText(""+totalProduct);
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
//                    Toast.makeText(SingleProductPage.this, "Clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddToCart.setText(getResources().getString(R.string.btnAddToCartClicked));
                btnAddToCart.setBackgroundColor(getResources().getColor(R.color.btnColor2));
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
                rattingButton.setImageResource(R.drawable.ic_baseline_star_rate_48);
            }else{
                rattingButton.setImageResource(R.drawable.ic_baseline_star_outline_48);
            }
            index++;
        }
    }
}