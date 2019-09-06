package com.example.mizansen.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.mizansen.OtherClass.OtherMetod;

public class TextViewCustom extends AppCompatTextView {


    public TextViewCustom(Context context, AttributeSet attis) {
        super(context, attis);
        OtherMetod om = new OtherMetod();


//        int Gravity;
//
//        if (View.LAYOUT_DIRECTION_LOCALE == View.LAYOUT_DIRECTION_LTR) {
//            Gravity = View.LAYOUT_DIRECTION_RTL;
//        } else {
//            Gravity = View.LAYOUT_DIRECTION_LTR;
//        }
//
//        this.setGravity(Gravity);

        this.setTypeface(om.SetFont(context, "IRANSans"));

    }

}