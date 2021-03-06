package com.mncomunity1.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.mncomunity1.R;
import com.mncomunity1.event.ActivityResultBus;
import com.mncomunity1.event.ApiBus;

public class AboutWebViewActivity extends AppCompatActivity {
    ProgressBar progressBar5;
    WebView webview;
    String link;
    String cats;
    Toolbar toolbar;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        return true;
    }
    String cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        webview = (WebView) findViewById(R.id.webview);
        progressBar5 = (ProgressBar) findViewById(R.id.progressBar5);
        progressBar5.setVisibility(View.VISIBLE);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        link = getIntent().getStringExtra("link");
        Log.e("link",link);

        cats = getIntent().getStringExtra("key");
       // Log.e("ddddd",cats);

        if (cats.equals("About")) {
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                getSupportActionBar().setTitle("About");
                toolbar.setTitleTextColor(Color.BLACK);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);

                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

            }

        }

        webview.loadUrl(link);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                progressBar5.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityResultBus.getInstance().register(this);
        ApiBus.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityResultBus.getInstance().unregister(this);
        ApiBus.getInstance().unregister(this);
    }





    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
