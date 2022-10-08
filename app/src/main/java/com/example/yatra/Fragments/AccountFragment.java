package com.example.yatra.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yatra.MainActivity;
import com.example.yatra.R;
import com.example.yatra.SignInActivity;
import com.example.yatra.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class AccountFragment extends Fragment {

    public AccountFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_account, container, false);
        Button logoutButton = view.findViewById(R.id.logoutButton);
        TextView uName = view.findViewById(R.id.uName);
        TextView userEmail = view.findViewById(R.id.userEmail);
        TextView orders = view.findViewById(R.id.orders);
        TextView favoriteList = view.findViewById(R.id.favoriteList);
        TextView helpCenter = view.findViewById(R.id.helpCenter);
        TextView editProfile = view.findViewById(R.id.editProfile);
        TextView savedAddress = view.findViewById(R.id.editAddress);

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFrag(new MyCartFragment());
            }
        });
        helpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFrag(new AboutFragment());
            }
        });
        favoriteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFrag(new FavoriteFragment());
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFrag(new EditProfileFragment());
            }
        });
        savedAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFrag(new MyCartFragment());
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Do you want to logout");
                builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        logoutUser();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
//                Toast.makeText(getContext(), "hi", Toast.LENGTH_SHORT).show();
            }
        });


//        ############################### if user is not log in logout send to signInActivity  ########################
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(getContext(), SignInActivity.class));
            (getActivity()).finish();
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Users").child(currentUser.getUid());

        //        ########################### Fetching User's Detail from Firebase ################################
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                User user = snapshot.getValue(User.class);
                if(user != null){
                    uName.setText(user.getFullName());
                    userEmail.setText(user.getEmail());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    public void logoutUser(){
//      ##################   Logout Function #######################3
        FirebaseAuth.getInstance().signOut();
        Intent signInIntent = new Intent(getContext(), SignInActivity.class);
        startActivity(signInIntent);
        (getActivity()).finish();
    }

    public void loadFrag(Fragment fragment){
//        ########## Function to load Fragments
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeFragment, fragment);
        fragmentTransaction.commit();
    }
}