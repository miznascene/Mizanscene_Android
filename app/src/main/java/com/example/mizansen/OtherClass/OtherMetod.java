package com.example.mizansen.OtherClass;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;


public class OtherMetod {

    SharedPreferences sp;
    String packages = "com.example.mizansen", TAG = "TAG_OtherMetod";


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
