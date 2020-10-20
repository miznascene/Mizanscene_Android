package com.example.mizansen.Helper;


import com.example.mizansen.Network.ModelNetwork.AccountModel;
import com.example.mizansen.Network.ModelNetwork.CategoryModel;
import com.example.mizansen.Network.ModelNetwork.ErrorModel;
import com.example.mizansen.Network.ModelNetwork.IpAddressModel;
import com.example.mizansen.Network.ModelNetwork.MainpageModel;
import com.example.mizansen.Network.ModelNetwork.RegisterDataModel;
import com.example.mizansen.Network.ModelNetwork.RegisterModel;
import com.example.mizansen.Network.ModelNetwork.ResetCodeModel;
import com.example.mizansen.Network.ModelNetwork.ValidationModel;
import com.example.mizansen.Network.ModelNetwork.ValidtionCodeModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class JsonHelper {


    static Gson gson = new Gson();

    public static ValidationModel ConvertStringToValidationModel(String jsonString) {


        Type listType = new TypeToken<ValidationModel>() {
        }.getType();
        ValidationModel Return = gson.fromJson(jsonString.toString(), listType);
        return Return;
    }

    public static ValidtionCodeModel ConvertStringToValidationCodeModel(String jsonString) {


        Type listType = new TypeToken<ValidtionCodeModel>() {
        }.getType();
        ValidtionCodeModel Return = gson.fromJson(jsonString.toString(), listType);
        return Return;
    }

    public static RegisterModel ConvertStringToRegisterModel(String jsonString) {

        Type listType = new TypeToken<RegisterModel>() {
        }.getType();
        RegisterModel Return = gson.fromJson(jsonString.toString(), listType);
        return Return;
    }

    public static ErrorModel ConvertStringToErrorModel(String jsonString){
        Type listType = new TypeToken<ErrorModel>() {
        }.getType();
        ErrorModel Return  = gson.fromJson(jsonString.toString(), listType);
        return Return;
    }

    public static ResetCodeModel ConvertStringToResetCodeModel(String jsonString){
        Type listType = new TypeToken<ResetCodeModel>() {
        }.getType();
        ResetCodeModel Return  = gson.fromJson(jsonString.toString(), listType);
        return Return;
    }

    public static AccountModel ConvertStringToAccountModel(String jsonString){
        Type listType = new TypeToken<AccountModel>() {
        }.getType();
        AccountModel Return  = gson.fromJson(jsonString.toString(), listType);
        return Return;
    }

    public static MainpageModel ConvertStringToMainpageModel(String jsonString){
        Type listType = new TypeToken<MainpageModel>() {
        }.getType();
        MainpageModel Return  = gson.fromJson(jsonString.toString(), listType);
        return Return;
    }

    public static CategoryModel ConvertStringToCategoryModel(String jsonString){
        Type listType = new TypeToken<CategoryModel>() {
        }.getType();
        CategoryModel Return  = gson.fromJson(jsonString.toString(), listType);
        return Return;
    }

    public static IpAddressModel ConvertStringToIpAddressModel(String jsonString){
        Type listType = new TypeToken<IpAddressModel>() {
        }.getType();
        IpAddressModel Return  = gson.fromJson(jsonString.toString(), listType);
        return Return;
    }

}
