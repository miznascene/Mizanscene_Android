package com.example.mizansen.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;


import com.example.mizansen.OtherClass.OtherMetod;

public class EditTextCustom extends AppCompatEditText {


    public EditTextCustom(Context context, AttributeSet attis) {
        super(context, attis);
        OtherMetod om = new OtherMetod();

        this.setTypeface(om.SetFont(context, "IRANSans"));

    }

}