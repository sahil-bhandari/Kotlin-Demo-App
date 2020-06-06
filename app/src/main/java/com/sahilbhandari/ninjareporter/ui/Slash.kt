package com.sahilbhandari.ninjareporter.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.sahilbhandari.ninjareporter.R

class Slash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            finish()
            startActivity(Intent(this, UserList::class.java))
            overridePendingTransition(0,0)
        }, 2000)
    }
}
