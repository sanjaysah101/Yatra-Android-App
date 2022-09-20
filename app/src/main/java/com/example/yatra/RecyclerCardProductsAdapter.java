package com.example.yatra;

import android.content.Context;
import android.content.Intent;
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
        final RecyclerCardProductsModel temp = arrayList.get(position);

        holder.imageProduct.setImageResource(arrayList.get(position).img);
        holder.txtPrductTitle.setText(arrayList.get(position).title);
        holder.txtProductPrice.setText(arrayList.get(position).price);

        holder.imageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent productIntent = new Intent(context, SingleProductPage.class);
                productIntent.putExtra("productTitle", temp.getTitle());
                productIntent.putExtra("productPrice", temp.getPrice());
                productIntent.putExtra("productImage", temp.getImg());
                context.startActivity(productIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        TextView txtPrductTitle, txtProductPrice;
        ImageView imageProduct;
        public ViewHolder(View itemView){
            super(itemView);
            txtPrductTitle = itemView.findViewById(R.id.txtProductTitle);
            txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
            imageProduct = itemView.findViewById(R.id.imageProduct);
        }
    }
}
