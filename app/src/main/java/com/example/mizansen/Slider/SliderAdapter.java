package com.example.mizansen.Slider;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mizansen.Network.ModelNetwork.SlideshowModel;
import com.example.mizansen.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;
    private int mCount;
    List<SlideshowModel> slideshow;

    public SliderAdapter(Context context, List<SlideshowModel> SlidShow) {
        this.context = context;
        this.slideshow = SlidShow;
    }

    public void setCount(int count) {
        this.mCount = count;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout_item_image, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        SlideshowModel sm = slideshow.get(position);

        viewHolder.textViewDescription.setText("" + sm.slideshow_text);
        viewHolder.textViewDescription.setTextSize(16);
        viewHolder.textViewDescription.setTextColor(Color.WHITE);
        viewHolder.imageGifContainer.setVisibility(View.GONE);

        Picasso.with(context)
                .load(sm.slideshow_image)
                .fit()
                .into(viewHolder.imageViewBackground);

//                viewHolder.textViewDescription.setTextSize(29);
//                viewHolder.textViewDescription.setTextColor(Color.WHITE);
//                viewHolder.textViewDescription.setText("Ohhhh! look at this!");
//                viewHolder.imageGifContainer.setVisibility(View.VISIBLE);
//
//                Glide.with(viewHolder.itemView)
//                        .load(R.drawable.puma_offer)
//                        .fitCenter()
//                        .into(viewHolder.imageViewBackground);
//
//                Glide.with(viewHolder.itemView)
//                        .asGif()
//                        .load(R.drawable.oh_look_at_this)
//                        .into(viewHolder.imageGifContainer);


    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mCount;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }


}
