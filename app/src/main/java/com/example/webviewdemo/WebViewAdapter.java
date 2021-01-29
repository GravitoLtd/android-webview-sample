package com.example.webviewdemo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class WebViewAdapter {
    Context mContext;
    WebViewCallback webViewCallback = null;
    /** Instantiate the interface and set the context */
    WebViewAdapter(Context c,WebViewCallback webViewCallback) {
        mContext = c;
        this.webViewCallback = webViewCallback;

    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();

    }

    @JavascriptInterface
    public void getValueFromWebView(String value) {
        //Toast.makeText(mContext,value, Toast.LENGTH_LONG).show();
        webViewCallback.updateTextBox(value);
        SharedPreferences preferences = mContext.getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("TOKEN",value);
        boolean b =  editor.commit();
    }

    @JavascriptInterface
    public String getValueFromStorage(){
        final SharedPreferences mSharedPreference=mContext.getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String value=(mSharedPreference.getString("TOKEN", ""));
        Log.d("SP",value);

        return value;
    }


    @JavascriptInterface
    public void onButtonClick(){

    }

}
