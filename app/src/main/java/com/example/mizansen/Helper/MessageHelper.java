package com.example.mizansen.Helper;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.mizansen.R;
import com.google.android.material.snackbar.Snackbar;


public class MessageHelper {

    //	        implementation 'com.androidadvance:topsnackbar:+'

    public static void Snackbar(Context context, String body, String Action) {

        TSnackbar snackbar = TSnackbar.make(((Activity) context).findViewById(android.R.id.content), body, TSnackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.WHITE);

//        snackbar.setIconLeft(R.drawable.ic_back, 24); //Size in dp - 24 is great!
//        snackbar.setIconRight(R.drawable.ic_bill, 48); //Resize to bigger dp
//        snackbar.setIconPadding(8);

        snackbar.setMaxWidth(Snackbar.LENGTH_LONG); //if you want fullsize on tablets
        View snackbarView = snackbar.getView();

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20 , 5, 20, 0);
        snackbarView.setLayoutParams(lp);

//        snackbarView.setBackgroundColor(context.getResources().getColor(R.color.colorBgItem));
        snackbarView.setBackground(context.getResources().getDrawable(R.drawable.messagestyle));
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);

        snackbar.show();

    }


}
