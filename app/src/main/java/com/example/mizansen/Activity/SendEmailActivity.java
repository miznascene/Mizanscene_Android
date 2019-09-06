package com.example.mizansen.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Helper.LocaleHelper;
import com.example.mizansen.Helper.RequstHelper;
import com.example.mizansen.Network.ModelNetwork.ValidationModel;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;

import okhttp3.FormBody;


public class SendEmailActivity extends Activity {


    static String TypePage;
    static Button submit;
    static EditText mail;
    LanguageHelper languageHelper = new LanguageHelper();
    static String TAG = "TAG_SendEmailActivity";
    static OtherMetod om = new OtherMetod();
    RequstHelper requstHelper = new RequstHelper();
    ImageView backPage;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "fa"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmail);

        init();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (om.validEmail(mail.getText().toString())) {
                    sendCodeByTypePage();
                } else {
                    mail.setError(getResources().getString(R.string.ErrorValidEmail));
                }

            }
        });

        backPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    void sendCodeByTypePage() {
        if (TypePage.equals("forgotpass")) {
            submit.setVisibility(View.GONE);

        } else {
            submit.setVisibility(View.GONE);
            mail.setVisibility(View.INVISIBLE);
            requstHelper.SendCodeRegisterByMail(new FormBody.Builder().add("email", mail.getText().toString()).build(), "https://mizanscene.com/wp-json/mobile/v1/profile/register", SendEmailActivity.this);

        }
    }

    void init() {
        Bundle bundle = getIntent().getExtras();
        TypePage = bundle.getString("typePage");

        backPage = findViewById(R.id.sendemail_backpage);
        submit = findViewById(R.id.sendemail_submit);
        mail = findViewById(R.id.sendemail_email);

        languageHelper.GetLanguage(SendEmailActivity.this);

    }

    public static void ResualtReqeustValidationEmail(ValidationModel validationModel, String Json, Context context) {

        if (om.validStatus(validationModel.status)) {
            Log.i(TAG, "user_email : " + validationModel.data.user_email);
            goToPageVerifiyCodeActivity(Json, context);
        } else {
            Log.i(TAG, "message : " + validationModel.message);
            submit.setVisibility(View.VISIBLE);
            mail.setVisibility(View.VISIBLE);
        }


    }

    static void goToPageVerifiyCodeActivity(String Json, Context context) {

        Intent intent = new Intent(context, VerifiyCodeActivity.class);
        intent.putExtra("typePage", TypePage)
                .putExtra("json", Json);

        context.startActivity(intent);


    }
}
