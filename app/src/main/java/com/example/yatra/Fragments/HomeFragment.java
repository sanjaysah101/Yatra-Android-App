package com.example.yatra.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yatra.Adapters.RecyclerCardProductsAdapter;
import com.example.yatra.Models.RecyclerCardProductsModel;
import com.example.yatra.R;

import java.util.ArrayList;
import java.util.Locale;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    SearchView searchView;
    RecyclerCardProductsAdapter recyclerCardProductsAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerCardProducts);
        searchView = view.findViewById(R.id.searchView);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerCardProductsAdapter = new RecyclerCardProductsAdapter(getContext(), productArrayList());
        recyclerView.setAdapter(recyclerCardProductsAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                recyclerCardProductsAdapter.getFilter().filter(newText);
                filter(newText);
                return false;
            }
        });

        return view;
    }

    private void filter(String text){
        ArrayList<RecyclerCardProductsModel> filteredList = new ArrayList<>();

        for(RecyclerCardProductsModel item: productArrayList()){
            if(item.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        }else {
            recyclerCardProductsAdapter.filterList(filteredList);
        }
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