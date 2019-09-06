package com.example.mizansen.Helper;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.mizansen.Activity.SendEmailActivity;
import com.example.mizansen.Activity.VerifiyCodeActivity;
import com.example.mizansen.NetWork;
import com.example.mizansen.Network.ModelNetwork.ValidationModel;
import com.example.mizansen.OtherClass.OtherMetod;

import okhttp3.RequestBody;

public class RequstHelper {

    NetWork netWork = new NetWork();
    String TAG = "TAG_RequstHelper";
    JsonHelper jsonHelper = new JsonHelper();

    public void SendCodeRegisterByMail(final RequestBody formBody, final String url, final Context context) {


        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... p) {
                return netWork.PostData(url, formBody, "", "SECRET_MZ_KEY", "SALAM_MZ_SERVER");
            }


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Log.i(TAG, "result: " + result);

                ValidationModel validationModel = jsonHelper.ConvertStringToValidationModel(result);

                if (OtherMetod.validStatus(validationModel.status)) {
                    SendEmailActivity.ResualtReqeustValidationEmail(validationModel, result, context);
                } else {
                    Log.i(TAG, "message: " + validationModel.message);
                    SendEmailActivity.ResualtReqeustValidationEmail(validationModel, result, context);
                }


            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }


}
