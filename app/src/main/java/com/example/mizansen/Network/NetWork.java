package com.example.mizansen;


import android.util.Log;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NetWork {

    OkHttpClient client = new OkHttpClient();
    String allurl = "https://napi.arvancloud.com/vod/2.0/";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    public String GETDATA(String URL) {
        Response response = null;
        try {
            Request request = new Request.Builder()
                    .url(allurl + URL)
                    .header("Authorization","Apikey 8f91b792-6aa7-46c9-86db-dbbc25d2c4b8")
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



}
