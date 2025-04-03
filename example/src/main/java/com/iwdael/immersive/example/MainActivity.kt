package com.iwdael.immersive.example

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
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
//        Log.v("dzq", "onCreate:${currentPhoneRom}")
        setContentView(
            R.layout.activity_main,
            resources.getColor(R.color.blue),
            resources.getColor(R.color.green),
            stateStatusBar = BarState.SHOW,
            stateNavBar = BarState.DISABLE
        )
        val proRed = findViewById<SeekBar>(R.id.pro_red)
        val proGreen = findViewById<SeekBar>(R.id.pro_green)
        val proBlue = findViewById<SeekBar>(R.id.pro_blue)
        val proAlpha = findViewById<SeekBar>(R.id.pro_alpha)
        proRed.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
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
        proGreen.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
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
        proBlue.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
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
        proAlpha.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
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
            stateStatusBar = if (stateStatusBar == BarState.SHOW) {
                BarState.HIDDEN
            } else {
                BarState.SHOW
            }
        }
        val navigation = findViewById<Button>(R.id.btn_navigation)
        navigation.setOnClickListener {
            stateNavBar = if (stateNavBar == BarState.SHOW) {
                BarState.HIDDEN
            } else {
                BarState.SHOW
            }
        }
        val btnStatusTextColor = findViewById<Button>(R.id.btn_status_text_color)
        btnStatusTextColor.setOnClickListener(object : View.OnClickListener {
            var key = 0
            override fun onClick(v: View) {
                isLightNavBar = key % 2 == 0
                isLightStatusBar = key % 2 == 0

                key++
            }
        })
        val jump = findViewById<Button>(R.id.jump)
        jump.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity::class.java))
        }
    }


}