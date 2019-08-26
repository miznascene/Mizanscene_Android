package com.example.mizansen.CustomView;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.mizansen.OtherClass.OtherMetod;

public class TextViewCustom extends AppCompatTextView {


    public TextViewCustom(Context context, AttributeSet attis) {
        super(context, attis);
        OtherMetod om = new OtherMetod();

        this.setTypeface(om.SetFont(context, "IRANSans"));

    }

}