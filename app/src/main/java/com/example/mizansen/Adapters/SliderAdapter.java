package com.example.mizansen.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.mizansen.Network.ModelNetwork.SlideshowModel;
import com.example.mizansen.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;


import java.util.List;

public class SliderAdapter extends PagerAdapter {


    List<SlideshowModel> slideshow;
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(List<SlideshowModel> slideshow, Context context) {

        this.slideshow = slideshow;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return slideshow.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = layoutInflater.inflate(R.layout.adapter_slidre, container, false);

        Log.i("TAG_","image : "+slideshow.get(position).slideshow_image);
        RoundedImageView imageView = (RoundedImageView) view.findViewById(R.id.image_slider);
        Picasso.with(context)
                .load(slideshow.get(position).slideshow_image)
                .fit()
                .into(imageView);

        imageView.setImageResource(R.drawable.puma_offer);
        container.addView(view);
        return view;
    }
}
