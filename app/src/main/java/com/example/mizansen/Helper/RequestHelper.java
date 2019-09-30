package com.example.mizansen.Helper;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.mizansen.Activity.LoginActivity;
import com.example.mizansen.Activity.ResetPasswordActivity;
import com.example.mizansen.Activity.RegisterActivity;
import com.example.mizansen.Activity.SendEmailActivity;
import com.example.mizansen.Activity.VerifiyCodeActivity;
import com.example.mizansen.Fragment.BottomBar.CategoryFragment;
import com.example.mizansen.Fragment.BottomBar.HomeFragment;
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
    IndicatorHelper loadingHelper = new IndicatorHelper();


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

                SplashScreenActivity.ResultRequst(result.replace("?>",""), context);
            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public void Login(String email, String password, final String url, final Context context) {

        loadingHelper.CreateIndicator(context);

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.PostData(url, new FormBody.Builder().build(), "Authorization", "", "email", email, "password", password);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                loadingHelper.DismissIndicator();
                Log.i(TAG, "Login result: " + result);

                LoginActivity.ResultRequest(result.replace("?>",""), context);
            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public void SendCodeRegisterByMail(final RequestBody formBody, final String url, final Context context) {

        loadingHelper.CreateIndicator(context);

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.PostData(url, formBody, "Authorization", "", "SECRET_MZ_KEY", "SALAM_MZ_SERVER", "", "");
            }


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Log.i(TAG, "SendCodeRegisterByMail result: " + result);

                loadingHelper.DismissIndicator();
                ValidationModel validationModel = jsonHelper.ConvertStringToValidationModel(result.replace("?>",""));

                if (ValidationHelper.validStatus(validationModel.status)) {
                    SendEmailActivity.ResualtReqeustRegisterValidationEmail(validationModel, result.replace("?>",""), context);
                } else {
                    SendEmailActivity.ResualtReqeustRegisterValidationEmail(validationModel, result.replace("?>",""), context);
                }


            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public void SendCodeResetpasswordByMail(final String url, final String QueryParameterName, final String QueryParameterValue, final Context context) {

        loadingHelper.CreateIndicator(context);

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.GetData(url, "Authorization", "", "SECRET_MZ_KEY", "SALAM_MZ_SERVER", QueryParameterName, QueryParameterValue);
            }


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Log.i(TAG, "SendCodeResetpasswordByMail result: " + result);
                loadingHelper.DismissIndicator();

                ValidationModel validationModel = jsonHelper.ConvertStringToValidationModel(result.replace("?>",""));

                if (ValidationHelper.validStatus(validationModel.status)) {
                    SendEmailActivity.ResualtReqeustResetPasswordValidationEmail(validationModel, result.replace("?>",""), context);
                } else {
                    Log.i(TAG, "message: " + validationModel.message);
                    SendEmailActivity.ResualtReqeustResetPasswordValidationEmail(validationModel, result.replace("?>",""), context);
                }

            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public void verify_code(final RequestBody formBody, final String url, final Context context) {

        loadingHelper.CreateIndicator(context);

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.PostData(url, formBody, "Authorization", "", "SECRET_MZ_KEY", "SALAM_MZ_SERVER", "", "");
            }


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                loadingHelper.DismissIndicator();
                Log.i(TAG, "verify_code result: " + result);

                VerifiyCodeActivity.ResualtValidationCode(result.replace("?>",""), context);

            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public void ResetCode(final RequestBody formBody, final String url, final Context context) {

        loadingHelper.CreateIndicator(context);

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.PostData(url, formBody, "Authorization", "", "SECRET_MZ_KEY", "SALAM_MZ_SERVER", "", "");
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                loadingHelper.DismissIndicator();
                Log.i(TAG, "Register result: " + result);
                VerifiyCodeActivity.ResualtResetCode(result.replace("?>",""), context);


            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public void Register(final RequestBody formBody, final String url, final Context context) {
        loadingHelper.CreateIndicator(context);

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.PutData(url, formBody, "Authorization", "", "SECRET_MZ_KEY", "SALAM_MZ_SERVER");
            }


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                loadingHelper.DismissIndicator();
                Log.i(TAG, "Register result: " + result);
                RegisterActivity.resultRequst(result.replace("?>",""), context);


            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public void ResetPassword(final RequestBody formBody, final String url, final Context context) {

        loadingHelper.CreateIndicator(context);

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.PostData(url, formBody, "Authorization", "", "SECRET_MZ_KEY", "SALAM_MZ_SERVER", "", "");
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                loadingHelper.DismissIndicator();
                Log.i(TAG, "Register result: " + result);
                ResetPasswordActivity.resultRequst(result.replace("?>",""), context);


            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    //Request is MainActivity => HomePage | Category |

    public void HomePage(final String token, final String url, final Context context,String pagenumber) {


        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.GetData(url, "Authorization", token, "pagenumber", pagenumber, "", "");
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Log.i(TAG, "HomePage result: " + result);
                HomeFragment.resultRequst(result.replace("?>",""), context);


            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public void CategoryByPagenaumbr(final String token, final String url, final Context context,String pagenumber) {


        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.GetData(url, "Authorization", token, "pagenumber", pagenumber, "", "");
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Log.i(TAG, "CategoryByPagenaumbr result: " + result);
                CategoryFragment.resultRequst(result.replace("?>",""), context);


            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }



    //Request is ... =>

    //Request is ... =>

    //Request is ... =>


}
