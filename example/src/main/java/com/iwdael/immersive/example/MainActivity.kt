package com.iwdael.immersive.example

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.iwdael.immersive.*

class MainActivity : AppCompatActivity() {
    private var red = 1
    private var green = 1
    private var blue = 1
    private var alpha = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_main,
            R.color.blue,
            R.color.green,
            false,
            false
        )
        //        Immersive.setStatusContentColor(MainActivity.this, Immersive.MODE.WHITE);
//        Immersive.setNavigationContentColor(MainActivity.this, Immersive.MODE.WHITE);
        val pro_red = findViewById<SeekBar>(R.id.pro_red)
        val pro_green = findViewById<SeekBar>(R.id.pro_green)
        val pro_blue = findViewById<SeekBar>(R.id.pro_blue)
        val pro_alpha = findViewById<SeekBar>(R.id.pro_alpha)
        pro_red.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                red = progress
                setStatusBarColor(Color.argb(alpha, red, green, blue))
                setNavigationBarColor(Color.argb(alpha, red, green, blue))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        pro_green.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                green = progress
                setStatusBarColor(Color.argb(alpha, red, green, blue))
                setNavigationBarColor(Color.argb(alpha, red, green, blue))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        pro_blue.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                blue = progress
                setStatusBarColor(Color.argb(alpha, red, green, blue))
                setNavigationBarColor(Color.argb(alpha, red, green, blue))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        pro_alpha.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                alpha = progress
                setStatusBarColor(Color.argb(alpha, red, green, blue))
                setNavigationBarColor(Color.argb(alpha, red, green, blue))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        val status = findViewById<Button>(R.id.btn_status)
        status.setOnClickListener {
            if (isShowOfStatus()) hideStatus() else showStatus()
        }
        val navigation =
            findViewById<Button>(R.id.btn_navigation)
        navigation.setOnClickListener {
            if (isShowOfNavigation()) hideNavigation() else showNavigation()
        }
        val btn_status_text_color = findViewById<Button>(R.id.btn_status_text_color)
        btn_status_text_color.setOnClickListener(object : View.OnClickListener {
            var key = 0
            override fun onClick(v: View) {
                if (key % 2 == 0) {
                    setStatusContentColor(MODE.BLACK)
                    setNavigationContentColor(MODE.BLACK)
                } else {
                    setStatusContentColor(MODE.WHITE)
                    setNavigationContentColor(MODE.WHITE)
                }
                key++
            }
        })
        val jump = findViewById<Button>(R.id.jump)
        jump.setOnClickListener(object : View.OnClickListener {
            var key = 0
            override fun onClick(v: View) {
                startActivity(Intent(this@MainActivity, MainActivity::class.java))
            }
        })
    }
}