package com.example.yatra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerCardProductsAdapter extends RecyclerView.Adapter<RecyclerCardProductsAdapter.ViewHolder> {

    Context context;
    ArrayList<RecyclerCardProductsModel> arrayList;
    RecyclerCardProductsAdapter(Context context, ArrayList<RecyclerCardProductsModel> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_tems_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgContact.setImageResource(arrayList.get(position).img);
        holder.txtName.setText(arrayList.get(position).name);
        holder.txtNumber.setText(arrayList.get(position).number);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        TextView txtName, txtNumber;
        ImageView imgContact;
        public ViewHolder(View itemView){
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtNumber = itemView.findViewById(R.id.txtNumber);
            imgContact = itemView.findViewById(R.id.imgContact);
        }
    }
}
