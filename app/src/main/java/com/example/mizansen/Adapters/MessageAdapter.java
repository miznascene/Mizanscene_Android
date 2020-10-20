package com.example.mizansen.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.R;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {


    String TAG = "TAG_MessageAdapter";
    Context context;

    public MessageAdapter(Context _context) {

        context = _context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_message, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public ViewHolder(@NonNull View iv) {
            super(iv);


            iv.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {



        }
    }

}
