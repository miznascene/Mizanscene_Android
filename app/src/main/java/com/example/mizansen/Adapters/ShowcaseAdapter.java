package com.example.mizansen.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.Network.ModelNetwork.MainpageModel;
import com.example.mizansen.Network.ModelNetwork.TermModel;
import com.example.mizansen.R;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class ShowcaseAdapter extends RecyclerView.Adapter<ShowcaseAdapter.ViewHolder> {

    MainpageModel mainpageModels;
    String TAG = "TAG_CategoryAdapter";
    Context context;

    public ShowcaseAdapter(MainpageModel _mainpageModels, Context _context) {
        mainpageModels = _mainpageModels;
        context = _context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_showmovis_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {

        holder.sliderView.setVisibility(View.GONE);
        if (i == 0) {

            holder.sliderView.setVisibility(View.VISIBLE);
            holder.recyclerView.setVisibility(View.GONE);
            holder.title.setVisibility(View.GONE);
            holder.more.setVisibility(View.GONE);

            SliderAdapter sliderAdapter = new SliderAdapter(mainpageModels.data.slideshow,context);
            holder.sliderView.setAdapter(sliderAdapter);

        } else {
            TermModel mm = mainpageModels.data.terms.get(i - 1);
            holder.title.setText(mm.term_name);


            holder.more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });


            LinearLayoutManager LLM = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            holder.recyclerView.setLayoutManager(LLM);
            holder.recyclerView.setHasFixedSize(true);
            holder.recyclerView.setAdapter(new ObjectShowCaseAdapter(mm.term_movies, context));
        }


    }

    @Override
    public int getItemCount() {
        return mainpageModels.data.terms.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, more;
        RecyclerView recyclerView;
        HorizontalInfiniteCycleViewPager sliderView;


        public ViewHolder(@NonNull View iv) {
            super(iv);

            sliderView = (HorizontalInfiniteCycleViewPager) iv.findViewById(R.id.slider);
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
