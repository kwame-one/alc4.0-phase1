package com.kwame.phase1.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.net.http.SslError;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.kwame.phase1.activities.AboutActivity;
import com.kwame.phase1.interfaces.SSLListener;

public class WebClient extends WebViewClient {
    private SwipeRefreshLayout refreshLayout;
    private Context context;
    private SSLListener listener;

    public WebClient(Context context, SwipeRefreshLayout refreshLayout) {
        this.context = context;
        this.refreshLayout = refreshLayout;
        refreshLayout.setRefreshing(true);

    }

    public void setSSLListener(SSLListener listener){
        this.listener = listener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        refreshLayout.setRefreshing(false);

    }

    @Override
    public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("SSL Certificate error, Please try opening in browser");
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              listener.onError();

            }
        });

        builder.create().show();
    }
}
