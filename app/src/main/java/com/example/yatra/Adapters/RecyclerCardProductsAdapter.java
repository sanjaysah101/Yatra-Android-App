package com.example.yatra.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yatra.R;
import com.example.yatra.Models.RecyclerCardProductsModel;
import com.example.yatra.SingleProductPageActivity;

import java.util.ArrayList;

public class RecyclerCardProductsAdapter extends RecyclerView.Adapter<RecyclerCardProductsAdapter.ViewHolder> {

    Context context;
    ArrayList<RecyclerCardProductsModel> productModelArrayList;
//    ArrayList<RecyclerCardProductsModel> backup;
    public RecyclerCardProductsAdapter(Context context, ArrayList<RecyclerCardProductsModel> arrayList){
        this.context = context;
        this.productModelArrayList = arrayList;
//        this.backup = arrayList;
    }

//    method for filtering our recyclerview items
    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<RecyclerCardProductsModel> filterList){
        productModelArrayList = filterList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_tems_list, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RecyclerCardProductsModel temp = productModelArrayList.get(position);

//        holder.imageProduct.setImageResource(arrayList.get(position).getImg());
        holder.txtProductTitle.setText(productModelArrayList.get(position).getProduct().getName());
        holder.txtProductPrice.setText(""+productModelArrayList.get(position).getPrice());

        holder.imageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent productIntent = new Intent(context, SingleProductPageActivity.class);
                productIntent.putExtra("product", temp.getProduct());
//                productIntent.putExtra("productTitle", temp.getTitle());
//                productIntent.putExtra("productPrice", temp.getPrice());
//                productIntent.putExtra("productImage", temp.getImg());
//                productIntent.putExtra("productUnit", temp.getUnit());
                context.startActivity(productIntent);


                //Intent productIntent = new Intent(context, SingleProductPageActivity.class);
//                AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
//                Bundle productBundle = new Bundle();
//                SingleProductPageFragment singleProductPageFragment = new SingleProductPageFragment();
//
//                productBundle.putString("productTitle", temp.getTitle());
//                productBundle.putString("productPrice", temp.getPrice());
//                productBundle.putInt("productImage", temp.getImg());
//
//                singleProductPageFragment.setArguments(productBundle);
//                context.getSupportFragmentManager
//                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, singleProductPageFragment).commit();

//                loadFrag(new SingleProductPageFragment(), view);

            }
        });
    }

    @Override
    public int getItemCount() {
        return productModelArrayList.size();
    }

//    @Override
//    public Filter getFilter() {
//
//        return filter;
//    }
//    Filter filter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence charSequence) {
//            ArrayList<RecyclerCardProductsModel> filteredData = new ArrayList<>();
//
//            if (charSequence.toString().isEmpty()){
//                filteredData.addAll(backup);
////                Toast.makeText(context, "Search field is empty", Toast.LENGTH_SHORT).show();
//            }
//            else{
//                for (RecyclerCardProductsModel obj: backup){
//                    if(obj.getTitle().toString().toLowerCase().trim().contains(charSequence.toString().toLowerCase().trim())){
//                        filteredData.add(obj);
////                        Toast.makeText(context, obj.getTitle(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//
//            FilterResults results = new FilterResults();
//            results.values = filteredData;
//            return results;
//        }
//
//        @SuppressLint("NotifyDataSetChanged")
//        @Override
//        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//            arrayList.clear();
//            arrayList.addAll((ArrayList<RecyclerCardProductsModel>)filterResults.values);
//            notifyDataSetChanged();
//        }
//    };

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        TextView txtProductTitle, txtProductPrice;
        ImageView imageProduct;
        public ViewHolder(View itemView){
            super(itemView);
            txtProductTitle = itemView.findViewById(R.id.txtProductTitle);
            txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
            imageProduct = itemView.findViewById(R.id.imageProduct);
        }
    }
}
