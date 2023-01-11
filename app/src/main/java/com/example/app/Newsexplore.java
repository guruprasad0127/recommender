package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// - newsorg api key

public class Newsexplore extends AppCompatActivity implements CategoryRVAdapter.CategoryclickInterface{

    private RecyclerView newsRV, categoryRV;
    private ProgressBar loadingPB;

    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryModal> categoryModalArrayList;

    private CategoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;

    private RelativeLayout profiletab, newstab, booktab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newexplore);

        categoryRV = findViewById(R.id.RVcategories);
        newsRV = findViewById(R.id.RVnews);
        loadingPB = findViewById(R.id.newsexploreprogressbar);

        categoryModalArrayList = new ArrayList<>();
        articlesArrayList = new ArrayList<>();

        newsRVAdapter = new NewsRVAdapter(articlesArrayList, this);
        categoryRVAdapter = new CategoryRVAdapter(categoryModalArrayList, this, this::onCategoryClick);
        newsRV.setAdapter(newsRVAdapter);
        categoryRV.setAdapter(categoryRVAdapter);

        getCategories();
        getNews("All");
        newsRVAdapter.notifyDataSetChanged();

        profiletab=findViewById(R.id.profiletabfromnews);
        newstab=findViewById(R.id.newstabfromnews);
        booktab=findViewById(R.id.booktabfromnews);

        profiletab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Newsexplore.this, Profile.class);
                startActivity(i);
            }
        });
        newstab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Newsexplore.this, Newsexplore.class);
                startActivity(i);
            }
        });
        booktab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Newsexplore.this, Bookfeed.class);
                startActivity(i);
            }
        });
    }

    private void getCategories(){
        categoryModalArrayList.add(new CategoryModal("All","https://images.unsplash.com/photo-1497008386681-a7941f08011e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=80"));
        categoryModalArrayList.add(new CategoryModal("Recommend","https://image.freepik.com/free-photo/successful-business-people-with-thumbs-up-smiling-business-team_43314-858.jpg"));
        categoryModalArrayList.add(new CategoryModal("Technology","https://images.unsplash.com/photo-1518770660439-4636190af475?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"));
        categoryModalArrayList.add(new CategoryModal("Science","https://images.unsplash.com/photo-1532187863486-abf9dbad1b69?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"));
        categoryModalArrayList.add(new CategoryModal("Sports","https://images.unsplash.com/photo-1583051663501-de1f00bd6ad4?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80"));
        categoryModalArrayList.add(new CategoryModal("General","hhttps://images.unsplash.com/photo-1591696205602-2f950c417cb9?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80ttps://images.unsplash.com/photo-1598611836407-1be63d8390a1?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"));
        categoryModalArrayList.add(new CategoryModal("Business","https://images.unsplash.com/photo-1591696205602-2f950c417cb9?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"));
        categoryModalArrayList.add(new CategoryModal("Entertainment","https://images.unsplash.com/photo-1603190287605-e6ade32fa852?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"));
        categoryModalArrayList.add(new CategoryModal("Health","https://images.unsplash.com/photo-1506126613408-eca07ce68773?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=431&q=80"));
        categoryRVAdapter.notifyDataSetChanged();
    }

    private void getNews(String category){
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryURL = "https://newsapi.org/v2/top-headlines?country=in&language=en&category="+ category+"&apiKey=1d0e9a1ffa4b4c0db594cd7976bcf113";
        String url = "https://newsapi.org/v2/top-headlines?country=in&language=en&sortBy=publishedAt&apiKey=1d0e9a1ffa4b4c0db594cd7976bcf113";
        String BASE_URL="https://newsapi.org/";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);


        Call<NewsModal> call;

        if(category.equals("All"))
            call=retrofitAPI.getAllNews(url);
        else
            call=retrofitAPI.getNewsByCategory(categoryURL);

        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal = response.body();
                loadingPB.setVisibility((View.GONE));
                ArrayList<Articles> articles = newsModal.getArticles();
                for(int i=0; i<articles.size(); i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getDescription()));
                }
                newsRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(Newsexplore.this, "Failed to get news", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onCategoryClick(int position) {
        String category = categoryModalArrayList.get(position).getCategoryText();
        getNews(category);
    }
}