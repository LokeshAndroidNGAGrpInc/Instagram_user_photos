package com.dev.pyar;

import android.os.Bundle;

/**
 * Created by user2 on 8/1/2017.
 */

public class Singleton {
    public static String reuestToken;
    public static String accessToken;

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        Singleton.accessToken = accessToken;
    }

    public static String getReuestToken() {
        return reuestToken;
    }

    public static void setReuestToken(String reuestToken) {
        Singleton.reuestToken = reuestToken;
    }

    public static Singleton newInstance() {
        Bundle args = new Bundle();
        Singleton singleton = new Singleton();
        return singleton;
    }

}
