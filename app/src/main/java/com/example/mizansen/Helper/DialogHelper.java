package com.example.mizansen.Helper;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.example.mizansen.R;

public class DialogHelper {

    public static void singoutAccount(final Context context) {
        final Dialog dialog = new Dialog(context, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_outputaccunt);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        //************************************************


    }
}
