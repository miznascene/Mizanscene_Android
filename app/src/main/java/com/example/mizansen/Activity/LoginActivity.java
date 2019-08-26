package com.example.mizansen.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mizansen.Network.ModelNetwork.AccountModel;
import com.example.mizansen.Network.ModelNetwork.InputLoginModel;
import com.example.mizansen.Network.RequestBuilder;
import com.example.mizansen.Network.RequestBuilderClass;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {

    EditText username, password;
    Button login;
    String TAG = "TAG_Login_Activity";
    OtherMetod om = new OtherMetod();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        password.setText("@dminRB1370");
        username.setText("mizanscene");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!username.getText().toString().equals("") && password.length() > 6) {
                    Login(username.getText().toString(), password.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    void Login(String username, String Password) {

        InputLoginModel loginModel = new InputLoginModel();

        loginModel.password = Password;
        loginModel.username = username;

        Call<AccountModel> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).login(loginModel);
        client.enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                Log.i(TAG, "Login received");

                    AccountModel accountModel = response.body();

                    try {
                        if (!accountModel.token.equals("")) {
                            Log.i(TAG, "Token is :" + accountModel.token);
                            om.SetSharedPreferences("Token",accountModel.token,LoginActivity.this);
                            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception e){
                        Log.i(TAG,e.toString());
                    }

            }

            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {
                Log.i(TAG,t.toString());
            }
        });
    }

    void init() {
        username = findViewById(R.id.activitylogin_username);
        password = findViewById(R.id.activitylogin_password);
        login = findViewById(R.id.activitylogin_login);
    }

}
