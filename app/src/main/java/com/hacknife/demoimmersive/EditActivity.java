package com.hacknife.demoimmersive;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.hacknife.immersive.Immersive;

public class EditActivity extends AppCompatActivity {
    @Override
    protected void onCreate(  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Immersive.setContentView(this, R.layout.activity_edit, R.color.test_one, R.color.test_one, false, false);
//        Immersive.setNavigationContentColor(this, Immersive.MODE.BLACK);
//        Immersive.setStatusContentColor(this, Immersive.MODE.WHITE);
    }
}
