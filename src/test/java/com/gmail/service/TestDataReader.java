package com.gmail.service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("qa");

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}
