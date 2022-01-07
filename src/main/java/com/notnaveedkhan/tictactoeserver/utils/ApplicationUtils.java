package com.notnaveedkhan.tictactoeserver.utils;

public class ApplicationUtils {

    public static String getRandomStringOfLength(int length) {
        String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int)(string.length() * Math.random());
            stringBuilder.append(string.charAt(index));
        }
        return stringBuilder.toString();
    }

}
