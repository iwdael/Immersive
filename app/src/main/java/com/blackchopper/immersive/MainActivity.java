package com.blackchopper.immersive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Immersive.setContentView(this, R.layout.activity_main, R.color.blue, R.color.green, true, false);
    }
}
/**
 * 6.0 1,1
 * 6.0 0,1
 * 6.0 1,0
 * 6.0 0,0
 *
 *
 * 4.4 0,0
 * 4.4 1,1
 * 4.4 0,1
 * 4.4 1,0
 **/