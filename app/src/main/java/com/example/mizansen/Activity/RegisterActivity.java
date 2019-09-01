package com.example.mizansen.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Helper.LocaleHelper;
import com.example.mizansen.R;

public class RegisterActivity extends Activity {

    LanguageHelper languageHelper = new LanguageHelper();


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "fa"));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    void initView(){
        languageHelper.GetLanguage(RegisterActivity.this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
