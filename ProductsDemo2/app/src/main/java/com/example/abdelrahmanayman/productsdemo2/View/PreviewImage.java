package com.example.abdelrahmanayman.productsdemo2.View;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.abdelrahmanayman.productsdemo2.R;
import com.squareup.picasso.Picasso;


public class PreviewImage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_image);

        String url = getIntent().getStringExtra("url");
        ImageView imageView = findViewById(R.id.ivImagePreview);
        Picasso.with(this).load(Uri.parse(url)).into(imageView);
    }
}
