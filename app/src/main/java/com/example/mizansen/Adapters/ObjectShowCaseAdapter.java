package com.example.mizansen.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mizansen.Activity.MovieActivity;
import com.example.mizansen.Network.ModelNetwork.TermMoviesModel;
import com.example.mizansen.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ObjectShowCaseAdapter extends RecyclerView.Adapter<ObjectShowCaseAdapter.ViewHolder> {

    List<TermMoviesModel> moviesModels;
    String TAG = "TAG_CategoryAdapter";
    Context context;

    public ObjectShowCaseAdapter(List<TermMoviesModel> _moviesModels, Context _context) {
        moviesModels = _moviesModels;
        context = _context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_showmovies, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        TermMoviesModel mm = moviesModels.get(i);

        if (mm.image.equals(null))
            mm.image = "https://mizanscene.com/wp-content/uploads/2019/08/hamase-ghahreman-wpv_290x430.jpg";


        try {
            Picasso.with(holder.image.getContext())
                    .load(mm.image)
                    .fit()
                    .into(holder.image);
        } catch (Exception e) {
            Log.i(TAG, "Error :" + e.toString());
            Picasso.with(holder.image.getContext())
                    .load("https://mizanscene.com/wp-content/uploads/2019/09/template-2.png")
                    .fit()
                    .into(holder.image);
        }

        holder.title_fa.setText(mm.title);


    }

    @Override
    public int getItemCount() {
        return moviesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title_fa;
        RoundedImageView image;

        public ViewHolder(@NonNull View iv) {
            super(iv);

            title_fa = iv.findViewById(R.id.adapter_showmovies_titlefa);
            image = iv.findViewById(R.id.adapter_showmovies_imageView1);


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
