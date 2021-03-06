package com.example.mizansen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.mizansen.Activity.LoginActivity;
import com.example.mizansen.Activity.Hintro_Activity;
import com.example.mizansen.Activity.MainActivity;
import com.example.mizansen.Helper.JsonHelper;
import com.example.mizansen.Helper.RequestHelper;
import com.example.mizansen.Helper.SharedPreferencesHelper;
import com.example.mizansen.Helper.ValidationHelper;
import com.example.mizansen.Network.ModelNetwork.ErrorModel;
import com.example.mizansen.OtherClass.OtherMetod;

import java.io.IOException;


public class SplashScreenActivity extends Activity {

    OtherMetod om = new OtherMetod();
    String TAG = "TAG_SplashScreenActivity";
    RequestHelper requstHelper = new RequestHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ValiDateToken();
    }

    void ValiDateToken() {

        String Token = SharedPreferencesHelper.GetSharedPreferences("Token", "null", SplashScreenActivity.this);

        Log.i(TAG, "Token " + Token);
        if (Token.equals("null")) {
            // firt start app => goto Login
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    goToPageLogin();
                }
            }, 2000);

        } else {
            Intent intent;
            Token = "Bearer " + Token;
            try {

                if (ValidationHelper.validInternetConnection(SplashScreenActivity.this)) {
                    requstHelper.Validation(Token, getResources().getString(R.string.API_Validation), SplashScreenActivity.this);
                } else {
                    Log.i(TAG, "Internet not connected");
                    intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } catch (IOException e) {
                e.printStackTrace();
                intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }


        }

    }

    void goToPageLogin() {

        String check = om.GetSharedPreferences("chek_login", "0", SplashScreenActivity.this);

        if (check.equals("0")) {
            startActivity(new Intent(SplashScreenActivity.this, Hintro_Activity.class));
            finish();

        } else {
            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            finish();

        }
    }

    public static void ResultRequst(String json, Context context) {

        ErrorModel errorModel = JsonHelper.ConvertStringToErrorModel(json);

        Intent intent = new Intent(context, LoginActivity.class);

        try {
            if (ValidationHelper.validStatus(errorModel.status)) {
                //go to MainPage
                intent = new Intent(context, MainActivity.class);
            }
        } catch (Exception e) {

        }


        ((Activity) context).startActivity(intent);
        ((Activity) context).finish();


    }


}
