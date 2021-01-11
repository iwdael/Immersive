package com.hacknife.demoimmersive;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.hacknife.immersive.ImmersiveKt;
import com.hacknife.immersive.MODE;


public class EditActivity extends AppCompatActivity {
    @Override
    protected void onCreate(  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersiveKt.setContentView( this, R.layout.activity_edit, R.color.test_one, R.color.test_one, false, false);
        ImmersiveKt.setNavigationContentColor(this, MODE.BLACK);
        ImmersiveKt.setStatusContentColor(this, MODE.WHITE);

    }
}
