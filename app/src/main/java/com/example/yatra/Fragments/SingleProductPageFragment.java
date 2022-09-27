package com.example.yatra.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yatra.R;

import java.util.ArrayList;
import java.util.List;


public class SingleProductPageFragment extends Fragment {


    public SingleProductPageFragment() {
        // Required empty public constructor
    }

    ImageView imageProduct;
    TextView productTitle, productPrice,productDescription, productQuantity;
    ImageButton rating1, rating2, rating3, rating4, rating5;
    ImageButton removeProduct, addProduct;
    Button btnAddToCart;
    int totalProduct;
    List<ImageButton> ratingButtons = new ArrayList<ImageButton>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_single_product_page, container, false);


        initRating();
//        Intent myCartActivityIntent = new Intent(this, MyCartActivity.class);
//        Fragment fragment = new MyCartFragment();
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

//        numberPickerQuantity = findViewById(R.id.numberPickerQuantity);
        imageProduct = view.findViewById(R.id.imageProduct);
        productTitle = view.findViewById(R.id.productTitle);
        productDescription = view.findViewById(R.id.productDescription);
        productPrice = view.findViewById(R.id.productPrice);
        addProduct = view.findViewById(R.id.addProduct);
        removeProduct = view.findViewById(R.id.removeProduct);
        productQuantity = view.findViewById(R.id.productQuantity);
        btnAddToCart = view.findViewById(R.id.addToCartBtn);

//        numberPickerQuantity.setMaxValue(10);
//        numberPickerQuantity.setMinValue(1);

//        imageProduct.setImageResource(getIntent().getIntExtra("productImage", 0));
//        productTitle.setText(getIntent().getStringExtra("productTitle"));
//        productPrice.setText(getIntent().getStringExtra("productPrice"));


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

        return view;
    }
    private void initRating(){
        rating1 = getView().findViewById(R.id.rating1);
        rating2 = getView().findViewById(R.id.rating2);
        rating3 = getView().findViewById(R.id.rating3);
        rating4 = getView().findViewById(R.id.rating4);
        rating5 = getView().findViewById(R.id.rating5);

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