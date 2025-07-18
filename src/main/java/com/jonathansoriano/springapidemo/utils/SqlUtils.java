package com.jonathansoriano.springapidemo.utils;

public class SqlUtils {
    private SqlUtils() {
        //default private constructor
    }

    public static <T> String appendWhereIfNotNull (String query, T field)
    {
        if (field != null) {
            return String.format(" %s", query);
        }
        else
            return "";
    }

}
