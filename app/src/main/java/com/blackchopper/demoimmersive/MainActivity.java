package com.blackchopper.demoimmersive;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.blackchopper.immersive.Immersive;


public class MainActivity extends AppCompatActivity {

    private int red=1;
    private int green=1;
    private int blue=1;
    private int alpha=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/**
 * 6.0 1,1
 * 6.0 0,1
 * 6.0 1,0
 * 6.0 0,0
 * <p>
 * <p>
 * 4.4 0,0
 * 4.4 1,1
 * 4.4 0,1
 * 4.4 1,0
 **/
        Immersive.setContentView(this, R.layout.activity_main, R.color.blue, R.color.green, false, false);

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
    }


}
