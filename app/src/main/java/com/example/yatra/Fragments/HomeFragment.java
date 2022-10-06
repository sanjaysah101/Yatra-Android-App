package com.example.yatra.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yatra.Adapters.RecyclerCardProductsAdapter;
import com.example.yatra.Models.RecyclerCardProductsModel;
import com.example.yatra.Models.Product;
import com.example.yatra.Models.RetrofitModel;
import com.example.yatra.ProductRetrofit;
import com.example.yatra.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    SearchView searchView;
    RecyclerCardProductsAdapter recyclerCardProductsAdapter;
    ArrayList<RecyclerCardProductsModel> models;
    ProgressDialog progressDialog;
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
        showProgressDialog();
        ProductRetrofit.getProductData(new Callback<RetrofitModel>() {
            @Override
            public void onResponse(Call<RetrofitModel> call, Response<RetrofitModel> response) {
                if (response.code() == 200) {
                    Log.d("TAG", "onResponse: " + response.body());

                    models = new ArrayList<>();
                    RetrofitModel retrofitModel = response.body();
                    List<List<String>> product = retrofitModel.getData();
                    for (int i = 0; i < product.size(); i++) {
                        List<String> data = product.get(i);
                        Product product1 = new Product(0, data.get(0), data.get(1), data.get(6), data.get(2), Integer.parseInt(data.get(3)), data.get(4));
                        if (product1.getType().contains("vegetable")) {
                            models.add(new RecyclerCardProductsModel(product1));
                        }
                    }
                    dismissProgressDialog();
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
                    Collections.reverse(models);
                    recyclerCardProductsAdapter = new RecyclerCardProductsAdapter(getContext(), models);
                    recyclerView.setAdapter(recyclerCardProductsAdapter);
                } else {
                    models  = new ArrayList<>();
                    Product product = new Product(0, "Apple", "This is apple", "apple.jpg", "Fruit", 100, "Kg");
                    models.add(new RecyclerCardProductsModel(product));
                    Product product1 = new Product(0, "Banana", "This is apple", "apple.jpg", "Fruit", 40, "Kg");
                    models.add(new RecyclerCardProductsModel(product1));

                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    recyclerCardProductsAdapter = new RecyclerCardProductsAdapter(getContext(), models);
                    recyclerView.setAdapter(recyclerCardProductsAdapter);
                }
            }

            @Override
            public void onFailure(Call<RetrofitModel> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });

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

        for(RecyclerCardProductsModel item: models){
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
    private void showProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
    }
    private void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }







//        models.add(new RecyclerCardProductsModel(R.drawable.broccoli, "Broccoli", 45, "Kg"));
//        models.add(new RecyclerCardProductsModel(R.drawable.potato, "Potato", 30, "Kg"));
//        models.add(new RecyclerCardProductsModel(R.drawable.onion, "Onion", 20, "Kg"));
//        models.add(new RecyclerCardProductsModel(R.drawable.capsicum, "Milk", 25, "l"));
//        models.add(new RecyclerCardProductsModel(R.drawable.broccoli, "Mobile", 45, "piece"));
//        models.add(new RecyclerCardProductsModel(R.drawable.potato, "Potato", 30, "Kg"));
//        models.add(new RecyclerCardProductsModel(R.drawable.onion, "Onion", 20, "Kg"));
//        models.add(new RecyclerCardProductsModel(R.drawable.capsicum, "Capsicum", 25, "Kg"));
//        models.add(new RecyclerCardProductsModel(R.drawable.broccoli, "Broccoli", 45, "Kg"));
//        models.add(new RecyclerCardProductsModel(R.drawable.potato, "Potato", 30, "Kg"));
//        models.add(new RecyclerCardProductsModel(R.drawable.onion, "Onion", 20, "Kg"));
//        models.add(new RecyclerCardProductsModel(R.drawable.capsicum, "Capsicum", 25, "Kg"));


}