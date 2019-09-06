package com.example.mizansen.Helper;


import com.example.mizansen.Network.ModelNetwork.ValidationModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class JsonHelper {


    Gson gson = new Gson();

    public ValidationModel ConvertStringToValidationModel(String jsonString) {


        Type listType = new TypeToken<ValidationModel>() {
        }.getType();
        ValidationModel validationModel = gson.fromJson(jsonString.toString(), listType);
        return validationModel;
    }


}
