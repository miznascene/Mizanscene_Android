package com.example.mizansen;


import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class NetWork {

    OkHttpClient client = new OkHttpClient();
    String TAG = "TAG_NetWork";
    String allurl = "https://napi.arvancloud.com/vod/2.0/";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public String GETDATA(String URL) {
        Response response = null;
        try {
            Request request = new Request.Builder()
                    .url(allurl + URL)
                    .header("Authorization", "Apikey 8f91b792-6aa7-46c9-86db-dbbc25d2c4b8")
                    .build();

            response = client.newCall(request).execute();
            return response.body().string();

        } catch (IOException e) {
            Log.i("TAG_", "" + e.toString());
            client.connectionPool().evictAll();
            return "null";
        } finally {
            if (null != response) {
                response.close();
            }
        }
    }


    public String GetData(String URL, String headerName, String headerValue, String QueryParameterName, String QueryParameterValue, String QueryParameterName1, String QueryParameterValue1) {
        Response response = null;
        try {
            HttpUrl url = HttpUrl.parse(URL).newBuilder()
                    .addQueryParameter(QueryParameterName, QueryParameterValue)
                    .addQueryParameter(QueryParameterName1, QueryParameterValue1)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .header(headerName, headerValue)
                    .build();

            response = client.newCall(request).execute();
            return response.body().string();

        } catch (IOException e) {
            Log.i("TAG_", "" + e.toString());
            client.connectionPool().evictAll();
            return "null";
        } finally {
            if (null != response) {
                response.close();
            }
        }
    }

    public String PostData(String URL, RequestBody formBody, String headerName, String headerValue, String QueryParameterName, String QueryParameterValue, String QueryParameterName1, String QueryParameterValue1) {
        Response response = null;
        try {

            HttpUrl url = HttpUrl.parse(URL).newBuilder()
                    .addQueryParameter(QueryParameterName, QueryParameterValue)
                    .addQueryParameter(QueryParameterName1, QueryParameterValue1)
                    .build();


            Request request = new Request.Builder()
                    .url(url)
                    .header(headerName, headerValue)
                    .post(formBody)
                    .build();

            response = client.newCall(request).execute();
            return response.body().string();

        } catch (IOException e) {
            Log.i(TAG, "error : " + e.toString());
            client.connectionPool().evictAll();
            return "null";
        } finally {
            if (null != response) {
                response.close();
            }
        }
    }



    public String PutData(String URL, RequestBody formBody, String headerName, String headerValue, String QueryParameterName, String QueryParameterValue) {
        Response response = null;
        try {

            HttpUrl url = HttpUrl.parse(URL).newBuilder()
                    .addQueryParameter(QueryParameterName, QueryParameterValue)
                    .build();


            Request request = new Request.Builder()
                    .url(url)
                    .header(headerName, headerValue)
                    .put(formBody)
                    .build();

            response = client.newCall(request).execute();
            return response.body().string();

        } catch (IOException e) {
            Log.i(TAG, "error : " + e.toString());
            client.connectionPool().evictAll();
            return "null";
        } finally {
            if (null != response) {
                response.close();
            }
        }
    }

}

