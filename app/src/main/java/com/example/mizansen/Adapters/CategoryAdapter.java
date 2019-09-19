package com.example.mizansen.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.Network.ModelNetwork.CategoryModel;
import com.example.mizansen.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<CategoryModel> categoryModels;
    String TAG = "CategoryAdapter";

    public CategoryAdapter(List<CategoryModel> _categoryModels) {
        categoryModels = _categoryModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_category, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        CategoryModel cm = categoryModels.get(i);



        holder.title.setText(cm.Title);



    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView  title;
        ImageView imageView;
        public ViewHolder(@NonNull View iv) {
            super(iv);


            title = iv.findViewById(R.id.adaptercategory_per_title);


            iv.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            int i = getAdapterPosition();

            Log.i(TAG,"i"+i);
        }
    }

}