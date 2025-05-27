package com.alten.shop.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        Pattern emailPattern = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    public static String generateReference(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";
        StringBuilder s = new StringBuilder(n);
        int y;
        for (y = 0; y < n; y++) {
            int index = (int) (AlphaNumericString.length()
                    * Math.random());
            s.append(AlphaNumericString
                    .charAt(index));
        }
        return s.toString();
    }
}
