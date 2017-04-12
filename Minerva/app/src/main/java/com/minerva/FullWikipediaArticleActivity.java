package com.minerva;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class FullWikipediaArticleActivity extends AppCompatActivity {

    protected WebView wikipediaWebView;

    protected String wikipediaArticleWebPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_wikipedia_article);

        /*** Change color of the Action Bar ***/
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.rome)));

        // Getting string from previous interface, and build article URL on Wikipedia
        String articleName = getIntent().getStringExtra("PLACE_NAME");
        wikipediaArticleWebPage = "https://en.wikipedia.org/wiki/" + articleName;

        /*** Retrieve full web view of the Wikipedia article ***/
        wikipediaWebView = (WebView) findViewById(R.id.wikipediaWebView);
        wikipediaWebView.getSettings().setJavaScriptEnabled(true);
        wikipediaWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(FullWikipediaArticleActivity.this, description, Toast.LENGTH_SHORT).show();
            }
        });
        wikipediaWebView.loadUrl(wikipediaArticleWebPage);
    }


}
