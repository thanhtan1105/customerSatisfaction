package com.hackathon.util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class ValidateUtil {

    public static Long parseNumber(String number) {
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static boolean isImageFile(InputStream data) {
        try {
            ImageIO.read(data).toString();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean isNotEmpty(Collection collection) {
        return collection != null && collection.size() > 0;
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() <= 0;
    }

    public static boolean isEmpty(String text) {
        return text == null || text.length() <= 0;
    }

    public static boolean isNotEmpty(String text) {
        return text != null && text.length() > 0;
    }

    public static boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    public static boolean isResultAutocomplete(String text) {
        String pattern = "^(.*)-(.*)$";
        return text.matches(pattern);
    }

}
