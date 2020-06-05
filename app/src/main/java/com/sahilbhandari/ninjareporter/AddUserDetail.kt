package com.sahilbhandari.ninjareporter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AddUserDetail : AppCompatActivity() {

    lateinit var btnBack:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user_detail)
        initViews()

        btnBack.setOnClickListener { finish() }
    }

    private fun initViews() {
        btnBack=findViewById(R.id.btnBack)
    }
}
