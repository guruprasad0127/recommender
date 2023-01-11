package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsInDetail extends AppCompatActivity {

    String title, content, desc, imageurl, url;
    TextView headline, contentview, descview;
    Button urlbutton;
    ImageView newsimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_in_detail);

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        desc = getIntent().getStringExtra("desc");
        url  = getIntent().getStringExtra("url");
        imageurl = getIntent().getStringExtra("image");


        headline=findViewById(R.id.RVnewsheadline);
        contentview=findViewById(R.id.newsdetail);
        descview=findViewById(R.id.RVnewsdescription);
        urlbutton=findViewById(R.id.linkbutton);
        newsimage=findViewById(R.id.RVnewsimage);

        headline.setText(title);
        contentview.setText(content);
        descview.setText(desc);
        Picasso.get().load(imageurl).into(newsimage);

        urlbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}