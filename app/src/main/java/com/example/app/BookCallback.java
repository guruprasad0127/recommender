package com.example.app;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public interface BookCallback {

    void onBookItemClick(int pos,
                         RelativeLayout imgcontainer,
                         ImageView book_cover,
                         TextView title,
                         TextView author,
                         TextView totalrating,
                         TextView lang_code,
                         TextView publish_year,
                         RatingBar ratingBar,
                         ImageView favbutton);
}
