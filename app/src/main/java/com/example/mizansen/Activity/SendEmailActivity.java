package com.example.mizansen.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Helper.LocaleHelper;
import com.example.mizansen.Helper.MessageHelper;
import com.example.mizansen.Helper.RequestHelper;
import com.example.mizansen.Helper.ValidationHelper;
import com.example.mizansen.Network.ModelNetwork.ValidationModel;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;

import java.io.IOException;

import okhttp3.FormBody;


public class SendEmailActivity extends Activity {


    static String TypePage;
    static Button submit;
    static EditText mail;
    TextView title;
    LanguageHelper languageHelper = new LanguageHelper();
    static String TAG = "TAG_SendEmailActivity";
    static OtherMetod om = new OtherMetod();
    RequestHelper requstHelper = new RequestHelper();
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

                if (ValidationHelper.validEmail(mail.getText().toString())) {

                    try {
                        if (ValidationHelper.validInternetConnection(SendEmailActivity.this)){
                            sendCodeByTypePage();
                        }else{
                            MessageHelper.Snackbar(SendEmailActivity.this, getResources().getString(R.string.ErrorValidInternet), "",R.color.red,R.drawable.messagestyle);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        MessageHelper.Snackbar(SendEmailActivity.this, getResources().getString(R.string.ErrorValidInternet), "",R.color.red,R.drawable.messagestyle);
                    } catch (IOException e) {
                        e.printStackTrace();
                        MessageHelper.Snackbar(SendEmailActivity.this, getResources().getString(R.string.ErrorValidInternet), "",R.color.red,R.drawable.messagestyle);
                    }

                } else {
                    MessageHelper.Snackbar(SendEmailActivity.this, getResources().getString(R.string.ErrorValidEmail), "",R.color.red,R.drawable.messagestyle);
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
        startActivity(new Intent(SendEmailActivity.this, LoginActivity.class));
        finish();

    }

    void sendCodeByTypePage() {
        submit.setVisibility(View.GONE);
        if (TypePage.equals("reset_password")) {
            requstHelper.SendCodeResetpasswordByMail(getResources().getString(R.string.API_SendEmailToResetPass), "email", mail.getText().toString(), SendEmailActivity.this);
        } else {
            mail.setVisibility(View.INVISIBLE);
            requstHelper.SendCodeRegisterByMail(new FormBody.Builder().add("email", mail.getText().toString()).build(), getResources().getString(R.string.API_SendEmailToRegister), SendEmailActivity.this);

        }
    }

    void init() {
        Bundle bundle = getIntent().getExtras();
        TypePage = bundle.getString("typePage");

        title = findViewById(R.id.sendemail_title);
        backPage = findViewById(R.id.sendemail_backpage);
        submit = findViewById(R.id.sendemail_submit);
        mail = findViewById(R.id.sendemail_email);

        languageHelper.GetLanguage(SendEmailActivity.this);

        if (TypePage.equals("reset_password")) {
            title.setText(getResources().getString(R.string.title_sendmail_reset_password));
        } else {
            title.setText(getResources().getString(R.string.title_sendmail_register));
        }

    }

    public static void ResualtReqeustRegisterValidationEmail(ValidationModel validationModel, String Json, Context context) {

        if (ValidationHelper.validStatus(validationModel.status)) {
            goToPageVerifiyCodeActivity(Json, context);
        } else {
            MessageHelper.Snackbar(context, context.getResources().getString(R.string.worng_email_register), "",R.color.red,R.drawable.messagestyle);
            submit.setVisibility(View.VISIBLE);
            mail.setVisibility(View.VISIBLE);
        }
    }

    static void goToPageVerifiyCodeActivity(String Json, Context context) {

        Intent intent = new Intent(context, VerifiyCodeActivity.class);
        intent.putExtra("typePage", TypePage)
                .putExtra("json", Json);

        context.startActivity(intent);
        ((Activity) context).finish();


    }

    public static void ResualtReqeustResetPasswordValidationEmail(ValidationModel validationModel, String Json, Context context) {

        if (ValidationHelper.validStatus(validationModel.status)) {
            goToPageVerifiyCodeActivity(Json, context);
        } else {
            MessageHelper.Snackbar(context, context.getResources().getString(R.string.worng_email_reset), "",R.color.red,R.drawable.messagestyle);
            submit.setVisibility(View.VISIBLE);
            mail.setVisibility(View.VISIBLE);
        }
    }
}
