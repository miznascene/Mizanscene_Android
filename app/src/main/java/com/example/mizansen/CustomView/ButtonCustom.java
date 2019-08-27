package com.example.mizansen.CustomView;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.mizansen.OtherClass.OtherMetod;

public class ButtonCustom extends AppCompatButton {


    public ButtonCustom(Context context, AttributeSet attis) {
        super(context, attis);
        OtherMetod om = new OtherMetod();

        this.setTypeface(om.SetFont(context, "IRANSans"));

    }

}