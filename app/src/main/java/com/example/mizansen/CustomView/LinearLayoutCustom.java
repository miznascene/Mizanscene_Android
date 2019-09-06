package com.example.mizansen.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


public class LinearLayoutCustom extends LinearLayout {


    public LinearLayoutCustom(Context context, AttributeSet attis) {
        super(context, attis);

        this.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);

    }

}