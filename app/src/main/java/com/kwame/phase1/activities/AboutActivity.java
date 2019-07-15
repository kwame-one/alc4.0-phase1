package com.kwame.phase1.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.kwame.phase1.R;
import com.kwame.phase1.interfaces.SSLListener;
import com.kwame.phase1.utils.WebClient;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        final WebView webView = findViewById(R.id.web);
        SwipeRefreshLayout refreshLayout = findViewById(R.id.refresh);
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark));

        WebClient webClient = new WebClient(this, refreshLayout);
        webClient.setSSLListener(new SSLListener() {
            @Override
            public void onError() {
                finish();
            }
        });

        webView.setWebViewClient(webClient);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.loadUrl(getString(R.string.url));


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.loadUrl(getString(R.string.url));
            }
        });
    }
}
