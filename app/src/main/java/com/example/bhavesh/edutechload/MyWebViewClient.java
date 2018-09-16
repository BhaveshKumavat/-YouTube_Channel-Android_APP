package com.example.bhavesh.edutechload;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by bhavesh on 2/22/2018.
 */

public class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String request) {
        return false;
    }
}


