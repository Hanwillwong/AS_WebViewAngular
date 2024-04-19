package com.hanwill.webviewangular;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private WebView _webViewAngular;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _webViewAngular = (WebView) findViewById(R.id.webViewAngular);

        WebViewClient webViewClient = new WebViewClient();
        _webViewAngular.setWebViewClient(webViewClient);

        WebChromeClient webChromeClient = new WebChromeClient();
        _webViewAngular.setWebChromeClient(webChromeClient);

        WebSettings webSettings = _webViewAngular.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        WebAppInterface webAppInterface = new WebAppInterface(this, MainActivity.this);
        _webViewAngular.addJavascriptInterface(webAppInterface,"Android");

        String url = "https://stmikpontianak.net/011100862/angular011100862";
        _webViewAngular.loadUrl(url);
    }
}