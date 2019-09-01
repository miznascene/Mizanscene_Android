package com.example.mizansen;

import android.app.Application;
import android.content.Context;

import com.example.mizansen.Helper.LocaleHelper;

public class MainApplication extends Application {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base,"fa"));
    }
}
