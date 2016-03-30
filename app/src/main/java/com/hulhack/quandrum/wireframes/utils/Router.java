package com.hulhack.quandrum.wireframes.utils;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hulhack.quandrum.wireframes.activities.NavActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by KAI on 26-Mar-16.
 */
public class Router extends Application {
    private static Router mInstance;
    public static final String URL = "https://77ec4210.ngrok.io/";
    public static String token;

    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized Router getInstance() {
        return mInstance;
    }

    String responseString = "null";
    public String doPost(final Map<String, String> params, String url) {
        responseString = "null";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL+url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                        Log.e("Success", responseString);
                        responseString = "SUCCESS";

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error == null || error.networkResponse == null)
                            responseString = "ERROR:No internet connection";
                        else
                            responseString = "ERROR:" + new String(error.networkResponse.data);
                        Log.e("Error",responseString);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        Log.e("Returned",responseString);
        return responseString;
    }
}
