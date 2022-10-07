package com.example.yatra.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yatra.Models.RecyclerFavoriteItemModel;
import com.example.yatra.R;
import com.example.yatra.Sqlite.SQLiteDbHelper;
import com.example.yatra.Sqlite.SQLiteFavoriteItemDbHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerFavoriteItemAdapter extends RecyclerView.Adapter<RecyclerFavoriteItemAdapter.ViewHolder> {
    Context context;
    ArrayList<RecyclerFavoriteItemModel> arrayList;

    public RecyclerFavoriteItemAdapter(Context context, ArrayList<RecyclerFavoriteItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item_in_cart, parent, false);
        return new ViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<ImageButton> ratingButtons = new ArrayList<ImageButton>();

        RecyclerFavoriteItemModel recyclerFavoriteItemModel = arrayList.get(position);

        int unitPrice = arrayList.get(position).getPrice();



//        holder.imageProduct.setImageResource(arrayList.get(position).getImg());
        Picasso.get().load(arrayList.get(position).getImg()).resize(130,110).into(holder.imageProduct);
        holder.productTitle.setText(arrayList.get(position).getTitle());
        holder.productPrice.setText(""+arrayList.get(position).getPrice());

        ratingButtons.add(holder.rating1);
        ratingButtons.add(holder.rating2);
        ratingButtons.add(holder.rating3);
        ratingButtons.add(holder.rating4);
        ratingButtons.add(holder.rating5);

        for(ImageButton rattingButton: ratingButtons) {
            rattingButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
//                    index++;
                    ImageButton clickedRating = (ImageButton) view;
                    int clickedRatingTag = Integer.parseInt(view.getTag().toString());
                    updateRating(clickedRatingTag, ratingButtons);
//                    Toast.makeText(SingleProductPageActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }

        //To increase quantity of the product



        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                SQLiteFavoriteItemDbHelper dbHelper = new SQLiteFavoriteItemDbHelper(context);
                dbHelper.deleteData(Integer.toString(arrayList.get(position).getId()));
                ArrayList<RecyclerFavoriteItemModel> arrayList = new ArrayList<>();
                arrayList = dbHelper.readData(context);
//                return arrayList;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private void updateRating(int tag, List<ImageButton> ratingButtons){
        // A function to update ratings
        int index = 1;
        for (ImageButton rattingButton :
                ratingButtons) {
            if(index <= tag){
                rattingButton.setImageResource(R.drawable.ic_baseline_star_rate_24);
            }else{
                rattingButton.setImageResource(R.drawable.ic_baseline_star_outline_24);
            }
            index++;
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton rating1, rating2, rating3, rating4, rating5, removeProduct, addProduct;
        ImageView imageProduct;
        TextView productTitle, productPrice,productDescription, productQuantity, deliveryDate, deliveryMode;
        Button btnRemove;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.imageProduct);
            productTitle = itemView.findViewById(R.id.productTitle);
            productPrice = itemView.findViewById(R.id.productPrice);
            deliveryDate = itemView.findViewById(R.id.deliveryDate);
            deliveryMode = itemView.findViewById(R.id.deliveryMode);
            addProduct = itemView.findViewById(R.id.addProduct);
            productDescription = itemView.findViewById(R.id.productDescription);
            removeProduct = itemView.findViewById(R.id.removeProduct);
            productQuantity = itemView.findViewById(R.id.productQuantity);
            btnRemove = itemView.findViewById(R.id.btnRemove);

            rating1 = itemView.findViewById(R.id.rating1);
            rating2 = itemView.findViewById(R.id.rating2);
            rating3 = itemView.findViewById(R.id.rating3);
            rating4 = itemView.findViewById(R.id.rating4);
            rating5 = itemView.findViewById(R.id.rating5);
        }
    }
}
