package com.example.mizansen.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chaos.view.PinView;
import com.example.mizansen.Helper.JsonHelper;
import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Helper.LocaleHelper;
import com.example.mizansen.Helper.MessageHelper;
import com.example.mizansen.Helper.RequestHelper;
import com.example.mizansen.Helper.ValidationHelper;
import com.example.mizansen.Network.ModelNetwork.ResetCodeModel;
import com.example.mizansen.Network.ModelNetwork.ValidationModel;
import com.example.mizansen.Network.ModelNetwork.ValidtionCodeModel;
import com.example.mizansen.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.FormBody;


public class VerifiyCodeActivity extends Activity {

    static String TypePage, Json;
    static Button submit;
    TextView textMail;
    static TextView ResetCode, textTimeDown;
    static int time;
    static Timer myTimer;
    LanguageHelper languageHelper = new LanguageHelper();
    static PinView pincode;
    RequestHelper requstHelper = new RequestHelper();
    static String TAG = "TAG_VerifiyCodeActivity", PinCode = "";
    ValidationModel validationModel = new ValidationModel();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "fa"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifiycode);

        init();



        pincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 4) {
                    submit.setVisibility(View.VISIBLE);
                    PinCode = s.toString();

                } else {
                    PinCode = "";
                    submit.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        textMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VerifiyCodeActivity.this, SendEmailActivity.class);
                i.putExtra("typePage", TypePage);
                startActivity(i);
                finish();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (ValidationHelper.validInternetConnection(VerifiyCodeActivity.this)){
                        sendValidationCode(PinCode);
                    }else{
                        MessageHelper.Snackbar(VerifiyCodeActivity.this, getResources().getString(R.string.ErrorValidInternet), "",R.color.red,R.drawable.messagestyle);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    MessageHelper.Snackbar(VerifiyCodeActivity.this, getResources().getString(R.string.ErrorValidInternet), "",R.color.red,R.drawable.messagestyle);
                } catch (IOException e) {
                    e.printStackTrace();
                    MessageHelper.Snackbar(VerifiyCodeActivity.this, getResources().getString(R.string.ErrorValidInternet), "",R.color.red,R.drawable.messagestyle);
                }

            }
        });


        ResetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (ValidationHelper.validInternetConnection(VerifiyCodeActivity.this)){
                        ResetCode.setVisibility(View.GONE);
                        ResetCodeCode();
                    }else{
                        MessageHelper.Snackbar(VerifiyCodeActivity.this, getResources().getString(R.string.ErrorValidInternet), "",R.color.red,R.drawable.messagestyle);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    MessageHelper.Snackbar(VerifiyCodeActivity.this, getResources().getString(R.string.ErrorValidInternet), "",R.color.red,R.drawable.messagestyle);
                } catch (IOException e) {
                    e.printStackTrace();
                    MessageHelper.Snackbar(VerifiyCodeActivity.this, getResources().getString(R.string.ErrorValidInternet), "",R.color.red,R.drawable.messagestyle);
                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(VerifiyCodeActivity.this, LoginActivity.class));
        finish();
    }

    void init() {
        Bundle bundle = getIntent().getExtras();
        TypePage = bundle.getString("typePage");
        Json = bundle.getString("json");

        validationModel = JsonHelper.ConvertStringToValidationModel(Json);

        pincode = findViewById(R.id.verifiycode_pincode);
        textTimeDown = findViewById(R.id.verifiycode_timedowntext);
        textMail = findViewById(R.id.verifiycode_mailtext);
        submit = findViewById(R.id.verifiycode_submit);
        ResetCode = findViewById(R.id.verifiycode_resetcode);

        textMail.setText(validationModel.data.email);

        languageHelper.GetLanguage(VerifiyCodeActivity.this);

        timeDown(VerifiyCodeActivity.this);

    }

    static void goToNextStep(Context context) {
        Intent i;

        if (TypePage.equals("reset_password"))
            i = new Intent(context, ResetPasswordActivity.class);
        else
            i = new Intent(context, RegisterActivity.class);

        i.putExtra("json", Json);
        context.startActivity(i);
        ((Activity) context).finish();
    }

    static void timeDown(final Context context) {
        time = 60;
        textTimeDown.setVisibility(View.VISIBLE);

        myTimer = new Timer();

        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time -= 1;
                        if (time <= 0) {
                            ResetCode.setVisibility(View.VISIBLE);
                            textTimeDown.setVisibility(View.GONE);
                            myTimer.cancel();
                        } else {
                            textTimeDown.setText(time + "s");
                        }


                    }
                });
            }

        }, 0, 1000);
    }

    void sendValidationCode(String PinCod) {

        try {
            submit.setVisibility(View.GONE);
            FormBody form = new FormBody.Builder()
                    .add("user_id", String.valueOf(validationModel.data.user_id))
                    .add("verification_code", PinCod)
                    .add("action", TypePage)
                    .build();

            requstHelper.verify_code(form, getResources().getString(R.string.API_VerifiCode), VerifiyCodeActivity.this);

        } catch (Exception e) {
            Log.i(TAG, "ResualtValidationCode Error" + e.toString());
        }

    }

    public static void ResualtValidationCode(String json, Context context) {

        ValidtionCodeModel validtionCodeModel = JsonHelper.ConvertStringToValidationCodeModel(json);
        submit.setVisibility(View.VISIBLE);
        try {
            if (ValidationHelper.validStatus(validtionCodeModel.status)) {
                //Success
//                if (validtionCodeModel.data.verification)
                goToNextStep(context);
            } else {
                // Error
                Log.i(TAG, context.getResources().getString(R.string.worng_code));
                MessageHelper.Snackbar(context, context.getResources().getString(R.string.worng_code), "",R.color.red,R.drawable.messagestyle);
                pincode.setText("");

            }
        } catch (Exception e) {
            Log.i(TAG, "ResualtValidationCode Error" + e.toString());
        }


    }

    void ResetCodeCode() {
        FormBody form = new FormBody.Builder()
                .add("user_id", String.valueOf(validationModel.data.user_id))
                .build();
        requstHelper.ResetCode(form, getResources().getString(R.string.API_ReSendCode), VerifiyCodeActivity.this);
    }

    public static void ResualtResetCode(String json, Context context) {

        ResetCodeModel resetCodeModel = JsonHelper.ConvertStringToResetCodeModel(json);

        if (ValidationHelper.validStatus(resetCodeModel.status)) {
            Log.i(TAG, context.getResources().getString(R.string.resetpass_ok));
            MessageHelper.Snackbar(context, context.getResources().getString(R.string.resetpass_ok), "",R.color.green,R.drawable.messagestylesuccess);
            timeDown(context);

        } else {
            Log.i(TAG, context.getResources().getString(R.string.resetpass_worng));
            MessageHelper.Snackbar(context, context.getResources().getString(R.string.resetpass_worng), "",R.color.red,R.drawable.messagestyle);
            ResetCode.setVisibility(View.VISIBLE);

        }

    }

}
