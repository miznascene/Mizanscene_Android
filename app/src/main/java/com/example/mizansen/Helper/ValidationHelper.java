package com.example.mizansen.Helper;


import android.content.Context;
import android.net.ConnectivityManager;

import java.io.IOException;
import java.util.regex.Pattern;


public class ValidationHelper {

    private static final String Number = "0123456789";
    private static final ValidationHelper ourInstance = new ValidationHelper();
    static boolean isWifiEnable = false;
    static boolean isMobileNetworkAvailable = false;

    public static ValidationHelper getInstance() {
        return ourInstance;
    }

    public static boolean validEmail(String Email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (Email == null)
            return false;
        return pat.matcher(Email).matches();

    }

    public static boolean validStatus(int status) {

        if (status == 200) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean validPassword(String Password, String ConfirmPassword) {

        if (!Password.isEmpty() && !ConfirmPassword.isEmpty()) {

            if (Password.equals(ConfirmPassword)) {
                return true;

            } else {
                return false;
            }

        } else {
            return false;
        }


    }

    public static boolean validPasswordByLenght(String Password, String ConfirmPassword) {

        if (!Password.isEmpty() && !ConfirmPassword.isEmpty()) {

            if (Password.length() > 5) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }


    }

    public static boolean validFirstNameAndLastName(String Name) {

        if (!Name.isEmpty()) {

            if (Name.length() > 2) {

                for (int i = 0; i < Name.length(); i++) {
                    for (int j = 0; j < Number.length(); j++) {
                        if (Name.charAt(i) == Number.charAt(j)) return false;
                    }
                }

                return true;

            } else {
                return false;
            }


        } else {
            return false;
        }


    }

    public static boolean validInternetConnection(Context context) throws InterruptedException, IOException {
        if (validWifiAndDataMobile(context)) {
            final String command = "ping -c 1 google.com";
            return Runtime.getRuntime().exec(command).waitFor() == 0;
        } else {
            return false;
        }

    }

    static boolean validWifiAndDataMobile(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        isWifiEnable = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
        isMobileNetworkAvailable = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();

        if (isWifiEnable || isMobileNetworkAvailable ) {
        /*Sometime wifi is connected but service provider never connected to internet
        so cross check one more time*/
            return true;
        }

        return false;
    }


}
