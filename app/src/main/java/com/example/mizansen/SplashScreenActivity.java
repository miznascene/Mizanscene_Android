package com.example.mizansen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.mizansen.Activity.LoginActivity;
import com.example.mizansen.Activity.MainActivity;
import com.example.mizansen.Activity.Hintro_Activity;
import com.example.mizansen.Network.ModelNetwork.ValidationModel;
import com.example.mizansen.Network.RequestBuilder;
import com.example.mizansen.Network.RequestBuilderClass;
import com.example.mizansen.OtherClass.OtherMetod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashScreenActivity extends Activity {

    OtherMetod om = new OtherMetod();
    String TAG = "TAG_SplashScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ValiDateToken();
    }

    void ValiDateToken() {

        String Token = om.GetSharedPreferences("Token", "null", SplashScreenActivity.this);

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

            try {
                Call<ValidationModel> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).Validation("Bearer " + Token);
                client.enqueue(new Callback<ValidationModel>() {
                    @Override
                    public void onResponse(Call<ValidationModel> call, Response<ValidationModel> response) {
                        ValidationModel validationModel = response.body();

                        try {
                            Log.i(TAG, "splash received  code is " + validationModel.code);
                            if (validationModel.code.equals("jwt_auth_valid_token")) {
                                // Token is Valid => goto MainActivity
                                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                                finish();
                            } else {
                                // Token is not Valid => goto LoginActivity
                                goToPageLogin();
                            }
                        } catch (Exception e) {
                            Log.i(TAG, "Error try :" + e.toString());
                            goToPageLogin();
                        }

                    }

                    @Override
                    public void onFailure(Call<ValidationModel> call, Throwable t) {
                        Log.i(TAG, "splash failed: " + t.toString());
                        goToPageLogin();
                    }
                });
            } catch (Exception e) {
                Log.i(TAG, "splash error: " + e.toString());
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

}
