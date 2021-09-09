package com.cos.photogramstart.util;

public class Script {

    public static String back(String message) {
        String sb = "<scripts>" +
                "alert('" + message + "');" +
                "history.back();" +
                "</script>";
        return sb;
    }
}
