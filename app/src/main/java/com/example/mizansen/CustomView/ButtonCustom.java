package com.example.mizansen.CustomView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.text.HtmlCompat;

import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;

public class ButtonCustom extends AppCompatButton {

    String TAG = "TAG_ButtonCustom";

    @SuppressLint("WrongConstant")
    public ButtonCustom(Context context, AttributeSet attis) {
        super(context, attis);
        OtherMetod om = new OtherMetod();


        String fontName = "IRANSans";

        TypedArray attributes = context.obtainStyledAttributes(attis, R.styleable.ButtonCustom);

        String word = attributes.getString(R.styleable.ButtonCustom_btn_bold_word);
        String text = this.getText().toString();
//        fontName = attributes.getString(R.styleable.ButtonCustom_btn_font);
        attributes.recycle();


        try {

//            if (fontName.isEmpty()) {
//                fontName = "IRANSans";
//            }

            text = text.replace(word, "<b>" + word + "</b>");
        } catch (Exception e) {

        }

        this.setText(HtmlCompat.fromHtml(text, Typeface.BOLD));

        this.setTypeface(om.SetFont(context, fontName));

    }

}