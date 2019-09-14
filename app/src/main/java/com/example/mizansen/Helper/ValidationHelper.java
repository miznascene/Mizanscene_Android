package com.example.mizansen.Helper;

import java.util.regex.Pattern;

public class ValidationHelper {

    private static final String Number = "0123456789";
    private static final ValidationHelper ourInstance = new ValidationHelper();

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

}
