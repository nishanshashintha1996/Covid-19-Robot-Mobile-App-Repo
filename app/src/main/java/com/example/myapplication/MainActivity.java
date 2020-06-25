package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    GifImageView gifImageView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, NotificationService.class));
        button = findViewById(R.id.reload);
        gifImageView = findViewById(R.id.loadingGif);
        progressBar = findViewById(R.id.progress);
        progressBar.setMax(100);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("https://robot.findx.lk/");
            }
        });
        webView.loadUrl("https://robot.findx.lk/");
        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.GONE);
                gifImageView.setVisibility(View.VISIBLE);
                if(newProgress==100){
                    progressBar.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                    gifImageView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Toast.makeText(MainActivity.this,title,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }
        });

        
    }
}
