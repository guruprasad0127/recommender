package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class Bookfeed extends AppCompatActivity implements BookCallback{


    private RecyclerView rvbookfeed;
    private List<BookModal> mdata;
    private BookRVAdapter bookRVAdapter;

    private RelativeLayout profiletab, newstab, booktab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookfeed);

        mdata = new ArrayList<>();

        rvbookfeed = findViewById(R.id.recyclerView);
        rvbookfeed.setHasFixedSize(true);

        bookRVAdapter = new BookRVAdapter(mdata, this,this);
        rvbookfeed.setAdapter(bookRVAdapter);


        additemsfromjason();
        bookRVAdapter.notifyDataSetChanged();

        profiletab=findViewById(R.id.profiletabfrombooks);
        newstab=findViewById(R.id.newstabfrombooks);
        booktab=findViewById(R.id.booktabfrombooks);

        profiletab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bookfeed.this, Profile.class);
                startActivity(i);
            }
        });
        newstab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bookfeed.this, Newsexplore.class);
                startActivity(i);
            }
        });
        booktab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bookfeed.this, Bookfeed.class);
                startActivity(i);
            }
        });
    }

    private void additemsfromjason(){

        try {
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);
            Log.d("mytag","loading from json completed");

            for (int i=0; i<jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                String name = itemObj.getString("title");
                String author = itemObj.getString("authors");
                String image_url = itemObj.getString("image_url");
                String lang_code = itemObj.getString("language_code");

                int publish_year = itemObj.getInt("original_publication_year");
                int total_rating = itemObj.getInt("ratings_count");

                float rating = (float)itemObj.getDouble("average_rating");

                BookModal bookModal = new BookModal(name,author,image_url,lang_code, publish_year, total_rating,rating);
                mdata.add(bookModal);
                Log.d("mytag",bookModal.getBook_name());
            }


        } catch (JSONException | IOException e) {
            Log.d("mytag","Error in loading from json");
            Log.d("mytag", e.toString());
        }
        bookRVAdapter.notifyDataSetChanged();
    }

    private String readJSONDataFromFile() throws IOException {

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {

            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.recommended_books);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }

    @Override
    public void onBookItemClick(int pos,
                                RelativeLayout imgcontainer,
                                ImageView book_cover,
                                TextView title,
                                TextView author,
                                TextView totalrating,
                                TextView lang_code,
                                TextView publish_year,
                                RatingBar ratingBar,
                                ImageView favbutton) {

        Intent intent = new Intent(this, BookDetail_Activity.class);
        intent.putExtra("bookObject", mdata.get(pos));


        Pair<View, String> p1 = Pair.create((View)imgcontainer,"panelTN");
        Pair<View, String> p2 = Pair.create((View)book_cover,"bookcoverTN");
        Pair<View, String> p3 = Pair.create((View)title,"booknameTN");
        Pair<View, String> p4 = Pair.create((View)author,"bookauthorTN");
        Pair<View, String> p5 = Pair.create((View)totalrating,"totalratingTN");
        Pair<View, String> p6 = Pair.create((View)lang_code,"langaugeTN");
        Pair<View, String> p7 = Pair.create((View)publish_year,"panelTN");
        Pair<View, String> p8 = Pair.create((View)ratingBar,"ratingbarTN");
        Pair<View, String> p9 = Pair.create((View)favbutton,"favbuttonTN");

        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this,p1,p2,p3,p4,p5,p6,p7,p8,p9);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent,optionsCompat.toBundle());
        }
        else
            startActivity(intent);
    }
}

