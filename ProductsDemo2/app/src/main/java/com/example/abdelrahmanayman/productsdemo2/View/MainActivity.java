package com.example.abdelrahmanayman.productsdemo2.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.abdelrahmanayman.productsdemo2.Controller.Controller;
import com.example.abdelrahmanayman.productsdemo2.Model.Products.Product;
import com.example.abdelrahmanayman.productsdemo2.Model.Products.ProductsAdapter;
import com.example.abdelrahmanayman.productsdemo2.R;
import com.example.abdelrahmanayman.productsdemo2.interfaces.ProductResponse;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ProductResponse {

    private RecyclerView mRecyclerView;
    private List<Product> productsList ;
    private ProductsAdapter productsAdapter;
    private Controller controller;
    private int id=1 ;
    private int count =10 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productsList = new ArrayList<>();
        controller = new Controller(MainActivity.this , this);
        controller.getProduct(count , id);
        initViews();
    }

    public void initViews() {
        mRecyclerView = this.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
                int totalCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();

                if (lastVisible == totalCount - 1)
                    loadMore();
            }
        });
    }

    public void getView(List<Product> productsList){
        if (productsAdapter != null){
            productsAdapter.addAll(productsList);
        }else {
            productsAdapter = new ProductsAdapter(productsList , this);
            mRecyclerView.setAdapter(productsAdapter);
        }
    }

    @Override
    public void getProductsResponse(Response response) {
        if (response.body() != null){
            if (response.body() instanceof List) {
                if (productsList.size() != 0) {
                    productsList.addAll((List<Product>)response.body());
                    getView(productsList);
                } else {
                    productsList = (List<Product>) response.body();
                    getView(productsList);
                }
            }
            else
                Toast.makeText(this, "Response Not Match", Toast.LENGTH_SHORT).show();
        }

    }

    public void loadMore(){
        controller.getProduct(count , productsList.get(productsList.size() - 1).getId());
    }
}
