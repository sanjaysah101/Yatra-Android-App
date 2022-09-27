package com.example.yatra.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yatra.Adapters.RecyclerCardProductsAdapter;
import com.example.yatra.Models.RecyclerCardProductsModel;
import com.example.yatra.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerCardProducts);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        RecyclerCardProductsAdapter recyclerCardProductsAdapter = new RecyclerCardProductsAdapter(getContext(), productArrayList());
        recyclerView.setAdapter(recyclerCardProductsAdapter);

        return view;
    }

    private ArrayList<RecyclerCardProductsModel> productArrayList(){
        ArrayList<RecyclerCardProductsModel> models = new ArrayList<>();

        models.add(new RecyclerCardProductsModel(R.drawable.broccoli, "Broccoli", "45 Rs."));
        models.add(new RecyclerCardProductsModel(R.drawable.potato, "Potato", "30 Rs."));
        models.add(new RecyclerCardProductsModel(R.drawable.onion, "Onion", "20 Rs."));
        models.add(new RecyclerCardProductsModel(R.drawable.capsicum, "Capsicum", "25 Rs"));
        models.add(new RecyclerCardProductsModel(R.drawable.broccoli, "Broccoli", "45 Rs."));
        models.add(new RecyclerCardProductsModel(R.drawable.potato, "Potato", "30 Rs."));
        models.add(new RecyclerCardProductsModel(R.drawable.onion, "Onion", "20 Rs."));
        models.add(new RecyclerCardProductsModel(R.drawable.capsicum, "Capsicum", "25 Rs"));
        models.add(new RecyclerCardProductsModel(R.drawable.broccoli, "Broccoli", "45 Rs."));
        models.add(new RecyclerCardProductsModel(R.drawable.potato, "Potato", "30 Rs."));
        models.add(new RecyclerCardProductsModel(R.drawable.onion, "Onion", "20 Rs."));
        models.add(new RecyclerCardProductsModel(R.drawable.capsicum, "Capsicum", "25 Rs"));

        return models;
    }
}