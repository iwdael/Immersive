package com.hacknife.demoimmersive;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.hacknife.immersive.Immersive;


public class MainActivity extends AppCompatActivity {

    private int red = 1;
    private int green = 1;
    private int blue = 1;
    private int alpha = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Immersive.setContentView(this, R.layout.activity_main, R.color.blue, R.color.green, false, false);
        Immersive.setStatusContentColor(MainActivity.this, Immersive.MODE.WHITE);
        Immersive.setNavigationContentColor(MainActivity.this, Immersive.MODE.WHITE);
        SeekBar pro_red = findViewById(R.id.pro_red);
        SeekBar pro_green = findViewById(R.id.pro_green);
        SeekBar pro_blue = findViewById(R.id.pro_blue);
        SeekBar pro_alpha = findViewById(R.id.pro_alpha);
        pro_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                red = progress;
                Immersive.setStatusBarColor(MainActivity.this, Color.argb(alpha, red, green, blue));
                Immersive.setNavigationBarColor(MainActivity.this, Color.argb(alpha, red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        pro_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                green = progress;
                Immersive.setStatusBarColor(MainActivity.this, Color.argb(alpha, red, green, blue));
                Immersive.setNavigationBarColor(MainActivity.this, Color.argb(alpha, red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        pro_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blue = progress;
                Immersive.setStatusBarColor(MainActivity.this, Color.argb(alpha, red, green, blue));
                Immersive.setNavigationBarColor(MainActivity.this, Color.argb(alpha, red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        pro_alpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                alpha = progress;
                Immersive.setStatusBarColor(MainActivity.this, Color.argb(alpha, red, green, blue));
                Immersive.setNavigationBarColor(MainActivity.this, Color.argb(alpha, red, green, blue));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button status = findViewById(R.id.btn_status);
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Immersive.isShowOfStatus(MainActivity.this))
                    Immersive.hideStatus(MainActivity.this);
                else
                    Immersive.showStatus(MainActivity.this);
            }
        });

        Button navigation = findViewById(R.id.btn_navigation);
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Immersive.isShowOfNavigation(MainActivity.this))
                    Immersive.hideNavigation(MainActivity.this);
                else
                    Immersive.showNavigation(MainActivity.this);
            }
        });

        Button btn_status_text_color = findViewById(R.id.btn_status_text_color);
        btn_status_text_color.setOnClickListener(new View.OnClickListener() {
            int key = 0;

            @Override
            public void onClick(View v) {
                if (key % 2 == 0) {
                    Immersive.setStatusContentColor(MainActivity.this, Immersive.MODE.BLACK);
                    Immersive.setNavigationContentColor(MainActivity.this, Immersive.MODE.BLACK);
                } else {
                    Immersive.setStatusContentColor(MainActivity.this, Immersive.MODE.WHITE);
                    Immersive.setNavigationContentColor(MainActivity.this, Immersive.MODE.WHITE);
                }
                key++;
            }
        });
        Button jump = findViewById(R.id.jump);
        jump.setOnClickListener(new View.OnClickListener() {
            int key = 0;

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }
}
