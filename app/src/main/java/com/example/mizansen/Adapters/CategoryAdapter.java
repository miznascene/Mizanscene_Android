package com.example.mizansen.Adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.Activity.MainActivity;
import com.example.mizansen.Fragment.BottomBar.CategorySelectedFragment;
import com.example.mizansen.Helper.WidthAnimationHelper;
import com.example.mizansen.Network.ModelNetwork.CategoryDataModel;
import com.example.mizansen.Network.ModelNetwork.CategoryModel;
import com.example.mizansen.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    CategoryModel categoryModels;
    String TAG = "TAG_CategoryAdapter";
    Context context;


    public CategoryAdapter(CategoryModel _categoryModels, Context context) {
        this.categoryModels = _categoryModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_category, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        CategoryDataModel cm = categoryModels.data.get(i);
        int[] color_category = context.getResources().getIntArray(R.array.color_category);

        holder.title.setText(cm.term_name);

        Log.i(TAG, "i = " + (i % 8));
        int index = i % 8;

        holder._color.setBackgroundColor(color_category[index]);
        holder.more.setColorFilter(color_category[index]);
        holder.title.setTextColor(color_category[index]);


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!cm.anim_status) {
                    cm.anim_status = true;
                    AnimColor(holder._color, 1000, 500, holder.title, holder.more, R.color.white, cm.anim_status, cm.term_id);
                } else {
                    cm.anim_status = false;
                    AnimColor(holder._color, 55, 500, holder.title, holder.more, holder._color.getSolidColor(), cm.anim_status, cm.term_id);
                }


            }
        });

    }

    @Override
    public int getItemCount() {

        return categoryModels.data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, _color;
        RelativeLayout relativeLayout;
        ImageView more;

        public ViewHolder(@NonNull View iv) {
            super(iv);

            relativeLayout = iv.findViewById(R.id.adaptercategory_per_layout);
            title = iv.findViewById(R.id.adaptercategory_per_title);
            _color = iv.findViewById(R.id.adaptercategory_per_color);
            more = iv.findViewById(R.id.adaptercategory_per_more);


        }

    }

    void AnimColor(TextView object, int ReizeObject, int Duration, TextView Title, ImageView More, int Color, boolean status, int id) {
        WidthAnimationHelper anim = new WidthAnimationHelper(object, ReizeObject, status);
        anim.setDuration(Duration);
        object.startAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Title.setTextColor(Title.getContext().getResources().getColor(Color));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                MainActivity.setViewById(4);
                CategorySelectedFragment.getIdAndTitleCategory(0,Title.getText().toString());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

}