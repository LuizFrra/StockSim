package com.luizfrra.stockSim.Utils;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class StringStockUtils {
    public static boolean isNullOrEmptyOrWhiteSpace(String str) {
        return (str == null || StringUtils.isEmpty(str) || StringUtils.containsWhitespace(str));
    }

    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        return pattern.matcher(email).find();
    }
}
