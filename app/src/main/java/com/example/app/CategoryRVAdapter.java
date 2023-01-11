package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder> {

    private ArrayList<CategoryModal> categoryModals;
    private Context context;
    private CategoryclickInterface categoryclickInterface;

    public CategoryRVAdapter(ArrayList<CategoryModal> categoryModals, Context context, CategoryclickInterface categoryclickInterface) {
        this.categoryModals = categoryModals;
        this.context = context;
        this.categoryclickInterface = categoryclickInterface;
    }

    @NonNull
    @Override
    public CategoryRVAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv_item,parent,false);
        return new CategoryRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRVAdapter.ViewHolder holder, int position) {
        CategoryModal categoryModal = categoryModals.get(position);
        holder.categoryText.setText(categoryModal.getCategoryText());
        Picasso.get().load(categoryModal.getCategoryImageUrl()).into(holder.categoryImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryclickInterface.onCategoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModals.size();
    }
    public interface CategoryclickInterface{
        void onCategoryClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryText;
        private ImageView categoryImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryText=itemView.findViewById(R.id.RVcategorytext);
            categoryImage= itemView.findViewById(R.id.RVcategoryimage);
        }
    }
}
