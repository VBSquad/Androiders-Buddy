package com.iitms.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.webview);
        /*Generally, the web page which we are loading in
        WebView might use JavaScript. In case if we won’t
        enable the JavaScript, the functionality which related
        to JavaScript in web page won’t work that’s the reason we
        are enabling the JavaScript using setJavaScriptEnabled() */
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://github.com/VBSquad/Androiders-Buddy");
    }
}