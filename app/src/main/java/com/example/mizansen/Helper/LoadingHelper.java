package com.example.mizansen.Helper;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.example.mizansen.R;

public class LoadingHelper {

    private static Dialog dialog;


    public void CreateLoading(Context context) {
        dialog = new Dialog(context, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void DismissDialog() {
        dialog.dismiss();
    }


}
