package com.example.webviewdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebMessage;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    private EditText mEditText;
    private TextView mTextView;
    private Button mButton;
    private String[] users = { "Suresh Dasari", "Rohini Alavala", "Trishika Dasari", "Praveen Alavala", "Madav Sai", "Hamsika Yemineni"};
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = findViewById(R.id.mWebView);
        mEditText = findViewById(R.id.mEditText);
        mTextView = findViewById(R.id.mTextView);
        mButton = findViewById(R.id.mButton);
        mWebView.setWebContentsDebuggingEnabled(true);


        //Pass webviewadapter object to webview
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(false);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.loadUrl("https://cdn.gravito.net/webview/index.html?platform=android");


        mWebView.addJavascriptInterface(new WebViewAdapter(getApplicationContext(), new WebViewCallback() {
            @Override
            public void updateTextBox(final String text) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(text);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Log.d("postwebmessage","POST WEB MESSAGE");
                            mWebView.postWebMessage(new WebMessage("post web message"), Uri.EMPTY);
                        }
                    }
                });


            }
        }), "Android");

       // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, users);
        //mListView.setAdapter(arrayAdapter);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, ""+mEditText.getText().toString(), Toast.LENGTH_SHORT).show();
                mWebView.loadUrl(mEditText.getText().toString());
            }
        });


    }
}
