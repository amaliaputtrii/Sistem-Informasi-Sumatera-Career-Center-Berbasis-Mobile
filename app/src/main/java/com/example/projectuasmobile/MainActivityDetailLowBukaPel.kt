package com.example.projectuasmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivityDetailLowBukaPel : AppCompatActivity() {

    //menampilkan layout lowongan buka pelamar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_low_buka_pel)
        // val value = findViewById<ViewPager2>(R.id.signup_pel_id)
        val extras = intent.extras

    }

    //mengembalikan ke halaman beranda pelamar ketika 'kembali' di klik
    fun btn_kembali_beranda_pel(view: View) {
        val i = Intent(applicationContext, MainActivityBerandaPelamar::class.java)
        startActivity(i)
    }




}