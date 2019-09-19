package com.example.mizansen.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mizansen.Helper.JsonHelper;
import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Helper.LocaleHelper;
import com.example.mizansen.Helper.MessageHelper;
import com.example.mizansen.Helper.RequestHelper;
import com.example.mizansen.Helper.ValidationHelper;
import com.example.mizansen.Network.ModelNetwork.RegisterModel;
import com.example.mizansen.Network.ModelNetwork.ValidationModel;
import com.example.mizansen.R;

import java.io.IOException;

import okhttp3.FormBody;

public class RegisterActivity extends Activity {

    LanguageHelper languageHelper = new LanguageHelper();
    EditText firstName, lastName, password, passwordConfirm;
    Button submit;
    RequestHelper requstHelper = new RequestHelper();
    ValidationModel validationModel = new ValidationModel();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "fa"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ValidationHelper.validPassword(password.getText().toString(), passwordConfirm.getText().toString())) {

                    if (ValidationHelper.validPasswordByLenght(password.getText().toString(), passwordConfirm.getText().toString())) {
                        if (ValidationHelper.validFirstNameAndLastName(lastName.getText().toString())
                                && ValidationHelper.validFirstNameAndLastName(firstName.getText().toString())) {

                            try {
                                if (ValidationHelper.validInternetConnection(RegisterActivity.this)) {
                                    Register(firstName.getText().toString(), lastName.getText().toString(), password.getText().toString());
                                } else {
                                    MessageHelper.Snackbar(RegisterActivity.this, getResources().getString(R.string.ErrorValidInternet), "", R.color.red, R.drawable.messagestyle);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                MessageHelper.Snackbar(RegisterActivity.this, getResources().getString(R.string.ErrorValidInternet), "", R.color.red, R.drawable.messagestyle);
                            } catch (IOException e) {
                                e.printStackTrace();
                                MessageHelper.Snackbar(RegisterActivity.this, getResources().getString(R.string.ErrorValidInternet), "", R.color.red, R.drawable.messagestyle);
                            }

                        } else {
                            MessageHelper.Snackbar(RegisterActivity.this, getResources().getString(R.string.validation_firstnamelastname), "", R.color.red, R.drawable.messagestyle);
                        }
                    } else {
                        MessageHelper.Snackbar(RegisterActivity.this, getResources().getString(R.string.password_count), "", R.color.red, R.drawable.messagestyle);
                    }

                } else {
                    MessageHelper.Snackbar(RegisterActivity.this, getResources().getString(R.string.Same_password), "", R.color.red, R.drawable.messagestyle);
                }
            }
        });
    }

    void initView() {
        languageHelper.GetLanguage(RegisterActivity.this);
        passwordConfirm = findViewById(R.id.register_confirmpass);
        firstName = findViewById(R.id.register_firstname);
        lastName = findViewById(R.id.register_lastname);
        password = findViewById(R.id.register_pass);
        submit = findViewById(R.id.register_submit);

        Bundle bundle = getIntent().getExtras();
        String Json = bundle.getString("json");
        validationModel = JsonHelper.ConvertStringToValidationModel(Json);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    void Register(String first_name, String last_name, String password) {

        FormBody form = new FormBody.Builder()
                .add("user_id", String.valueOf(validationModel.data.user_id))
                .add("first_name", first_name)
                .add("last_name", last_name)
                .add("password", password)
                .build();

        requstHelper.Register(form, getResources().getString(R.string.API_Register), RegisterActivity.this);
    }

    public static void resultRequst(String Json, Context context) {


        RegisterModel rm = JsonHelper.ConvertStringToRegisterModel(Json);

        if (ValidationHelper.validStatus(rm.status)) {
            MessageHelper.Snackbar(context, context.getResources().getString(R.string.registered_ok), "", R.color.green, R.drawable.messagestylesuccess);
            ((Activity) context).startActivity(new Intent(context, LoginActivity.class));
            ((Activity) context).finish();
        } else {
            MessageHelper.Snackbar(context, context.getResources().getString(R.string.error_host), "", R.color.red, R.drawable.messagestyle);
        }


    }


}
