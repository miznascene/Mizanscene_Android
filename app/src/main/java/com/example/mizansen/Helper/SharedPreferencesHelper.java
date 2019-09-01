package com.example.mizansen.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

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



}
