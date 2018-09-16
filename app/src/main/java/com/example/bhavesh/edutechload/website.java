package com.example.bhavesh.edutechload;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class website extends AppCompatActivity {


    private WebView webView;
    public ProgressBar bar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);



        webView = (WebView) findViewById(R.id.webView1);

        bar = (ProgressBar) findViewById(R.id.progressBar1);
        webView.setWebViewClient(new myWebclient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://tecchtalks.wordpress.com/");



    }



    public class myWebclient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String urt) {
            super.onPageFinished(view, urt);
            bar.setVisibility(View.GONE);


        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            return super.shouldOverrideUrlLoading(view, url);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if((keyCode== KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return  true;
        };


        return super.onKeyDown(keyCode,event);

    }
}






