package com.example.mizansen.Network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RequestBuilderClass {

    public static final String URL_ip = "https://ifconfig.me/";
    public static final String URL_mizan = "https://mizanscene.com/wp-json/";
    public static final String URL_arvand = "https://napi.arvancloud.com/vod/2.0/";


    public static OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

    private static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create();

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_mizan) //This is the only mandatory call on Builder object.
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build();

    public static Retrofit retrofit_ip = new Retrofit.Builder()
            .baseUrl(URL_ip)
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(okHttpClient)
            .build();

    public static Retrofit retrofit_arvand = new Retrofit.Builder()
            .baseUrl(URL_arvand)
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(okHttpClient)
            .build();

}
