package com.example.yatra.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yatra.Adapters.RecyclerItemInCartAdapter;
import com.example.yatra.Models.RecyclerItemInCartModel;
import com.example.yatra.R;
import com.example.yatra.Sqlite.SQLiteDbHelper;

import java.util.ArrayList;

public class MyCartFragment extends Fragment {

    SQLiteDbHelper dbHelper = new SQLiteDbHelper(getContext());
    public MyCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_cart, container, false);
        RecyclerView recyclerViewMyItems = view.findViewById(R.id.recyclerViewMyItems);
        recyclerViewMyItems.setLayoutManager(new LinearLayoutManager(getContext()));

        RecyclerItemInCartAdapter recyclerItemInCartAdapter = new RecyclerItemInCartAdapter(getContext(), myCartArrayList());
        recyclerViewMyItems.setAdapter(recyclerItemInCartAdapter);
        return view;
    }
    private ArrayList<RecyclerItemInCartModel> myCartArrayList(){
        ArrayList<RecyclerItemInCartModel> arrayList = new ArrayList<>();
        arrayList = dbHelper.readData(getContext());
        return arrayList;
    }
}