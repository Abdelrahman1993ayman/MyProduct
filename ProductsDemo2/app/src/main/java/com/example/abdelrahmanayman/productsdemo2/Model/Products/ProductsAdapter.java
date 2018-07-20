package com.example.abdelrahmanayman.productsdemo2.Model.Products;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.abdelrahmanayman.productsdemo2.R;
import com.example.abdelrahmanayman.productsdemo2.View.PreviewImage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.Holder> {
    ////
    private List<Product> productsList ;
    private Context context;
    private String imageurl;

    public ProductsAdapter(List<Product> products  , Context context) {
        this.context = context;
       this.productsList = products;
    }

    @NonNull
    @Override
    public ProductsAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {
        Product products = productsList.get(position);
        holder.pDesc.setText(products.getProductDescription());
        holder.pPrice.setText(Integer.toString(products.getPrice()));

        imageurl = products.getImage().getUrl();
        Picasso.with(context).load(Uri.parse(imageurl)).into(holder.pImage);
        holder.pImage.setMaxHeight(products.getImage().getHeight());
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView pDesc , pPrice ;
        public ImageView pImage;

        public Holder(final View itemView) {
            super(itemView);

            pDesc = itemView.findViewById(R.id.tvDesc);
            pPrice = itemView.findViewById(R.id.tvPrice);
            pImage = itemView.findViewById(R.id.ivMyImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context , PreviewImage.class);
                    i.putExtra("url" , imageurl);
                    context.startActivity(i);
                }
            });
        }
    }

    public void addAll(List<Product> productsList) {
        this.productsList = productsList;
        notifyDataSetChanged();
    }

}
