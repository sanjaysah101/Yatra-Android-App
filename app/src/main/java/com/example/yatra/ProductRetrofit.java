package com.example.yatra;

import com.example.yatra.Models.RetrofitModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductRetrofit {
    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://visionnewspoint.com/Yatra/Yatra%20Api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
        }
        return retrofit;
    }

    public static void getProductData(Callback<RetrofitModel> cb) {
        APIInterface apiInterface = getRetrofit().create(APIInterface.class);
        Call<RetrofitModel> call = apiInterface.getProductData();
        call.enqueue(cb);
    }
}
