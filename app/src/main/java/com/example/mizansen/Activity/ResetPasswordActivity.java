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
import com.example.mizansen.Network.ModelNetwork.ErrorModel;
import com.example.mizansen.Network.ModelNetwork.ValidationModel;
import com.example.mizansen.R;
import okhttp3.FormBody;




public class ResetPasswordActivity extends Activity {

    LanguageHelper languageHelper = new LanguageHelper();
    EditText password, passwordconfirm;
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
        setContentView(R.layout.activity_resetpassword);

        initView();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ValidationHelper.validPassword(password.getText().toString(), passwordconfirm.getText().toString())) {

                    if (ValidationHelper.validPasswordByLenght(password.getText().toString(), passwordconfirm.getText().toString())) {

                        FormBody form = new FormBody.Builder()
                                .add("user_pass", password.getText().toString())
                                .add("user_id", String.valueOf(validationModel.data.user_id))
                                .build();
                        requstHelper.ResetPassword(form, getResources().getString(R.string.API_ResetPassword), ResetPasswordActivity.this);

                    } else {
                        MessageHelper.Snackbar(ResetPasswordActivity.this, getResources().getString(R.string.password_count), "");
                    }

                } else {
                    MessageHelper.Snackbar(ResetPasswordActivity.this, getResources().getString(R.string.Same_password), "");
                }
            }
        });


    }

    void initView() {

        Bundle bundle = getIntent().getExtras();
        String Json = bundle.getString("json");
        validationModel = JsonHelper.ConvertStringToValidationModel(Json);

        submit = findViewById(R.id.recoverypassword_submit);
        password = findViewById(R.id.recoverypassword_password);
        passwordconfirm = findViewById(R.id.recoverypassword_confirmpassword);

        languageHelper.GetLanguage(ResetPasswordActivity.this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
        finish();
    }

    public static void resultRequst(String json, Context context) {

        ErrorModel errorModel = JsonHelper.ConvertStringToErrorModel(json);

        if (ValidationHelper.validStatus(errorModel.status)) {

            MessageHelper.Snackbar(context, context.getResources().getString(R.string.sucsses_resetpass), "");
            ((Activity) context).startActivity(new Intent(context, LoginActivity.class));
            ((Activity) context).finish();

        } else {
            MessageHelper.Snackbar(context, context.getResources().getString(R.string.error_host), "");
        }

    }

}
