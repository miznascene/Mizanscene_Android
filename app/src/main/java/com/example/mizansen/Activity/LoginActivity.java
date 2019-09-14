package com.example.mizansen.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Helper.LocaleHelper;
import com.example.mizansen.Helper.MessageHelper;
import com.example.mizansen.Helper.RequestHelper;
import com.example.mizansen.Helper.ValidationHelper;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;



public class LoginActivity extends Activity {

    EditText username, password;
    Button login;
    TextView Register, Forgotpass;
    String TAG = "TAG_LoginActivity";
    OtherMetod om = new OtherMetod();
    LanguageHelper languageHelper = new LanguageHelper();
    RequestHelper requstHelper = new RequestHelper();


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
                goToSendcodeCodePage("register");
            }
        });

        Forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSendcodeCodePage("reset_password");
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ValidationHelper.validEmail(username.getText().toString())) {
                    if (ValidationHelper.validPasswordByLenght(password.getText().toString(), password.getText().toString())) {
                        Login(username.getText().toString(), password.getText().toString());
                    } else {
                        MessageHelper.Snackbar(LoginActivity.this, getResources().getString(R.string.password_count), "");
                    }

                } else {
                    MessageHelper.Snackbar(LoginActivity.this, getResources().getString(R.string.ErrorValidEmail), "");
                }
            }
        });


    }

    void chekLogin() {
        String check = om.GetSharedPreferences("chek_login", "0", LoginActivity.this);
        om.SetSharedPreferences("chek_login", String.valueOf(Integer.parseInt(check) + 1), LoginActivity.this);
    }

    void Login(String Email, String Password) {

        requstHelper.Login(Email, Password, getResources().getString(R.string.API_Login), LoginActivity.this);

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

    void goToSendcodeCodePage(String key) {
        Intent intent = new Intent(LoginActivity.this, SendEmailActivity.class);
        intent.putExtra("typePage", key);
        startActivity(intent);
        finish();
    }

}
