package com.example.yatra;

import com.example.yatra.Models.RetrofitModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("getProduct.php")
    Call<RetrofitModel> getProductData();
}
