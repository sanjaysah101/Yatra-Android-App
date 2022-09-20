package com.example.yatra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SingleProductPage extends AppCompatActivity {

    NumberPicker numberPickerQantity;
    ImageView imageProduct;
    TextView productTitle, productPrice,productDescription;
    ImageFilterButton rating1, rating2, rating3, rating4, rating5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product_page);

        numberPickerQantity = findViewById(R.id.numberPickerQantity);
        imageProduct = findViewById(R.id.imageProduct);
        productTitle = findViewById(R.id.productTitle);
        productDescription = findViewById(R.id.productDescription);
        productPrice = findViewById(R.id.productPrice);

        numberPickerQantity.setMaxValue(10);
        numberPickerQantity.setMinValue(1);

        imageProduct.setImageResource(getIntent().getIntExtra("productImage", 0));
        productTitle.setText(getIntent().getStringExtra("productTitle"));
        productPrice.setText(getIntent().getStringExtra("productPrice"));
    }
}