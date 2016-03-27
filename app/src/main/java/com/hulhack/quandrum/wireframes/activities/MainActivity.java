package com.hulhack.quandrum.wireframes.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dd.CircularProgressButton;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.hulhack.quandrum.wireframes.R;
import com.hulhack.quandrum.wireframes.gcm.GcmActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "Login Credentials";
    public String TAG = "Returned";
    EditText mNumber;
    String PROJECT_NUMBER = "477020844036";
    CircularProgressButton circularProgressButton;
    GoogleCloudMessaging gcm;
    String regid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Hindustan Unilever");

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        if(prefs.contains("token")) {
            startActivity(new Intent(this, NavActivity.class));
            finish();
        }

        mNumber=(EditText)findViewById(R.id.editText);
        // Tag used to cancel the request
        circularProgressButton=(CircularProgressButton)findViewById(R.id.btnWithText);
        circularProgressButton.setIndeterminateProgressMode(true);
        Intent i = new Intent(this, GcmActivity.class);
        startActivity(i);
        ImageView myImageView= (ImageView)findViewById(R.id.imageView);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade);
        myImageView.startAnimation(myFadeInAnimation); //Set animation to your ImageView
        mNumber.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                circularProgressButton.setProgress(0);
            }
        });

    }

    public void buttonClick(View view){
        if(circularProgressButton.getProgress()==-1)
            return;
        circularProgressButton.setProgress(50);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://77ec4210.ngrok.io/req_otp" ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        circularProgressButton.setProgress(100);


                        final ProgressDialog pDialog = new ProgressDialog(MainActivity.this);
                        pDialog.setMessage("Loading...");
                        pDialog.show();


                        String URL="https://77ec4210.ngrok.io/login";
                        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL ,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.i("login response", response);
                                        pDialog.hide();
                                        try {
                                            JSONObject json = new JSONObject(response);
                                            JSONObject data = json.getJSONObject("data");
                                            String cid = data.getString("Customer_ID");
                                            String token = data.getString("token");
                                            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                            editor.putString("id", cid);
                                            editor.putString("token", token);
                                            editor.commit();

                                            startActivity(new Intent(MainActivity.this, NavActivity.class));
                                            finish();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(MainActivity.this, "Error occurred. Try again", Toast.LENGTH_SHORT).show();
                                        Log.e("login error response", error.toString());
                                        pDialog.hide();
                                    }
                                }){
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String,String> params=new HashMap<>();
                                params.put("number",mNumber.getText().toString());
                                params.put("otp", "otp");
                                /////////////////////////////////////////////
                                //TO DO
                                /////////////////////////////////////////////
                                return params;
                            }

                        };

                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        circularProgressButton.setProgress(-1);
                        Toast.makeText(MainActivity.this,"Error occurred. Try again",Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params=new HashMap<>();
                params.put("number",mNumber.getText().toString());
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }



}
