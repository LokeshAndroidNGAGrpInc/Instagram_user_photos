package com.dev.pyar;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by user2 on 8/1/2017.
 */

public class AuthWebViewClient extends WebViewClient{

    private String request_token;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith(StringConstants.mRedirectUrl)) {
            System.out.println(url);
            Log.d("AuthWebViewClient*****", "redirect url "+url);
            String parts[] = url.split("=");
            request_token = parts[1];  //This is your request token.
            Log.d("AuthWebViewClient*****", " request token "+request_token);
            return true;
        }
        return false;
    }
}
