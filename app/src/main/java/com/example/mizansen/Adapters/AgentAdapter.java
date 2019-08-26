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

public class AgentAdapter extends RecyclerView.Adapter<AgentAdapter.ViewHolder> {


    String TAG = "TAG_AgentAdapter";
    Context context;

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
                .load("https://mizanscene.com/wp-content/uploads/2019/08/hamase-ghahreman-wpv_290x430.jpg")
                .fit()
                .into(holder.image_right);

        Picasso.with(context)
                .load("https://mizanscene.com/wp-content/uploads/2019/08/hamase-ghahreman-wpv_290x430.jpg")
                .fit()
                .into(holder.image_center);

        Picasso.with(context)
                .load("https://mizanscene.com/wp-content/uploads/2019/08/hamase-ghahreman-wpv_290x430.jpg")
                .fit()
                .into(holder.image_left);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView image_right, image_center, image_left;

        public ViewHolder(@NonNull View iv) {
            super(iv);

            image_right = iv.findViewById(R.id.adapter_agent_imagemovieright);
            image_center = iv.findViewById(R.id.adapter_agent_imagemoviecenter);
            image_left = iv.findViewById(R.id.adapter_agent_imagemovieleft);

        }

    }

}