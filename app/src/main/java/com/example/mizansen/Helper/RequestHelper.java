package com.example.mizansen.Helper;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.mizansen.Activity.ResetPasswordActivity;
import com.example.mizansen.Activity.RegisterActivity;
import com.example.mizansen.Activity.SendEmailActivity;
import com.example.mizansen.Activity.VerifiyCodeActivity;
import com.example.mizansen.NetWork;
import com.example.mizansen.Network.ModelNetwork.AccountModel;
import com.example.mizansen.Network.ModelNetwork.ValidationModel;
import com.example.mizansen.SplashScreenActivity;

import okhttp3.FormBody;
import okhttp3.RequestBody;


public class RequestHelper {

    NetWork netWork = new NetWork();
    String TAG = "TAG_RequstHelper";
    JsonHelper jsonHelper = new JsonHelper();
    LoadingHelper loadingHelper = new LoadingHelper();


    // Request is UserActivity => Validation | Login | SendMail | VerifyCode | ResendCode(ResetCode) | Register | ResetPassword
    public void Validation(final String Authorization, final String url, final Context context) {


        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.PostData(url, new FormBody.Builder().build(), "Authorization", Authorization, "", "", "", "");
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Log.i(TAG, "Validation result: " + result);
                SplashScreenActivity.ResultRequst(result,context);
            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public void Login(String email, String password, final String url, final Context context) {

        loadingHelper.CreateLoading(context);

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.PostData(url, new FormBody.Builder().build(), "Authorization", "", "email", email, "password", password);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                loadingHelper.DismissDialog();
                Log.i(TAG, "Login result: " + result);
                try{
                    AccountModel accountmodel = JsonHelper.ConvertStringToAccountModel(result);
                    SharedPreferencesHelper.SetSharedPreferences("Token",accountmodel.token,context);
                }catch (Exception e){

                }


            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public void SendCodeRegisterByMail(final RequestBody formBody, final String url, final Context context) {

        loadingHelper.CreateLoading(context);

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.PostData(url, formBody, "Authorization", "", "SECRET_MZ_KEY", "SALAM_MZ_SERVER", "", "");
            }


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Log.i(TAG, "SendCodeRegisterByMail result: " + result);

                loadingHelper.DismissDialog();
                ValidationModel validationModel = jsonHelper.ConvertStringToValidationModel(result);

                if (ValidationHelper.validStatus(validationModel.status)) {
                    SendEmailActivity.ResualtReqeustRegisterValidationEmail(validationModel, result, context);
                } else {
                    SendEmailActivity.ResualtReqeustRegisterValidationEmail(validationModel, result, context);
                }


            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public void SendCodeResetpasswordByMail(final String url, final String QueryParameterName, final String QueryParameterValue, final Context context) {

        loadingHelper.CreateLoading(context);

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.GetData(url, "Authorization", "", "SECRET_MZ_KEY", "SALAM_MZ_SERVER", QueryParameterName, QueryParameterValue);
            }


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Log.i(TAG, "SendCodeResetpasswordByMail result: " + result);
                loadingHelper.DismissDialog();

                ValidationModel validationModel = jsonHelper.ConvertStringToValidationModel(result);

                if (ValidationHelper.validStatus(validationModel.status)) {
                    SendEmailActivity.ResualtReqeustResetPasswordValidationEmail(validationModel, result, context);
                } else {
                    Log.i(TAG, "message: " + validationModel.message);
                    SendEmailActivity.ResualtReqeustResetPasswordValidationEmail(validationModel, result, context);
                }

            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public void verify_code(final RequestBody formBody, final String url, final Context context) {

        loadingHelper.CreateLoading(context);

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.PostData(url, formBody, "Authorization", "", "SECRET_MZ_KEY", "SALAM_MZ_SERVER", "", "");
            }


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                loadingHelper.DismissDialog();
                Log.i(TAG, "verify_code result: " + result);

                VerifiyCodeActivity.ResualtValidationCode(result, context);

            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public void ResetCode(final RequestBody formBody, final String url, final Context context) {

        loadingHelper.CreateLoading(context);

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.PostData(url, formBody, "Authorization", "", "SECRET_MZ_KEY", "SALAM_MZ_SERVER", "", "");
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                loadingHelper.DismissDialog();
                Log.i(TAG, "Register result: " + result);
                VerifiyCodeActivity.ResualtResetCode(result, context);


            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public void Register(final RequestBody formBody, final String url, final Context context) {
        loadingHelper.CreateLoading(context);

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.PutData(url, formBody, "Authorization", "", "SECRET_MZ_KEY", "SALAM_MZ_SERVER");
            }


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                loadingHelper.DismissDialog();
                Log.i(TAG, "Register result: " + result);
                RegisterActivity.resultRequst(result, context);


            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public void ResetPassword(final RequestBody formBody, final String url, final Context context) {

        loadingHelper.CreateLoading(context);

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.PostData(url, formBody, "Authorization", "", "SECRET_MZ_KEY", "SALAM_MZ_SERVER", "", "");
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                loadingHelper.DismissDialog();
                Log.i(TAG, "Register result: " + result);
                ResetPasswordActivity.resultRequst(result, context);


            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    //Request is MainActivity =>

    //Request is ... =>

    //Request is ... =>

    //Request is ... =>






}
