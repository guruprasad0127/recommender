package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsRVAdapter extends RecyclerView.Adapter<NewsRVAdapter.ViewHolder> {

    private ArrayList<Articles> articlesArrayList;
    private Context context;

    public NewsRVAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv_item,parent,false);
        return new NewsRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRVAdapter.ViewHolder holder, int position) {
        Articles articles = articlesArrayList.get(position);
        holder.subTitleRV.setText(articles.getDescription());
        holder.titleRV.setText(articles.getTitle());

        Picasso.get().load(articles.getUrlToImage()).into(holder.newsimageRV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, NewsInDetail.class);
                i.putExtra("title",articles.getTitle());
                i.putExtra("content",articles.getContent());
                i.putExtra("desc",articles.getDescription());
                i.putExtra("image",articles.getUrlToImage());
                i.putExtra("url",articles.getUrl());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleRV, subTitleRV;

        private ImageView newsimageRV;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            titleRV=itemView.findViewById(R.id.RVnewsheadline);
            subTitleRV=itemView.findViewById(R.id.RVnewsdescription);
            newsimageRV=itemView.findViewById(R.id.RVnewsimage);
        }
    }
}
