package com.example.abdelrahmanayman.productsdemo2.Model.Rest;

import com.example.abdelrahmanayman.productsdemo2.Model.Products.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/products?")
    Call<List<Product>> getProducts(@Query("count") int count , @Query("from") int from );
}

