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
import com.example.mizansen.Activity.MovieActivity;
import com.example.mizansen.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AgentMovieAdapter extends RecyclerView.Adapter<AgentMovieAdapter.ViewHolder> {


    String TAG = "TAG_AgentAdapter";
    Context context;

    public AgentMovieAdapter(Context _context) {
        context = _context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_agentmovie, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Picasso.with(context)
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Asghar_Farhadi_Cannes_2013.jpg/350px-Asghar_Farhadi_Cannes_2013.jpg")
                .fit()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CircleImageView image;
        TextView name, side;

        public ViewHolder(@NonNull View iv) {
            super(iv);

            image = iv.findViewById(R.id.adapter_agentmovie_image);
            iv.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ((Activity) context).startActivity(new Intent(context, AgentsActivity.class));
        }

    }

}