package com.example.mizansen.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chaos.view.PinView;
import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Helper.LocaleHelper;
import com.example.mizansen.Helper.RequstHelper;
import com.example.mizansen.R;

import java.util.Timer;
import java.util.TimerTask;


public class VerifiyCodeActivity extends Activity {

    String TypePage, Json;
    Button submit;
    TextView textMail, textTimeDown, ReSend;
    int time;
    Timer myTimer;
    LanguageHelper languageHelper = new LanguageHelper();
    PinView pincode;
    static String TAG = "TAG_VerifiyCodeActivity";
    String CodeInput;
    RequstHelper requstHelper = new RequstHelper();

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

                    goToNextStep();

                } else {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        textMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        Json = bundle.getString("json");

        pincode = findViewById(R.id.verifiycode_pincode);
        textTimeDown = findViewById(R.id.verifiycode_timedowntext);
        textMail = findViewById(R.id.verifiycode_mailtext);
        submit = findViewById(R.id.verifiycode_submit);
        ReSend = findViewById(R.id.verifiycode_resend);

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
                        } else {
                            textTimeDown.setText(time + "s");
                        }


                    }
                });
            }

        }, 0, 1000);
    }

    void sendValidationCode(String PinCod) {
    }


}
