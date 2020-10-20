package com.example.mizansen.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class SearchAdaptor extends RecyclerView.Adapter<SearchAdaptor.ViewHolder> {


    String TAG = "TAG_SearchAdaptor";
    Context context;

    public SearchAdaptor(Context _context) {

        context = _context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_search, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Picasso.with(context)
                .load("https://www.uptvs.net/wp-content/uploads/2018/04/texas-min.jpg")
                .placeholder( R.drawable.progress_animation )
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RoundedImageView image;

        public ViewHolder(@NonNull View iv) {
            super(iv);

            image = iv.findViewById(R.id.adapter_search_imagemovie);

            iv.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {



        }
    }

}
