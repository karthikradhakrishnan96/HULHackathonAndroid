package com.hulhack.quandrum.wireframes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hulhack.quandrum.wireframes.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Hindustan Unilever");
    }

    public void buttonClick(View view){
        Intent i = new Intent(this,NavActivity.class);
        startActivity(i);
    }
}
