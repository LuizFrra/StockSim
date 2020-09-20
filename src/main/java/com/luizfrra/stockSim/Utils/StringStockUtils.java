package com.luizfrra.stockSim.Utils;

import java.util.regex.Pattern;

public class StringStockUtils {

    public static boolean isNullOrEmptyOrOnlyWhiteSpace(String str) {
        return (str == null || str.isBlank());
    }

    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        return pattern.matcher(email).find();
    }
}
