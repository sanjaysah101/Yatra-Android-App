package com.example.yatra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MyCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        RecyclerView recyclerViewMyItems = findViewById(R.id.recyclerViewMyItems);
        recyclerViewMyItems.setLayoutManager(new LinearLayoutManager(this));

        RecyclerItemInCartAdapter recyclerItemInCartAdapter = new RecyclerItemInCartAdapter(this, myCartArrayList());
        recyclerViewMyItems.setAdapter(recyclerItemInCartAdapter);

    }
    private ArrayList<RecyclerItemInCartModel> myCartArrayList(){
        ArrayList<RecyclerItemInCartModel> arrayList = new ArrayList<>();
//        public RecyclerItemInCartModel(String title, String deliveryDate, String deliveryMode, int price, int img, int quantity)
        arrayList.add(new RecyclerItemInCartModel("Broccoli", "1st Oct", "Free Delivery", 45, R.drawable.broccoli, 2));
        arrayList.add(new RecyclerItemInCartModel("Capsicum", "2nt Oct", "Delivery Charge Rs. 50", 35, R.drawable.capsicum, 3));
        arrayList.add(new RecyclerItemInCartModel("Onion", "3rd Oct", "Free Delivery", 25, R.drawable.onion, 1));
        return arrayList;
    }
}