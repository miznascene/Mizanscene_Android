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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.chaos.view.PinView;
import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Helper.LocaleHelper;
import com.example.mizansen.Network.ModelNetwork.AccountModel;
import com.example.mizansen.Network.ModelNetwork.ValidationModel;
import com.example.mizansen.Network.ModelNetwork.ValidationNetModel;
import com.example.mizansen.Network.RequestBuilder;
import com.example.mizansen.Network.RequestBuilderClass;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VerifiyCodeActivity extends Activity {

    String TypePage;
    Button submit;
    LinearLayout layoutPincode;
    TextView textMail, textTimeDown, ReSend;
    EditText mail;
    int time;
    Timer myTimer;
    LanguageHelper languageHelper = new LanguageHelper();
    PinView pincode;
    String TAG = "TAG_VerifiyCodeActivity";
    OtherMetod om = new OtherMetod();
    String CodeInput;

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
                    CodeInput = s.toString();
                    submit.setVisibility(View.VISIBLE);
                    ReSend.setVisibility(View.GONE);
                } else {
                    submit.setVisibility(View.GONE);
                    CodeInput = "";
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        textMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutPincode.setVisibility(View.GONE);
                mail.setVisibility(View.VISIBLE);
                submit.setText(getResources().getString(R.string.Get_code));
                submit.setVisibility(View.VISIBLE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (submit.getText().toString().equals(getResources().getString(R.string.Get_code))) {


                    if (om.validEmail(mail.getText().toString())) {

                        textMail.setText(mail.getText().toString() + " " + getResources().getString(R.string.Edit) + "?");
                        layoutPincode.setVisibility(View.VISIBLE);
                        mail.setVisibility(View.INVISIBLE);
                        timeDown();

                        if (TypePage.equals("forgotpass")) {
                            submit.setText(getResources().getString(R.string.change_pass));
                            sendCodeForEmail(mail.getText().toString());
                        } else {
                            submit.setText(getResources().getString(R.string.Register));
                        }

                        submit.setVisibility(View.GONE);
                    } else {
                        mail.setError(getResources().getString(R.string.ErrorValidEmail));
                    }

                } else {
                    if (TypePage.equals("forgotpass")) {
                        // resetpass verify code
                    } else {
                        // register verify code
                    }
                }


            }
        });

        ReSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReSend.setVisibility(View.GONE);
                timeDown();
            }
        });

    }

    void init() {
        Bundle bundle = getIntent().getExtras();
        TypePage = bundle.getString("typePage");

        pincode = findViewById(R.id.verifiycode_pincode);
        layoutPincode = findViewById(R.id.verifiycode_layoutpincode);
        textTimeDown = findViewById(R.id.verifiycode_timedowntext);
        textMail = findViewById(R.id.verifiycode_mailtext);
        submit = findViewById(R.id.verifiycode_submit);
        ReSend = findViewById(R.id.verifiycode_resend);
        mail = findViewById(R.id.verifiycode_email);

        languageHelper.GetLanguage(VerifiyCodeActivity.this);


    }

    void goToNextStep() {
        if (TypePage.equals("forgotpass")) {
            startActivity(new Intent(VerifiyCodeActivity.this, RecoveryPasswordActivity.class));
        } else {
            startActivity(new Intent(VerifiyCodeActivity.this, RegisterActivity.class));
        }
    }

    void timeDown() {
        time = 60;

        myTimer = new Timer();

        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time -= 1;
                        if (time <= 0) {
                            ReSend.setVisibility(View.VISIBLE);
                            myTimer.cancel();
                        }
                        textTimeDown.setText(time + "s");

                    }
                });
            }

        }, 0, 1000);
    }

    void sendCodeForEmail(String Email) {

        ValidationNetModel vnm = new ValidationNetModel();
        vnm.email = Email;


        Call<ValidationModel> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).
                SendCodeToEmail("SALAM_MZ_SERVER", vnm);
        client.enqueue(new Callback<ValidationModel>() {
            @Override
            public void onResponse(Call<ValidationModel> call, Response<ValidationModel> response) {

                ValidationModel validationModel = response.body();

                try {
                    if (!validationModel.user_id.isEmpty())
                        Log.i(TAG, "user_id = " + validationModel.user_id);

                } catch (Exception e) {
                    Log.i(TAG, "Error :" + e.toString());
                }

            }

            @Override
            public void onFailure(Call<ValidationModel> call, Throwable t) {
                Log.i(TAG, "onFailure :" + t.toString());
            }
        });


    }


}
