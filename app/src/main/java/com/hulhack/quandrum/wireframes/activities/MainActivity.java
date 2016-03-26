package com.hulhack.quandrum.wireframes.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dd.CircularProgressButton;
import com.hulhack.quandrum.wireframes.R;
import com.hulhack.quandrum.wireframes.utils.AppController;
import com.hulhack.quandrum.wireframes.utils.Router;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public String TAG = "Returned";
    EditText mNumber;
    CircularProgressButton circularProgressButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Hindustan Unilever");
        mNumber=(EditText)findViewById(R.id.editText);
        // Tag used to cancel the request
        circularProgressButton=(CircularProgressButton)findViewById(R.id.btnWithText);
        circularProgressButton.setIndeterminateProgressMode(true);

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
                        Intent i=new Intent(MainActivity.this,NavActivity.class);
                        startActivity(i);
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
