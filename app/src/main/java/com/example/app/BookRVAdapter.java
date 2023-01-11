package com.example.app;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookRVAdapter extends RecyclerView.Adapter<BookRVAdapter.bookviewholder> {

    private Context context;
    private List<BookModal> mdata;
    BookCallback callback;



    public BookRVAdapter(List<BookModal> mdata, Context context, BookCallback callback) {
        this.context=context;
        this.mdata = mdata;
        this.callback = callback;
    }

    @NonNull
    @Override
    public bookviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_rv_item, parent,false);

        return new bookviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookRVAdapter.bookviewholder holder, int position) {
        BookModal bookModal = mdata.get(position);
        holder.book_title.setText(bookModal.getBook_name());
        holder.book_author.setText(bookModal.getBook_author());
        holder.language_id.setText(bookModal.getLang_code());

        holder.publish_year.setText(String.valueOf(bookModal.getPublish_year()));
        holder.total_rating.setText(String.valueOf(bookModal.getTotal_rating()));

        holder.ratingBar.setRating(bookModal.getRating());

        Picasso.get().load(bookModal.getImage_url()).into(holder.book_cover);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class bookviewholder extends RecyclerView.ViewHolder{

        TextView book_title, book_author, total_rating, publish_year, language_id;
        RatingBar ratingBar;
        ImageView book_cover, favbutton;
        RelativeLayout relativeLayout;

        public bookviewholder(@NonNull View itemView) {
            super(itemView);

            book_cover=itemView.findViewById(R.id.bookcover);
            book_title=itemView.findViewById(R.id.bookitemtitle);
            book_author=itemView.findViewById(R.id.bookitemauthor);
            total_rating=itemView.findViewById(R.id.bookitemtotalrating);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            publish_year=itemView.findViewById(R.id.publicationyear);
            language_id=itemView.findViewById(R.id.language);
            relativeLayout = itemView.findViewById((R.id.panel));
            favbutton=itemView.findViewById(R.id.favbutton);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onBookItemClick(
                            getAdapterPosition(),
                            relativeLayout,
                            book_cover,
                            book_title,
                            book_author,
                            total_rating,
                            language_id,
                            publish_year,
                            ratingBar,
                            favbutton);
                }
            });

        }
    }
}
