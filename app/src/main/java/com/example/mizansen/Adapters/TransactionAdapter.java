package com.example.mizansen.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.R;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {


    String TAG = "TAG_CategoryAdapter";
    Context context;

    public TransactionAdapter(Context _context) {

        context = _context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_transaction, parent, false);
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

        TextView title, more;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View iv) {
            super(iv);

            title = iv.findViewById(R.id.adapter_showcase_row_title);
            more = iv.findViewById(R.id.adapter_showcase_row_more);
            recyclerView = iv.findViewById(R.id.adapter_showcase_row_recycler);


            iv.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
//            int i = getAdapterPosition();
//
//            Intent intent = new Intent(((Activity) context), MovieActivity.class);
//            intent.putExtra("movie_id", String.valueOf(moviesModels.get(i).id));
//            context.startActivity(intent);
//            ((Activity) context).finish();
//
//            Log.i(TAG, "Clicked item title is " + moviesModels.get(i).id);


        }
    }

}
