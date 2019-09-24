package com.example.mizansen.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.Network.ModelNetwork.TermMoviesModel;
import com.example.mizansen.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AgentAdapter extends RecyclerView.Adapter<AgentAdapter.ViewHolder> {


    String TAG = "TAG_AgentAdapter";
    Context context;
    public List<TermMoviesModel> term_movies = new ArrayList<>();

    public AgentAdapter(Context _context) {
        context = _context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_agent, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {


        Picasso.with(context)
                .load("https://pbs.twimg.com/media/EDEsh0gU4AUTO3P?format=jpg&name=900x900")
                .placeholder( R.drawable.progress_animation )
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView image;

        public ViewHolder(@NonNull View iv) {
            super(iv);

            image = iv.findViewById(R.id.adapter_agent_imagemovie);

        }

    }

}