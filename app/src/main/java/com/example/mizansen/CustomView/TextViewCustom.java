package com.example.mizansen.CustomView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.text.HtmlCompat;

import com.example.mizansen.Helper.LocaleHelper;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;

import java.util.Locale;

public class TextViewCustom extends AppCompatTextView {


    String TAG = "TAG_TextViewCustom";

    @SuppressLint("WrongConstant")
    public TextViewCustom(Context context, AttributeSet attis) {
        super(context, attis);
        OtherMetod om = new OtherMetod();

        TypedArray attributes = context.obtainStyledAttributes(attis, R.styleable.TextViewCustom);

        String word = attributes.getString(R.styleable.TextViewCustom_word_bold);

        boolean Gravity = attributes.getBoolean(R.styleable.TextViewCustom_inverse_gravity,false);

        String text = this.getText().toString();
        attributes.recycle();

        if (Gravity){

            if (LocaleHelper.getPeresistedData(context, Locale.getDefault().getLanguage()).equals("fa")) {
                this.setGravity(android.view.Gravity.LEFT);
            } else {
                this.setGravity(android.view.Gravity.RIGHT);
            }

        }


        try {
            text = text.replace(word, "<b>" + word + "</b>");
        } catch (Exception e) {

        }

        this.setText(HtmlCompat.fromHtml(text, Typeface.BOLD_ITALIC));
        this.setTypeface(om.SetFont(context, "IRANSans"));

    }

}