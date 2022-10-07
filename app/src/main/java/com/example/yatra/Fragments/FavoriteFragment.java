package com.example.yatra.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yatra.Adapters.RecyclerFavoriteItemAdapter;
import com.example.yatra.Adapters.RecyclerItemInCartAdapter;
import com.example.yatra.Models.RecyclerFavoriteItemModel;
import com.example.yatra.Models.RecyclerItemInCartModel;
import com.example.yatra.R;
import com.example.yatra.Sqlite.SQLiteDbHelper;
import com.example.yatra.Sqlite.SQLiteFavoriteItemDbHelper;

import java.util.ArrayList;
import java.util.Collections;


public class FavoriteFragment extends Fragment {


    //        ##################################### To be Changed ###############################################

    //    SQLiteFavoriteItemDbHelper dbHelper = new SQLiteFavoriteItemDbHelper(getContext());
        SQLiteDbHelper dbHelper = new SQLiteDbHelper(getContext());


    public FavoriteFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_favorite, container, false);
        RecyclerView recyclerViewMyItems = view.findViewById(R.id.recyclerViewFavoriteItems);
        recyclerViewMyItems.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
//
//        ##################################### To be Changed ###############################################

        RecyclerItemInCartAdapter recyclerFavoriteItemAdapter = new RecyclerItemInCartAdapter(getContext(), myFavoriteArrayList());
//        RecyclerFavoriteItemAdapter recyclerFavoriteItemAdapter = new RecyclerFavoriteItemAdapter(getContext(), myFavoriteArrayList());
        recyclerViewMyItems.setAdapter(recyclerFavoriteItemAdapter);
        return view;
    }

    private ArrayList<RecyclerItemInCartModel> myFavoriteArrayList() {

        ArrayList<RecyclerItemInCartModel> arrayList;
        arrayList = dbHelper.readData(getContext());
        Collections.reverse(arrayList);
        return arrayList;

    }

    //        ##################################### To be Changed ###############################################33

//    private ArrayList<RecyclerFavoriteItemModel> myFavoriteArrayList() {
//
//        ArrayList<RecyclerFavoriteItemModel> arrayList;
//        arrayList = dbHelper.readData(getContext());
//        Collections.reverse(arrayList);
//        return arrayList;
//
//    }


}