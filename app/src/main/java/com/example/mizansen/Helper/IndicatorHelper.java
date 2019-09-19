package com.example.mizansen.Helper;


import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import com.example.mizansen.R;

public class IndicatorHelper {

    private static Dialog dialog;


    public void CreateIndicator(Context context) {

        dialog = new Dialog(context,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCancelable(false);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void DismissIndicator() {
        dialog.dismiss();
    }


}
