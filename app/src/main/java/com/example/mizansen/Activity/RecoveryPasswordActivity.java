package com.example.mizansen.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Helper.LocaleHelper;
import com.example.mizansen.R;

public class RecoveryPasswordActivity  extends Activity {


    LanguageHelper languageHelper = new LanguageHelper();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "fa"));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recoverypassword);

        initView();



    }

    void initView(){

        languageHelper.GetLanguage(RecoveryPasswordActivity.this);

    }
}
