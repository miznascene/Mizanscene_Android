package com.example.mizansen.OtherClass;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;

import java.util.regex.Pattern;


public class OtherMetod {

    SharedPreferences sp;
    String packages = "com.example.mizansen", TAG = "TAG_OtherMetod";
    public String ApiKey = "23jel` uQ%sBNzimt?eXu%+3eB<|e*^H:LDxzszU><Q/`$^/<<3d#,!Z@l_:[Og2";
    public String SecretKey = "+a1M0%bg,))|+QP7=pT{rW.WLh8)2GQb;|p|+^K%3r3o6I26tm?TF@ciP-;:--*9";


    public boolean validEmail(String Email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (Email == null)
            return false;
        return pat.matcher(Email).matches();

    }

    public static boolean validStatus(int status) {

        if (status == 200) {
            return true;
        } else {
            return false;
        }

    }

    public void SetSharedPreferences(String name, String code, Context context) {
        sp = context.getSharedPreferences(packages, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(name, code);
        edit.commit();
    }

    public String GetSharedPreferences(String name, String Null, Context context) {
        sp = context.getSharedPreferences(packages, 0);
        return sp.getString(name, Null);
    }

    public Typeface SetFont(Context context, String name) {
        return Typeface.createFromAsset(context.getAssets(), "font/" + name + ".ttf");
    }

    public String GetVer(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(packages, 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }


}
