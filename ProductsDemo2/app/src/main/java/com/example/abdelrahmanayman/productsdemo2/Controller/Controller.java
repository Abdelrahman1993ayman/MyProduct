package com.example.abdelrahmanayman.productsdemo2.Controller;

import android.content.Context;
import android.widget.Toast;

import com.example.abdelrahmanayman.productsdemo2.Model.Products.Product;
import com.example.abdelrahmanayman.productsdemo2.Model.Rest.APIInterface;
import com.example.abdelrahmanayman.productsdemo2.View.MainActivity;
import com.example.abdelrahmanayman.productsdemo2.interfaces.ProductResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static com.example.abdelrahmanayman.productsdemo2.Model.Rest.APIClient.getClient;

public class Controller {
    private List<Product> productsList ;
    private Context context ;
    private ProductResponse productResponse;

    public Controller(Context context , ProductResponse productResponse) {
        this.productResponse = productResponse;
        this.context = context;
    }

    public void getProduct (int count , int from) {
        productsList = new ArrayList<>();
        APIInterface apiInterface = getClient().create(APIInterface.class);
        Call<List<Product>> call = apiInterface.getProducts(count , from);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, retrofit2.Response<List<Product>> response) {
                if (response.body() != null){
                    productResponse.getProductsResponse(response);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}

