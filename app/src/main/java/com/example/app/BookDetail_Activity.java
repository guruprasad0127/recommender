package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BookDetail_Activity extends AppCompatActivity {

    Button button;
    TextView book_title, book_author, total_rating, publish_year, language_id;
    ImageView book_cover, favbutton;
    RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        BookModal item = (BookModal) getIntent().getExtras().getSerializable("bookObject");

        book_title=findViewById(R.id.bookitemtitle);
        book_author=findViewById(R.id.bookitemauthor);
        total_rating=findViewById(R.id.bookitemtotalrating);
        publish_year=findViewById(R.id.publicationyear);
        language_id=findViewById(R.id.language);
        ratingBar=findViewById(R.id.ratingBar);
        book_cover=findViewById(R.id.bookcover);
        favbutton=findViewById(R.id.favbutton);

        book_title.setText(item.getBook_name());
        book_author.setText(item.getBook_author());
        total_rating.setText(String.valueOf(item.getTotal_rating()));
        publish_year.setText(String.valueOf(item.getPublish_year()));
        language_id.setText(item.getLang_code());
        ratingBar.setRating((float)item.getRating());
        Picasso.get().load(item.getImage_url()).into(book_cover);


        button = findViewById(R.id.linkbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String book_for_url =String.valueOf(item.getBook_name());
                book_for_url=book_for_url.replace(' ','+');
                String url = "https://www.amazon.in/s?k="+book_for_url+"&i=stripbooks&ref=nb_sb_noss";

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

}