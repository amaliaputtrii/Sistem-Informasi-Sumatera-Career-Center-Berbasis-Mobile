package com.example.projectuasmobile

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivityDetailLowTutupPel : AppCompatActivity() {

    //menampilkan layout detail lowongan tutup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_low_tutup_pel)
        // val value = findViewById<ViewPager2>(R.id.signup_pel_id)
        val extras = intent.extras

    }

    fun btn_sigin_pel(view: View) {
        val i = Intent(applicationContext, MainActivity::class.java)
        startActivity(i)
    }
}