package com.example.mizansen.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    static SharedPreferences sp;
    static String packages = "com.example.mizansen", TAG = "TAG_OtherMetod";

    public static void SetSharedPreferences(String name, String code, Context context) {
        sp = context.getSharedPreferences(packages, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(name, code);
        edit.commit();
    }

    public static String GetSharedPreferences(String name, String Null, Context context) {
        sp = context.getSharedPreferences(packages, 0);
        return sp.getString(name, Null);
    }



}
