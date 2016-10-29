package com.hackathon.util;

import java.util.Random;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class UtilApps {


    public static String trim(String text) {
        return ValidateUtil.isEmpty(text) ? null : text.trim();
    }


    public static String generatePassword() {
        String password = "";
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int p = 97 + r.nextInt(122 - 97);
            password += r.nextInt(100) + "" + ((char) p);
        }
        return password;
    }

    public static String generateToken() {
        String token = "";
        Random r = new Random();
        token = Math.abs(r.nextLong()) + "";
        return token;
    }


    public static int random(int min, int max) {
        return Math.abs(new Random().nextInt()) % (max - min + 1) + min;
    }

    public static String formatSentence(String sentence) {
        if (ValidateUtil.isEmpty(sentence)) {
            return "";
        }
        sentence.replaceAll("\\s+", "");
        sentence = sentence.trim();
        sentence = sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
        if (sentence.charAt(sentence.length() - 1) != '.') {
            sentence += '.';
        }
        return sentence;
    }


}
