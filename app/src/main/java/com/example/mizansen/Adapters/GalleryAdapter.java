package com.example.mizansen.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.Activity.AgentsActivity;
import com.example.mizansen.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {


    String TAG = "TAG_AgentAdapter";
    Context context;

    public GalleryAdapter(Context _context) {
        context = _context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_gallery, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Picasso.with(context)
                .load("https://mizanscene.com/wp-content/uploads/2019/06/nahid-poster-wpv_290x430.jpg")
                .fit()
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

            image = iv.findViewById(R.id.adapter_gallery_image);
            iv.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }

    }

}
