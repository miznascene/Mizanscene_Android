package com.example.mizansen.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Helper.LocaleHelper;
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
    TextView Register, Forgotpass;
    String TAG = "TAG_LoginActivity";
    OtherMetod om = new OtherMetod();
    LanguageHelper languageHelper = new LanguageHelper();


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "fa"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToVerifiyCodePage("register");
            }
        });

        Forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToVerifiyCodePage("forgotpass");
            }
        });

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

    void chekLogin() {
        String check = om.GetSharedPreferences("chek_login", "0", LoginActivity.this);
        om.SetSharedPreferences("chek_login", String.valueOf(Integer.parseInt(check) + 1), LoginActivity.this);
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
                        om.SetSharedPreferences("Token", accountModel.token, LoginActivity.this);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        //failed
                    }
                } catch (Exception e) {
                    Log.i(TAG, e.toString());
                }

            }

            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {
                Log.i(TAG, t.toString());
            }
        });
    }

    void init() {
        username = findViewById(R.id.activitylogin_username);
        password = findViewById(R.id.activitylogin_password);
        login = findViewById(R.id.activitylogin_login);
        Forgotpass = findViewById(R.id.activitylogin_forgotpass);
        Register = findViewById(R.id.activitylogin_register);


        languageHelper.GetLanguage(LoginActivity.this);


        chekLogin();
    }

    void goToVerifiyCodePage(String key) {
        Intent intent = new Intent(LoginActivity.this, SendEmailActivity.class);
        intent.putExtra("typePage", key);
        startActivity(intent);
    }

}
